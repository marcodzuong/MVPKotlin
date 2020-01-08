package com.bach.dv.basemvp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder

/**
Created by duongvanbach.hust@gmail.com on 18/12/2019
 */
abstract class BaseFragment<C> : Fragment() {
    var mPresenter: C? = null
    private var mUnbinder: Unbinder? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(getLayoutId, container, false)
        if (mUnbinder == null) {
            mUnbinder = ButterKnife.bind(this, view)
        }
        updateContentView()
        return view
    }

    abstract val getLayoutId: Int
    abstract fun updateContentView()
    override fun onDestroy() {
        mUnbinder?.unbind()
        super.onDestroy()
    }
}