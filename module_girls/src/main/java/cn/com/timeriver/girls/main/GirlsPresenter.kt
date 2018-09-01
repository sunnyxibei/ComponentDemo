package cn.com.timeriver.girls.main

import cn.com.timeriver.common.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GirlsPresenter(val view: GirlsContract.View) : GirlsContract.Presenter {

    /**
     * View和Presenter绑定
     */
    override fun subscribe() {
        view.setPresenter(this)
        getGirls(20, 1, false)
    }

    override fun getGirls(size: Int, page: Int, isRefresh: Boolean) {
        HttpClient.instance.getGirls(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (isRefresh) {
                        view.refresh(it.results)
                    } else {
                        view.load(it.results)
                    }
                    view.showNormal()
                }
                .doOnError {
                    if (isRefresh) {
                        view.showError()
                    }
                }
                .subscribe()

    }
}