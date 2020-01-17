package com.bach.dv.basemvp.network.base.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableManager {
    private constructor()

    companion object {
        private var compositeDisposable: CompositeDisposable? = null
        fun add(disposable: Disposable) {
            getCompositeDisposable().add(disposable)
        }

        fun dispose() {
            getCompositeDisposable().dispose()
        }

        private fun getCompositeDisposable(): CompositeDisposable {
            if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
                compositeDisposable = CompositeDisposable()
            }
            return compositeDisposable!!
        }

    }

}