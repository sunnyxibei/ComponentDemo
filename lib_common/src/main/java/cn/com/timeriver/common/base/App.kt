package cn.com.timeriver.common.base

import android.app.Application

/**
 * 提供一些全局的初始化
 *
 * 这样设计的目的，是用以解决组件开发（debug）和集成测试时阶段，
 * 要同时保证壳的Application和测试时各个module的Application都可以正常获取到Application的实例
 * 都要继承于当前基类
 */
abstract class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun getAppInstance(): App {
            return instance
        }
    }

}