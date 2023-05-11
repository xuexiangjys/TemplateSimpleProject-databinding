/*
 * Copyright (C) 2023 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.databindingsample.core.databinding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * 基础AndroidViewModel
 *
 * 存放通用的状态
 *
 * @author xuexiang
 * @since 2023/4/22 23:24
 */
abstract class DataBindingPageState(application: Application) : AndroidViewModel(application) {

    val isLoading = MutableLiveData(false)

    val title = MutableLiveData(this.initTitle())

    abstract fun initTitle() : String

}