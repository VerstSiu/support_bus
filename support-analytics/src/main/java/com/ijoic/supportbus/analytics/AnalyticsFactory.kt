/*
 *
 *  Copyright(c) 2018 VerstSiu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.ijoic.supportbus.analytics

import android.app.Activity
import android.content.Context
import java.util.*

/**
 * Analytics factory.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
internal object AnalyticsFactory {

  private val analyticsCache by lazy { WeakHashMap<Context, Analytics>() }
  private var creatorItems = mutableSetOf<(Context) -> Analytics>()

  /**
   * Register analytics creator.
   *
   * @param creator analytics creator.
   */
  internal fun register(creator: (Context) -> Analytics) {
    creatorItems.add(creator)
  }

  /**
   * Returns analytics instance.
   *
   * @param context context.
   */
  internal fun getAnalytics(context: Context): Analytics {
    val appContext = context.applicationContext
    var instance = analyticsCache[appContext]
    val creatorItems = this.creatorItems

    if (instance == null && !creatorItems.isEmpty()) {
      instance = if (creatorItems.size == 1) {
        creatorItems.first().invoke(appContext)
      } else {
        WrapListAnalytics(
            creatorItems.map { it.invoke(appContext) }
        )
      }

      analyticsCache[context] = instance
    }
    return instance ?: BlankAnalytics
  }

  /**
   * Blank analytics.
   */
  private object BlankAnalytics: Analytics {
    override fun onEvent(name: String, params: Map<String, String>?) {
      // do nothing.
    }

    override fun onScreen(activity: Activity, path: String, title: String?, params: Map<String, String>?) {
      // do nothing.
    }
  }

  private class WrapListAnalytics(private val items: List<Analytics>): Analytics {
    override fun onEvent(name: String, params: Map<String, String>?) {
      items.forEach { it.onEvent(name, params) }
    }

    override fun onTrack(path: String?, category: String, action: String, name: String, value: Float?) {
      items.forEach { it.onTrack(path, category, action, name, value) }
    }

    override fun register(activity: Activity) {
      items.forEach { it.register(activity) }
    }

    override fun screen(activity: Activity, path: String, title: String?, params: Map<String, String>?) {
      items.forEach { it.screen(activity, path, title, params) }
    }

    override fun onBindUserId(context: Context, userId: String) {
      items.forEach { it.onBindUserId(context, userId) }
    }

    override fun onUnbindUserId(context: Context) {
      items.forEach { it.onUnbindUserId(context) }
    }
  }

}