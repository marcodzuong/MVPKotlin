package com.bach.dv.basemvp.ui.splash

import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.base.BaseActivity
import com.bach.dv.basemvp.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashPresenter>(), ISplashView {

    override fun goToContinue() {
        MainActivity.start(this)
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
