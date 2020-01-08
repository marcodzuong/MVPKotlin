package com.bach.dv.basemvp.network.base.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BaseRemote {
    companion object {
        private var okHttpClient: OkHttpClient? = null
        const val REQUEST_TIMEOUT: Long = 20
        fun initOKHttp() {
            var httpClient =
                OkHttpClient.Builder().connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            var httpLoggingInterceptor: HttpLoggingInterceptor? = null
        }
    }
}