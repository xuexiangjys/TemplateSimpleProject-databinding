/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
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

import android.view.LayoutInflater
import android.view.ViewGroup
import com.xuexiang.templateproject.core.viewbinding.ViewBindingFragment
import com.xuexiang.templateproject.databinding.FragmentViewBindingEmptyBinding
import com.xuexiang.xpage.annotation.Page

/**
 * 这个是使用ViewBinding布局，自动生成的是ViewBinding
 *
 * @author xuexiang
 * @since 2022/7/7 1:40 上午
 */
@Page(name = "ViewBinding空页面")
class EmptyViewBindingFragment : ViewBindingFragment<FragmentViewBindingEmptyBinding>() {

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) = FragmentViewBindingEmptyBinding.inflate(inflater, container, attachToRoot)


    override fun initViews() {

    }
}