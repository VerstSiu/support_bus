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
@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("AnalyticsKt")

package com.ijoic.supportbus.analytics

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Analytics instance.
 *
 * @since 1.0
 */
val Context.analytics: Analytics
  get() = AnalyticsFactory.getAnalytics(this)

/**
 * Analytics instance
 *
 * @since 1.1
 */
val Fragment.analytics: Analytics?
  get() = context?.analytics

/**
 * Normal event with [name] and single key-value [pair]
 *
 * @author verstsiu created at 2019-02-22 20:47
 * @since 1.0.1
 */
fun Analytics.onEvent(name: String, pair: Pair<String, String>) {
  this.onEvent(name, mapOf(pair))
}

/**
 * Normal event with [name] and key-value [pairs]
 *
 * @author verstsiu created at 2019-02-22 20:48
 * @since 1.0.1
 */
fun Analytics.onEvent(name: String, vararg pairs: Pair<String, String>) {
  this.onEvent(name, mapOf(*pairs))
}