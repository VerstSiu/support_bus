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

/**
 * Analytics.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
interface Analytics {
  /**
   * Normal event with event [name] and [params]
   */
  fun onEvent(name: String, params: Map<String, String>? = null)

  /**
   * Trace event with [path], [category], [action], [name] and [value]
   */
  fun onTrack(path: String? = null, category: String, action: String, name: String, value: Float? = null) {}

  /**
   * Screen event with [activity], [path], [title] and [params]
   */
  @Deprecated(message = "use register + screen as replace")
  fun onScreen(activity: Activity, path: String, title: String? = null, params: Map<String, String>? = null) {
    register(activity)
    screen(activity, path, title, params)
  }

  /**
   * Register analytics to [activity]
   */
  fun register(activity: Activity) {}

  /**
   * Screen event with [activity], [path], [title] and [params]
   */
  fun screen(activity: Activity, path: String, title: String? = null, params: Map<String, String>? = null) {}

  /**
   * Bind [userId] with [context]
   */
  fun onBindUserId(context: Context, userId: String) {}

  /**
   * Unbind userId with [context]
   */
  fun onUnbindUserId(context: Context) {}
}