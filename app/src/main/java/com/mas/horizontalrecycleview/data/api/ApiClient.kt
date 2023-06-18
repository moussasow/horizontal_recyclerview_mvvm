package com.mas.horizontalrecycleview.data.api

import android.content.Context
import com.mas.horizontalrecycleview.data.model.ContentsModel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


object ApiClient {

    private const val baseUrl = "https://diga.pythonanywhere.com"
    private const val contentsPath = "izutsuya_contents"

    private fun okHttpClient(): OkHttpClient {
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Content-Type", "charset=UTF-8")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
            return@Interceptor it.proceed(request)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    private fun create(context: Context): MasApi {
        return Retrofit.Builder()
            .client(okHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(MasApi::class.java)
    }

    fun getContents(context: Context, observer: Observer<ContentsModel>
    ): Subscription {
        return create(context)
            .contents(contentsPath, "")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}