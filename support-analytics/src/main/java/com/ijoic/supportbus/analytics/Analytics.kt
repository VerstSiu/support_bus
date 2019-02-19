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

import com.ijoic.supportbus.analytics.event.EventBuilder

/**
 * Analytics.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
interface Analytics {
  /**
   * Event triggered with [category], [action], [name] and [params]
   */
  fun onEvent(category: String, action: String, name: String? = null, params: Map<String, String>? = null) {
    val extras = mutableMapOf<String, String>().apply {
      put(PARAM_ACTION, action)

      if (name != null) {
        put(PARAM_NAME, name)
      }
    }

    if (params == null || params.isEmpty()) {
      this.onEvent(category, extras)
    } else {
      this.onEvent(category, params.toMutableMap().apply { putAll(extras) })
    }
  }

  /**
   * Event triggered with [event], [params]
   */
  fun onEvent(event: String, params: Map<String, String>? = null)

  /**
   * Edit event.
   *
   * @param event event name.
   */
  fun editEvent(event: String): EventBuilder {
    return EventBuilder(this, event)
  }

  companion object {
    const val PARAM_ACTION = "action"
    const val PARAM_NAME = "name"
  }
}