package com.bach.dv.basemvp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

/**
Created by duongvanbach.hust@gmail.com on 18/12/2019
 */
abstract class BaseActivity<P> : AppCompatActivity() {
    var mPresenter: P? = null
    private var mUnbinder: Unbinder? = null
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        if (mUnbinder == null) {
            mUnbinder = ButterKnife.bind(this)
        }
        initData()
    }


    abstract fun initData()

    override fun onDestroy() {
        mUnbinder?.unbind()
        super.onDestroy()
    }
}