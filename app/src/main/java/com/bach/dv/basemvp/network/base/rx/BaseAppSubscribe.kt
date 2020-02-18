package com.bach.dv.basemvp.network.base.rx

import com.bach.dv.basemvp.network.cached.APISharedPrefUtils
import com.bach.dv.basemvp.ui.base.BaseView
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference
import java.lang.reflect.Type


class BaseAppSubscribe<T>() : DisposableObserver<T>() {
    private var cached: String? = null
    private var objectResponse: T? = null
    private var weakReference: WeakReference<BaseView>? = null
    private var keyCached: String? = null
    private var objectCached: T? = null
    private var loadFirst = false
    private var skipResponseServer = false

    constructor(keyCached: String?) : this() {
        this.keyCached = keyCached
    }

    constructor(keyCached: String?, loadFirst: Boolean) : this() {
        this.keyCached = keyCached
        this.loadFirst = loadFirst
    }

    constructor(keyCached: String?, loadFirst: Boolean, skipResponseServer: Boolean) : this() {
        this.keyCached = keyCached
        this.loadFirst = loadFirst
        this.skipResponseServer = skipResponseServer
    }

    constructor(view: BaseView?) : this() {
        this.weakReference = WeakReference<BaseView>(view)
//        this.tryRequest = TryRequest()
    }

    fun checkToCached(keyCached: String, loadFirst: Boolean) {
        this.keyCached = keyCached
        if (loadFirst) onAfterCache()
        cached = APISharedPrefUtils.getStringPreference(keyCached)
        when {
            cached != null && getType() != null -> {

            }
        }
    }

    private fun getType(): Type? {
        return null
    }

    private fun onAfterCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(t: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}