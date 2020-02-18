package com.bach.dv.basemvp.ui.base
/**
Created by duongvanbach.hust@gmail.com on 18/12/2019
 */
interface IPresenter<in V : IView> {
    fun onAttachView(mvpView: V)
    fun onDetach()
    fun onSubscribe()
    fun onUnSubscribe()
}