package com.ken.githubclient.net

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ken.githubclient.GithubApplication
import com.ken.githubclient.net.api.ApiService
import com.ken.githubclient.utls.Logger
import com.ken.githubclient.utls.MoshiHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    /**Cookie*/
    private val cookiePersistor = SharedPrefsCookiePersistor(GithubApplication.instance)
    private val cookieJar = PersistentCookieJar(SetCookieCache(), cookiePersistor)

    /**log**/
    private val logger = HttpLoggingInterceptor.Logger {
        Logger.i(this::class.simpleName, it)
    }
    private val logInterceptor = HttpLoggingInterceptor(logger).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**OkhttpClient*/
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .cookieJar(cookieJar)
        .addNetworkInterceptor(logInterceptor)
        .build()

    /**Retrofit*/
    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(MoshiHelper.moshi))
        .build()

    /**ApiService*/
    val apiService: ApiService = retrofit.create(ApiService::class.java)

    /**清除Cookie*/
    fun clearCookie() = cookieJar.clear()

    /**是否有Cookie*/
    fun hasCookie() = cookiePersistor.loadAll().isNotEmpty()
}