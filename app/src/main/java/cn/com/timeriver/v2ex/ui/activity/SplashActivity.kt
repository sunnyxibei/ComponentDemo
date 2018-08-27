package cn.com.timeriver.v2ex.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.com.timeriver.v2ex.R
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tv_brand.show()
        tv_brand.setOnClickListener { startRouter() }
    }

    private fun startRouter() {
        ARouter.getInstance().build("/home/home_page").navigation()
    }
}
