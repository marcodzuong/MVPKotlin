package com.bach.dv.basemvp.network.base;

import android.text.TextUtils;

import com.bach.dv.basemvp.BuildConfig;
import com.bach.dv.basemvp.util.DeviceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRemote {
    private static final String TAG = "BaseRemote: ";

    private static Retrofit retrofit = null;

    private static Retrofit retrofitBrowse = null;

    private static int REQUEST_TIMEOUT = 20;

    private static OkHttpClient okHttpClient;

    private static Retrofit getInstance(Type type, Object typeAdapter) {

        if (okHttpClient == null) {
            initOkHttp();
        }

//        if (retrofitBrowse == null) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.setLenient();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);

        Gson gson = gsonBuilder.create();
        retrofitBrowse = new Retrofit.Builder()
//                .baseUrl(BuildConfig.STAGING_BASE_DOMAIN)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
//        }
        return retrofitBrowse;
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (okHttpClient == null) {
            initOkHttp();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .baseUrl(BuildConfig.STAGING_BASE_DOMAIN)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    private static void initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = getHttpLoggingInterceptor();
        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "*/*")
                    .addHeader("Content-Type", "application/json");

//             Adding Authorization token (API Key)
//             Requests will be denied without API key
//            String token = SharedPrefsUtils.getStringPreference(Constants.SharePref.KEY_TOKEN);
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyOSIsInJvbGVzIjpbXSwiaWF0IjoxNTU5OTAwMDMyLCJleHAiOjE1NjA4OTAwMzJ9.s1NQGWgUObZxBeU2wipPrJIZWUD1K9b5iDLN4RFF0NU";
            if (!TextUtils.isEmpty(token) && !original.url().toString().contains("auth/signin/social")) {
                String tokenMix = ApiConstant.BEARER + token;
                requestBuilder.addHeader(ApiConstant.TOKEN, tokenMix);
            }

            // add device ID.
            String deviceId = DeviceUtils.getAndroidID();
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = "";
            }
            requestBuilder.addHeader(ApiConstant.DEVICE_ID, deviceId);
            requestBuilder.addHeader(ApiConstant.APP_VERSION, BuildConfig.VERSION_CODE + "");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        okHttpClient = httpClient.build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    protected <T> T create(Class<T> clazz) {
        T service = getInstance().create(clazz);
        return service;
    }

    protected <T> T createWithTypeAdapter(Class<T> clazz, Type type, Object typeAdapter) {
        T service = getInstance(type, typeAdapter).create(clazz);
        return service;
    }

}
