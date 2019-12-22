package com.bach.dv.basemvp.base

/**
Created by duongvanbach.hust@gmail.com on 18/12/2019
 */
open class BasePresenter<V : IView> : IPresenter<V> {
    var mMvpView: V? = null
        private set

    override fun onAttachView(mvpView: V) {
        this.mMvpView = mvpView
    }

    override fun onDetach() {
    }

    override fun onSubscribe() {
    }

    override fun onUnSubscribe() {
    }

    val isViewAttached: Boolean
        get() = mMvpView != null

    fun getMvpView(): V? {
        return this.mMvpView
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw ViewNotAttachedException()
    }

    //internal 修饰符是指成员的可见性是只在同一个模块(module)中才可见的
    private class ViewNotAttachedException internal constructor() :
        RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

}