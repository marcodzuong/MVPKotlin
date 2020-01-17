package com.bach.dv.basemvp.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.data.cached.UserCached
import com.bach.dv.basemvp.ui.base.BaseActivity
import com.bach.dv.basemvp.ui.main.MainActivity
import com.bach.dv.basemvp.util.toastSuccess
import com.bach.dv.basemvp.view.OnSingleClickListener

class LoginActivity : BaseActivity<LoginPresenter>(), ILoginView {
    @BindView(R.id.tvLogin)
    lateinit var tvLogin: TextView
    override val layoutId: Int
        get() = R.layout.activity_login

    init {
        mPresenter = LoginPresenter()
        mPresenter?.onAttachView(this)
    }
    override fun initData() {
        tvLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                toastSuccess("Dang nhap thanh cong.")
                UserCached.getInstance()?.saveUser("123", "duongvanbach")
                MainActivity.start(this@LoginActivity)
                finish()
            }
        })


    }

    companion object {
        fun start(context: Context?) {
            val starter = Intent(context, LoginActivity::class.java)
            context?.startActivity(starter)
        }

    }

}
