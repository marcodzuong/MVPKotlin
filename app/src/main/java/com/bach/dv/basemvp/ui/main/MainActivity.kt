package com.bach.dv.basemvp.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.widget.TextView
import butterknife.BindView
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.data.cached.UserCached
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.util.toastNormal

class MainActivity() : BaseActivity<MainPresenter?>(), IMainView {
    override fun showToast() {
    }

    @BindView(R.id.tvMain)
    lateinit var tvMain: TextView
    override val layoutId: Int
        get() = R.layout.activity_main

    init {
        mPresenter = MainPresenter()
        mPresenter!!.onAttachView(this)
    }

    override fun initData() {
        onAttachedView()
        tvMain.text = UserCached.getInstance()?.getUser("123")
        mPresenter?.getData()
    }

    private fun onAttachedView() {
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    var count = 0
    override fun onBackPressed() {
        count++
        handlerClickBack()
    }

    private fun handlerClickBack() {
        when (count) {
            1 -> toastNormal(Resources.getSystem().getString(R.string.click_more_to_off_app))
            2 -> super.onBackPressed()
        }
        android.os.Handler().postDelayed({ count = 0 }, 600)
    }

}
