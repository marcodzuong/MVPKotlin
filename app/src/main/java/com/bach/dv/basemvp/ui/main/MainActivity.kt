package com.bach.dv.basemvp.ui.main

import android.content.Context
import android.content.Intent
import android.widget.TextView
import butterknife.BindView
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.util.toastSuccess

class MainActivity : BaseActivity<MainPresenter?>(), IMainView {
    override fun showToast() {
        tvMain.text = ""
        toastSuccess("custom thanh cong")
    }

    @BindView(R.id.tvMain)
    lateinit var tvMain: TextView
    override val layoutId: Int
        get() = R.layout.activity_main


    override fun initData() {
        onAttachedView()
        mPresenter?.getData()
    }

    private fun onAttachedView() {
        mPresenter = MainPresenter()
        mPresenter!!.onAttachView(this)
    }

    companion object {
        @JvmStatic fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

}
