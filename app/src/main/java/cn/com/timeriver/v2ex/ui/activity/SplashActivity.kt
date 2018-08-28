package cn.com.timeriver.v2ex.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import cn.com.timeriver.v2ex.R
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val resArray = arrayOf(R.drawable.bg_splash_1, R.drawable.bg_splash_2)
        tv_brand.show()

        image.setImageResource(resArray[(Math.random() * 2).toInt()])

        val animatorX = ObjectAnimator.ofFloat(image,
                "scaleX", 1f, 1.05f)
        animatorX.duration = 5000
        val animatorY = ObjectAnimator.ofFloat(image,
                "scaleY", 1f, 1.05f)
        animatorY.duration = 5000
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animatorX, animatorY)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                startRouter()
            }
        })
        animatorSet.start()
    }

    private fun startRouter() {
        val optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(image,
                image.width / 2, image.height / 2, 0, 0)
        ARouter.getInstance().build("/home/home_page").withOptionsCompat(optionsCompat).navigation()
        finish()
    }
}
