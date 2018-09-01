package cn.com.timeriver.common.http

import cn.com.timeriver.common.data.bean.GirlsWrapper
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient private constructor() {

    private var mUserApi: UserApi

    companion object {
        val instance by lazy { HttpClient() }
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(HttpConstants.GAN_HUO_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        mUserApi = retrofit.create(UserApi::class.java)
    }

    fun getGirls(size: Int, path: Int): Observable<GirlsWrapper> {
        return mUserApi.getGirls(size, path)
    }

}