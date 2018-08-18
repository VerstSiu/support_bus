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
package com.ijoic.supportbus.analytics.event

import com.ijoic.supportbus.analytics.Analytics

/**
 * Event builder.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
class EventBuilder internal constructor(
    private val analytics: Analytics,
    private val event: String) {
  private val params by lazy { mutableMapOf<String, String>() }

  /**
   * Set event parameters.
   *
   * @param key param key.
   * @param value param value.
   */
  fun setParams(key: String, value: String): EventBuilder {
    params[key] = value
    return this
  }

  /**
   * Apply event.
   */
  fun apply() {
    analytics.onEvent(event, params)
  }
}