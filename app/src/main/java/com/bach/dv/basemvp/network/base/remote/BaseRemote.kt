package com.bach.dv.basemvp.network.base.remote

import com.bach.dv.basemvp.BuildConfig
import com.bach.dv.basemvp.network.base.ApiConstant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

open class BaseRemote {
    companion object {
        private val TAG = "BaseRemote: "
        private val URL = "http://develop.meety.vn:6161"
        private var okHttpClient: OkHttpClient? = null
        private var retrofitBrowse: Retrofit? = null
        private var retrofit: Retrofit? = null
        const val REQUEST_TIMEOUT: Long = 60
        protected fun <T> create(clazz: Class<T>): T? {
            return getInstance()?.create(clazz)
        }

        protected fun <T> createWithTypeAdapter(
            clazz: Class<T>,
            type: Type,
            typeAdapter: Object
        ): T? {
            var service = getInstance()?.create(clazz)
            return service
        }

        fun getInstance(type: Type, typeAdapter: Objects): Retrofit? {
            if (okHttpClient == null) {
                initOkHttp()
            }
            var gsonBuilder = GsonBuilder()
            gsonBuilder.setLenient()
            gsonBuilder.registerTypeAdapter(type, typeAdapter)
            var gson = gsonBuilder.create() as Gson
            retrofitBrowse = Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(createGsonConverter(type, typeAdapter)).build()

            return retrofitBrowse
        }

        private fun getInstance(): Retrofit? {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            if (okHttpClient == null) {
                initOkHttp()
            }
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }

        private fun initOkHttp() {
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
                //  val token = SharedPrefsUtils.getStringPreference(Constants.SharePref.KEY_TOKEN)
                //            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyOSIsInJvbGVzIjpbXSwiaWF0IjoxNTU5OTAwMDMyLCJleHAiOjE1NjA4OTAwMzJ9.s1NQGWgUObZxBeU2wipPrJIZWUD1K9b5iDLN4RFF0NU";
//                if (!TextUtils.isEmpty(token) && !original.url().toString().contains("auth/signin/social")) {
//                    val tokenMix = ApiConstant.BEARER + token
////                    Logger.i(TAG, "Token: $token")
//                    requestBuilder.addHeader(ApiConstant.TOKEN, tokenMix)
//                }

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

        fun createGsonConverter(type: Type?, typeAdapter: Objects?): Converter.Factory? {
            var gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(type, typeAdapter)
            val gson = gsonBuilder.create() as Gson
            return GsonConverterFactory.create(gson)
        }

    }
}