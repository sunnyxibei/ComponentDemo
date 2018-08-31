package cn.com.timeriver.main.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import cn.com.timeriver.common.widget.SecretTextView
import cn.com.timeriver.main.R
import com.alibaba.android.arouter.launcher.ARouter

class SplashActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val textView = findViewById<SecretTextView>(R.id.tv_brand)
        textView.show()

        imageView = findViewById(R.id.image)
        val resArray = arrayOf(R.drawable.bg_splash_1, R.drawable.bg_splash_2)
        imageView.setImageResource(resArray[(Math.random() * 2).toInt()])

        val animatorX = ObjectAnimator.ofFloat(imageView,
                "scaleX", 1f, 1.05f)
        animatorX.duration = 5000
        val animatorY = ObjectAnimator.ofFloat(imageView,
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
        val optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(imageView,
                imageView.width / 2, imageView.height / 2, 0, 0)
        ARouter.getInstance().build("/home/home_page").withOptionsCompat(optionsCompat).navigation()
        finish()
    }
}
