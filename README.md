# TemplateSimpleProject-databinding

简化版的Android空壳模版工程(kotlin-databinding版本)，快速搭建（集成了XUI、XUtil、XAOP、XPage、友盟统计和walle多渠道打包)

**【注意】** 本模板适用于Android Studio 4.0及以上版本。

## 关于我

| 公众号   | 掘金     |  知乎    |  CSDN   |   简书   |   思否  |   哔哩哔哩  |   今日头条
|---------|---------|--------- |---------|---------|---------|---------|---------|
| [我的Android开源之旅](https://t.1yb.co/Irse)  |  [点我](https://juejin.im/user/598feef55188257d592e56ed/posts)    |   [点我](https://www.zhihu.com/people/xuexiangjys/posts)       |   [点我](https://xuexiangjys.blog.csdn.net/)  |   [点我](https://www.jianshu.com/u/6bf605575337)  |   [点我](https://segmentfault.com/u/xuexiangjys)  |   [点我](https://space.bilibili.com/483850585)  |   [点我](https://img.rruu.net/image/5ff34ff7b02dd)

## 集成介绍（请star支持）

> 本项目是项目androidx项目。精选了X系列最最实用的几个库，可大大提高开发的效率。

* [XUI 一个简洁而优雅的Android原生UI框架，解放你的双手！](https://github.com/xuexiangjys/XUI)

* [XUtil 一个方便实用的Android工具类库！](https://github.com/xuexiangjys/XUtil)

* [XAOP 一个轻量级的AOP(Android)应用框架。囊括了最实用的AOP应用。](https://github.com/xuexiangjys/XAOP)

* [XPage 一个非常方便的fragment页面框架。](https://github.com/xuexiangjys/XPage)

除此之外，还集成了其他优秀的第三方库:

* [AndroidAutoSize 优秀的屏幕适配方案](https://github.com/JessYanCoding/AndroidAutoSize)

* [ViewBinding Google推荐的视图绑定工具](https://developer.android.google.cn/topic/libraries/view-binding)

* [leakcanary 内存泄漏检测](https://github.com/square/leakcanary)

## 使用方式

1.克隆项目

```
git clone https://github.com/xuexiangjys/TemplateSimpleProject-databinding.git
```

2.修改项目名（文件夹名），并删除目录下的.git文件夹（隐藏文件）

3.使用AS打开项目，然后修改`包名`、`applicationId`和`app_name`

* 修改包名

![templateproject_1.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/templateproject/1.png)

![templateproject_2.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/templateproject/2.png)

* 修改applicationId

![templateproject_3.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/templateproject/3.png)

* 修改app_name

![templateproject_5.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/templateproject/5.png)

## 项目打包

1.修改工程根目录的`gradle.properties`中的`isNeedPackage=true`。

2.添加并配置keystore，在`versions.gradle`中修改`app_release`相关参数。

3.如果考虑使用友盟统计的话，在`local.properties`中设置应用的友盟ID:`APP_ID_UMENG`。

4.使用`./gradlew clean assembleReleaseChannels`进行多渠道打包。

## 如果觉得项目还不错，可以考虑打赏一波

> 你的打赏是我维护的动力，我将会列出所有打赏人员的清单在下方作为凭证，打赏前请留下打赏项目的备注！

![pay.png](https://raw.githubusercontent.com/xuexiangjys/Resource/master/img/pay/pay.png)

## 联系方式

> 更多资讯内容，欢迎扫描关注我的个人微信公众号:【我的Android开源之旅】

![](https://s1.ax1x.com/2022/04/27/LbGMJH.jpg)