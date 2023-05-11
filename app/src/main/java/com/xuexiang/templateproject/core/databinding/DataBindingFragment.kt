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

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umeng.analytics.MobclickAgent
import com.xuexiang.xpage.base.XPageActivity
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.core.PageOption
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.Utils
import com.xuexiang.xrouter.facade.service.SerializationService
import com.xuexiang.xrouter.launcher.XRouter
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.actionbar.TitleBar
import com.xuexiang.xui.widget.actionbar.TitleUtils
import com.xuexiang.xui.widget.progress.loading.IMessageLoader
import java.io.Serializable
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 基础fragment(DataBinding)
 *
 * @author xuexiang
 * @since 2018/5/25 下午3:44
 */
abstract class DataBindingFragment<DataBinding : ViewDataBinding?, VM : ViewModel> :
    XPageFragment(), OnDataBindingListener {

    private var mMessageLoader: IMessageLoader? = null

    /**
     * DataBinding, XML布局要加<layout></layout>
     */
    var binding: DataBinding? = null
        protected set

    /**
     * viewModel
     */
    open lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    @Suppress("UNCHECKED_CAST")
    open fun createViewModel(): VM {
        val genericSuperclass = this.javaClass.genericSuperclass
        return (genericSuperclass as ParameterizedType).actualTypeArguments.let { typeArray ->
            // 因为ViewModel是第二个泛型，所以这里取typeArray[1]
            ViewModelProvider(this).get(typeArray[1] as Class<VM>)
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View? {
        binding =
            DataBindingUtil.inflate<DataBinding>(inflater, getLayoutId(), container, attachToRoot)
        return binding?.bindViewModel(viewLifecycleOwner, viewModel, this)
    }

    /**
     * DataBinding更新
     * @param binding DataBinding
     */
    override fun onDataBindingUpdate(binding: ViewDataBinding) {

    }

    abstract fun getLayoutId(): Int

    override fun initPage() {
        initTitle()
        initViews()
        initListeners()
    }

    open fun initTitle(): TitleBar? {
        return TitleUtils.addTitleBarDynamic(rootView as ViewGroup, pageTitle) { popToBack() }
    }

    override fun initListeners() {}

    override fun initViews() {}

    //==========================================================//

    fun getMessageLoader(): IMessageLoader? {
        if (mMessageLoader == null) {
            mMessageLoader = WidgetUtils.getMiniLoadingDialog(requireContext())
        }
        return mMessageLoader
    }

    fun getMessageLoader(message: String): IMessageLoader? {
        if (mMessageLoader == null) {
            mMessageLoader = WidgetUtils.getMiniLoadingDialog(requireContext(), message)
        } else {
            mMessageLoader?.updateMessage(message)
        }
        return mMessageLoader
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        //屏幕旋转时刷新一下title
        super.onConfigurationChanged(newConfig)
        val root = rootView as ViewGroup
        if (root.getChildAt(0) is TitleBar) {
            root.removeViewAt(0)
            initTitle()
        }
    }

    override fun onDestroyView() {
        mMessageLoader?.dismiss()
        mMessageLoader = null
        super.onDestroyView()
        onDataBindingUnbind()
    }

    /**
     * DataBinding解绑
     */
    override fun onDataBindingUnbind() {
        binding?.unbind()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(pageName)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(pageName)
    }

    //==============================页面跳转api===================================//
    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz 页面的类
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(clazz: Class<T>?): Fragment? {
        return PageOption(clazz).setNewActivity(true).open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param pageName 页面名
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(pageName: String?): Fragment? {
        return PageOption(pageName).setAnim(CoreAnim.slide).setNewActivity(true).open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz                页面的类
     * @param containActivityClazz 页面容器
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(
        clazz: Class<T>?, containActivityClazz: Class<out XPageActivity?>
    ): Fragment? {
        return PageOption(clazz).setNewActivity(true).setContainActivityClazz(containActivityClazz)
            .open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(clazz: Class<T>?, key: String?, value: Any?): Fragment? {
        val option = PageOption(clazz).setNewActivity(true)
        return openPage(option, key, value)
    }

    private fun openPage(option: PageOption, key: String?, value: Any?): Fragment? {
        when (value) {
            is Int -> option.putInt(key, value)
            is Float -> option.putFloat(key, value)
            is String -> option.putString(key, value)
            is Boolean -> option.putBoolean(key, value)
            is Long -> option.putLong(key, value)
            is Double -> option.putDouble(key, value)
            is Parcelable -> option.putParcelable(key, value)
            is Serializable -> option.putSerializable(key, value)
            else -> option.putString(key, serializeObject(value))
        }
        return option.open(this)
    }

    /**
     * 打开页面
     *
     * @param clazz          页面的类
     * @param addToBackStack 是否加入回退栈
     * @param key            入参的键
     * @param value          入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(
        clazz: Class<T>?, addToBackStack: Boolean, key: String?, value: String?
    ): Fragment? {
        return PageOption(clazz).setAddToBackStack(addToBackStack).putString(key, value).open(this)
    }

    /**
     * 打开页面
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(clazz: Class<T>?, key: String?, value: Any?): Fragment? {
        return openPage(clazz, true, key, value)
    }

    /**
     * 打开页面
     *
     * @param clazz          页面的类
     * @param addToBackStack 是否加入回退栈
     * @param key            入参的键
     * @param value          入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(
        clazz: Class<T>?, addToBackStack: Boolean, key: String?, value: Any?
    ): Fragment? {
        val option = PageOption(clazz).setAddToBackStack(addToBackStack)
        return openPage(option, key, value)
    }

    /**
     * 打开页面
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(clazz: Class<T>?, key: String?, value: String?): Fragment? {
        return PageOption(clazz).putString(key, value).open(this)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param key         入参的键
     * @param value       入参的值
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(
        clazz: Class<T>?, key: String?, value: Any?, requestCode: Int
    ): Fragment? {
        val option = PageOption(clazz).setRequestCode(requestCode)
        return openPage(option, key, value)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param key         入参的键
     * @param value       入参的值
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(
        clazz: Class<T>?, key: String?, value: String?, requestCode: Int
    ): Fragment? {
        return PageOption(clazz).setRequestCode(requestCode).putString(key, value).open(this)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(clazz: Class<T>?, requestCode: Int): Fragment? {
        return PageOption(clazz).setRequestCode(requestCode).open(this)
    }

    /**
     * 序列化对象
     *
     * @param object 需要序列化的对象
     * @return 序列化结果
     */
    fun serializeObject(target: Any?): String {
        return XRouter.getInstance().navigation(SerializationService::class.java)
            .object2Json(target)
    }

    /**
     * 反序列化对象
     *
     * @param input 反序列化的内容
     * @param clazz 类型
     * @return 反序列化结果
     */
    fun <T> deserializeObject(input: String?, clazz: Type?): T {
        return XRouter.getInstance().navigation(SerializationService::class.java)
            .parseObject(input, clazz)
    }

    override fun hideCurrentPageSoftInput() {
        if (activity == null) {
            return
        }
        // 记住，要在xml的父布局加上android:focusable="true" 和 android:focusableInTouchMode="true"
        Utils.hideSoftInputClearFocus(requireActivity().currentFocus)
    }
}