package com.bach.dv.basemvp.ui.splash

import android.os.Handler
import com.bach.dv.basemvp.base.BasePresenter

class SplashPresenter : BasePresenter<ISplashView>(), ISplashPresenter {
    fun postDelay() {
        Handler().postDelayed(object : Runnable {
            override fun run() {
                getMvpView()?.goToContinue()
            }
        }, 3000)

    }
}