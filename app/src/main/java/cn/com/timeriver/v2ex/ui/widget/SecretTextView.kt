package cn.com.timeriver.v2ex.ui.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by matt on 5/27/14.
 */
class SecretTextView : AppCompatTextView {

    private var mAlphas: DoubleArray? = null
    private var mIsVisible: Boolean = false
    private var mIsTextResetting = false
    private var mDuration = 2500
    private var percent: Float = 0f

    private var mTextString: String? = null
    private var mSpannableString: SpannableString? = null
    private var mSpans: Array<MutableForegroundColorSpan>? = null
    private var animator: ValueAnimator? = null

    var isVisible: Boolean
        get() = mIsVisible
        set(isVisible) {
            mIsVisible = isVisible
            resetSpannableString(if (isVisible) 2.0f else 0.0f)
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        this.mIsVisible = false
        animator = ObjectAnimator.ofFloat(this, "percent", 0.0f, 2.0f)
        animator!!.duration = mDuration.toLong()
    }

    fun toggle() {
        if (mIsVisible) {
            hide()
        } else {
            show()
        }
    }

    fun show() {
        mIsVisible = true
        if (animator!!.isRunning) {
            animator!!.cancel()
        }
        animator!!.start()
    }

    fun hide() {
        mIsVisible = false
        animator!!.start()
    }

    fun setPercent(percent: Float) {
        this.percent = percent
        resetSpannableString(percent)
    }

    private fun resetSpannableString(percent: Float) {
        mIsTextResetting = true
        val color = currentTextColor
        for (i in 0 until this.mTextString!!.length) {
            val span = mSpans!![i]
            span.color = Color.argb(clamp(mAlphas!![i] + percent), Color.red(color),
                    Color.green(color), Color.blue(color))
        }
        text = mSpannableString
        mIsTextResetting = false
    }

    private fun resetAlphas(length: Int) {
        mAlphas = DoubleArray(length)
        for (i in mAlphas!!.indices) {
            mAlphas!![i] = Math.random() - 1
        }
    }

    private fun resetIfNeeded() {
        if (!mIsTextResetting) {
            mTextString = text.toString()
            mSpannableString = SpannableString(mTextString)
            mSpans = Array(mTextString!!.length) {
                val span = MutableForegroundColorSpan()
                mSpannableString!!.setSpan(span, it, it + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                span
            }
            resetAlphas(mTextString!!.length)
            resetSpannableString(if (mIsVisible) 2.0f else 0f)
        }
    }

    fun setText(text: String) {
        super.setText(text)
        resetIfNeeded()
    }

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        super.setText(text, type)
        resetIfNeeded()
    }

    private fun clamp(f: Double): Int {
        return (255 * Math.min(Math.max(f, 0.0), 1.0)).toInt()
    }

    fun setDuration(duration: Int) {
        this.mDuration = duration
        animator!!.duration = duration.toLong()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (animator != null) {
            animator!!.cancel()
        }
    }

    inner class MutableForegroundColorSpan : CharacterStyle(), UpdateAppearance {

        var color: Int = 0

        override fun updateDrawState(tp: TextPaint) {
            tp.color = color
        }
    }
}