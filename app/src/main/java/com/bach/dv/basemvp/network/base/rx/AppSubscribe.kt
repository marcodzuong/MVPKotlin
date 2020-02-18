package com.bach.dv.basemvp.network.base.rx

import com.bach.dv.basemvp.ui.base.BaseView
import com.bach.dv.basemvp.util.GsonHelper
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference
import java.lang.reflect.Type


abstract class AppSubscribe<T> : DisposableObserver<T> {
    private var cached: String? = null
    private var objectRes: T? = null


    private var weakReference: WeakReference<BaseView>? = null

    constructor(keyCached: String?) : super() {
        checkToCached(keyCached, true)
    }

    private fun checkToCached(keyCached: String?, b: Boolean) {

    }

    override fun onComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(t: T) {
        objectRes = t
    }

    override fun onError(e: Throwable) {
        onRequestFinish()
    }


    abstract fun onSuccess(objectRes: T)
    fun getType(): Type? {
        return null
    }

    fun onSuccess(objectRes: T, isCache: Boolean) {

    }

    fun onRetry() {

    }

    fun onErrorWithCached(objectCached: T) {

    }

    fun onFirstTime() {

    }

    fun onRequestFinish() {

    }

    inner class Compare {
        var responseNew: String? = null
        var isSame: Boolean = false

        operator fun invoke(): Compare {
            try {
                responseNew = GsonHelper.getInstance().toJson(objectRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            isSame = cached != null && cached == responseNew
            return this
        }


    }
}