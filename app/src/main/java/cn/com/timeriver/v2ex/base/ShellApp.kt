package cn.com.timeriver.v2ex.base

import cn.com.timeriver.common.base.App
import cn.com.timeriver.v2ex.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

//ShellApp Module 作为壳工程，提供一些全局的初始化
class ShellApp : App() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {             // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()                // 打印日志
            ARouter.openDebug()              // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)          // 尽可能早，推荐在Application中初始化
    }

}