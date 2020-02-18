package com.bach.dv.basemvp.ui.home


import androidx.fragment.app.Fragment
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.ui.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<HomePresenter>(), IHomeView {

    init {
        mPresenter = HomePresenter()
        mPresenter?.onAttachView(this)
    }

    override val getLayoutId: Int
        get() = R.layout.fragment_home

    override fun updateContentView() {
    }


}
