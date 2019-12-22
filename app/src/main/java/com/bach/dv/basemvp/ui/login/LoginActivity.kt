package com.bach.dv.basemvp.ui.login

import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.base.BaseActivity

class LoginActivity : BaseActivity<LoginPresenter>( ),ILoginView {
    override val layoutId: Int
        get() = R.layout.activity_login


    override fun initData() {
        onAttachedView()
        mPresenter?.loadRemoteConfig()
    }

    private fun onAttachedView() {
        mPresenter = LoginPresenter()
        mPresenter?.onAttachView(this)
    }


}
