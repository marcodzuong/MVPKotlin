package com.bach.dv.basemvp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        @JvmStatic
        fun getAppContext(): Context {
            return this.context!!
        }

        fun show() {}
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}