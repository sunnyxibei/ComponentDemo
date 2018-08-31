package cn.com.timeriver.main.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import cn.com.timeriver.common.base.RouterMap
import cn.com.timeriver.main.R
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_home.*

/**
 * 主页面，包含了三个button，分别指向
 */
@Route(path = RouterMap.Main.HOME_PAGE)
class HomeActivity : AppCompatActivity() {

    private var mExitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bt_news.setOnClickListener { ARouter.getInstance().build("/to/do").navigation() }
        bt_girls.setOnClickListener { ARouter.getInstance().build("/to/do").navigation() }
        bt_fragment.setOnClickListener { ARouter.getInstance().build("/to/do").navigation() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                mExitTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}