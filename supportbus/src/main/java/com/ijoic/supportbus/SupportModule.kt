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

import androidx.lifecycle.Lifecycle
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Support module.
 *
 * @author verstsiu on 2018/8/18.
 * @version 1.0
 */
interface SupportModule {
  /**
   * Application init.
   *
   * @param context application context.
   */
  fun onAppInit(context: Context)

  /**
   * Activity init.
   *
   * @param activity activity.
   * @param lifecycle lifecycle.
   */
  fun onActivityInit(activity: FragmentActivity, lifecycle: Lifecycle)

  /**
   * Fragment init.
   *
   * @param fragment fragment.
   * @param lifecycle lifecycle.
   */
  fun onFragmentInit(fragment: Fragment, lifecycle: Lifecycle)
}