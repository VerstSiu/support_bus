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
package com.ijoic.supportbus

import android.content.Context

/**
 * Support bus config.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
class SupportBusConfig {
  private val moduleItems = mutableListOf<SupportModule>()

  /**
   * Add support module.
   *
   * @param module support module.
   */
  fun addModule(module: SupportModule): SupportBusConfig {
    moduleItems.add(module)
    return this
  }

  /**
   * Initialize support bus config.
   *
   * @param context context.
   */
  fun apply(context: Context) {
    val appContext = context.applicationContext
    SupportBus.moduleItems = moduleItems
    moduleItems.forEach { it.onAppInit(appContext) }
  }
}