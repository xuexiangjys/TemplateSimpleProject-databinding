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

package com.xuexiang.templateproject.core.databinding

import android.app.Application
import androidx.databinding.ViewDataBinding

/**
 * 提供ViewBinding的State
 *
 * @author xuexiang
 * @since 2023/6/2 00:39
 */
abstract class DataBindingProviderState<DataBinding : ViewDataBinding>(application: Application) :
    DataBindingPageState(application) {

    private var provider: IDataBindingProvider<DataBinding>? = null

    fun getBinding() = provider?.getViewBinding()

    fun setDataBindingProvider(iProvider: IDataBindingProvider<DataBinding>?) {
        provider = iProvider
    }
}