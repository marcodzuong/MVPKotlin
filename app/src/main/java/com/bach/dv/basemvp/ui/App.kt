package com.bach.dv.basemvp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun getAppContext(): Context? {
            return App.context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}