/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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
package com.xuexiang.templateproject.fragment

import com.xuexiang.templateproject.R
import com.xuexiang.templateproject.core.databinding.DataBindingFragment
import com.xuexiang.templateproject.databinding.FragmentDataBindingEmptyBinding
import com.xuexiang.templateproject.fragment.model.EmptyState
import com.xuexiang.xpage.annotation.Page

/**
 * 这个是使用DataBinding布局，自动生成的是ViewDataBinding
 *
 * @author xuexiang
 * @since 2019-07-08 00:52
 */
@Page(name = "DataBinding空页面")
class EmptyDataBindingFragment : DataBindingFragment<FragmentDataBindingEmptyBinding?, EmptyState>() {

    override fun getLayoutId() = R.layout.fragment_data_binding_empty

}