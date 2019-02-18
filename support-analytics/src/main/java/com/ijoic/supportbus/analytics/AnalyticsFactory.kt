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
  private var creator: ((Context) -> Analytics)? = null

  /**
   * Register analytics creator.
   *
   * @param creator analytics creator.
   */
  internal fun register(creator: (Context) -> Analytics) {
    this.creator = creator
  }

  /**
   * Returns analytics instance.
   *
   * @param context context.
   */
  internal fun getAnalytics(context: Context): Analytics {
    var instance = analyticsCache[context]

    if (instance == null) {
      instance = creator?.invoke(context.applicationContext)

      if (instance != null) {
        analyticsCache[context] = instance
      }
    }
    return instance ?: EmptyAnalytics
  }

  /**
   * Empty analytics.
   */
  private object EmptyAnalytics: Analytics {
    override fun onEvent(eventId: String, params: Map<String, String>?, extras: Map<String, String>?) {
      // do nothing.
    }
  }

}