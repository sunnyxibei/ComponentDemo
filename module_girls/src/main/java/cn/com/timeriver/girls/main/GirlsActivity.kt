package cn.com.timeriver.girls.main

import cn.com.timeriver.common.base.BaseActivity
import cn.com.timeriver.girls.R
import kotlinx.android.synthetic.main.activity_girls.*

class GirlsActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_girls

    override fun initView() {
        setSupportActionBar(toolbar)
    }

    override fun initData() {
        val girlsView = findViewById<GirlsView>(R.id.girls_view)
        val mPresenter: GirlsContract.Presenter = GirlsPresenter(girlsView)
        mPresenter.subscribe()
    }

}