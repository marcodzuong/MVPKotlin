package com.bach.dv.basemvp.ui.main

import com.bach.dv.basemvp.ui.base.BasePresenter

class MainPresenter :BasePresenter<IMainView>(), IMainPresenter{
    fun getData() {
        getMvpView()?.showToast()
    }
}