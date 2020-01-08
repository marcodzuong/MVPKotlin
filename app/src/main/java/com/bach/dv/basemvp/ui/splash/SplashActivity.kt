package com.bach.dv.basemvp.ui.splash

import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.ui.login.LoginActivity

class SplashActivity : BaseActivity<SplashPresenter>(), ISplashView {

    override fun goToContinue() {
        LoginActivity.start(this)
    }

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun initData() {
        onAttachView()
        mPresenter?.postDelay()
    }

    private fun onAttachView() {
        mPresenter = SplashPresenter()
        mPresenter?.onAttachView(this)
    }


}
