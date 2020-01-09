package com.bach.dv.basemvp.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.ui.main.MainActivity
import com.bach.dv.basemvp.util.toastSuccess
import com.bach.dv.basemvp.view.OnSingleClickListener

class LoginActivity : BaseActivity<LoginPresenter>(), ILoginView {
    @BindView(R.id.tvLogin)
    lateinit var tvLogin: TextView
    override val layoutId: Int
        get() = R.layout.activity_login


    override fun initData() {
        onAttachedView()
        tvLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                toastSuccess("Dang nhap thanh cong.")
                MainActivity.start(this@LoginActivity)
            }
        })


    }

    private fun onAttachedView() {
        mPresenter = LoginPresenter()
        mPresenter?.onAttachView(this)
    }

    companion object {
        fun start(context: Context?) {
            val starter = Intent(context, LoginActivity::class.java)
            context?.startActivity(starter)
        }

    }

}
