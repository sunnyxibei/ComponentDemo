package cn.com.timeriver.common.http

import cn.com.timeriver.common.data.bean.GirlsWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("福利/{size}/{path}")
    fun getGirls(@Path("size") size: Int, @Path("path") path: Int): Observable<GirlsWrapper>
}