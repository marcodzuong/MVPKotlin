package com.bach.dv.basemvp.ui.login

import com.bach.dv.basemvp.ui.base.BasePresenter

class LoginPresenter :BasePresenter<ILoginView>(),ILoginPresenter {
    fun loadRemoteConfig() {
        // TODO load from firebase  . . .

    }
}