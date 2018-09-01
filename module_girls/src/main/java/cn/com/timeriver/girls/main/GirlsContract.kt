package cn.com.timeriver.girls.main

import cn.com.timeriver.common.base.BasePresenter
import cn.com.timeriver.common.base.BaseView
import cn.com.timeriver.common.data.bean.Girl

interface GirlsContract {

    interface View : BaseView<Presenter> {
        /**
         * View 的存活状态
         *
         * @return true or false
         */
        fun isActive(): Boolean

        fun refresh(data: List<Girl>)

        fun load(data: List<Girl>)

        fun showError()

        fun showNormal()
    }

    interface Presenter : BasePresenter {

        fun getGirls(size: Int, page: Int, isRefresh: Boolean)
    }
}