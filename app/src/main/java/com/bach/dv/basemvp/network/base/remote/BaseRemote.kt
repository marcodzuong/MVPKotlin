package com.bach.dv.basemvp.network.base.remote

import android.text.TextUtils
import com.bach.dv.basemvp.BuildConfig
import com.bach.dv.basemvp.network.base.ApiConstant
import com.bach.dv.basemvp.ui.common.Constants
import com.bach.dv.basemvp.util.SharedPrefsUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BaseRemote {
    companion object {
        private var okHttpClient: OkHttpClient? = null
        const val REQUEST_TIMEOUT: Long = 20
        fun initOKHttp() {
            val httpClient =
                OkHttpClient.Builder().connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            val interceptor = getHttpLoggingInterceptor()
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "*/*")
                    .addHeader("Content-Type", "application/json")

                //             Adding Authorization token (API Key)
                //             Requests will be denied without API key
                val token = SharedPrefsUtils.getStringPreference(Constants.SharePref.KEY_TOKEN)
                //            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyOSIsInJvbGVzIjpbXSwiaWF0IjoxNTU5OTAwMDMyLCJleHAiOjE1NjA4OTAwMzJ9.s1NQGWgUObZxBeU2wipPrJIZWUD1K9b5iDLN4RFF0NU";
                if (!TextUtils.isEmpty(token) && !original.url().toString().contains("auth/signin/social")) {
                    val tokenMix = ApiConstant.BEARER + token
//                    Logger.i(TAG, "Token: $token")
                    requestBuilder.addHeader(ApiConstant.TOKEN, tokenMix)
                }

//                // add device ID.
//                var deviceId = DeviceUtils.getAndroidID()
//                if (TextUtils.isEmpty(deviceId)) {
//                    deviceId = ""
//                }
//                Logger.i(TAG, "deviceId: $deviceId")
//                requestBuilder.addHeader(ApiConstant.DEVICE_ID, deviceId)
                requestBuilder.addHeader(
                    ApiConstant.APP_VERSION,
                    BuildConfig.VERSION_CODE.toString() + ""
                )

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            okHttpClient = httpClient.build()

        }

        fun getHttpLoggingInterceptor(): HttpLoggingInterceptor? {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
            return interceptor
        }

    }
}