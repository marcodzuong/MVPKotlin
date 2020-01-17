package com.bach.dv.basemvp.ui.splash

import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.ui.login.LoginActivity

class SplashActivity : BaseActivity<SplashPresenter>(), ISplashView {

    override fun goToContinue() {
        LoginActivity.start(this)
        finish()
    }

    init {
        mPresenter = SplashPresenter()
        mPresenter?.onAttachView(this)
    }

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun initData() {
        mPresenter?.postDelay()
    }


}
