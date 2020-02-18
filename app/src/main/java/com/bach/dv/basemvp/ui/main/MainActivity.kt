package com.bach.dv.basemvp.ui.main

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import butterknife.BindView
import com.bach.dv.basemvp.R
import com.bach.dv.basemvp.base.BaseActivity
import com.bach.dv.basemvp.service.MusicService
import com.bach.dv.basemvp.utils.toastSuccess

class MainActivity : BaseActivity<MainPresenter?>(), IMainView {


    @BindView(R.id.btnStop)
    lateinit var btnStop: Button
    @BindView(R.id.btnStart)
    lateinit var btnStart: Button
    override val layoutId: Int
        get() = R.layout.activity_main


    override fun initData() {
        onAttachedView()
        btnStart.setOnClickListener(View.OnClickListener {
            startService(Intent(this, MusicService::class.java))
//            mPresenter?.playAudioByUrl("https://storage.googleapis.com/radio-r02/files/app_rss/rss_2000592/audio_1565684454361.mp3")
        })
        btnStop.setOnClickListener(View.OnClickListener {
            stopService(Intent(this, MusicService::class.java))
        })


    }

    private fun onAttachedView() {
        mPresenter = MainPresenter()
        mPresenter!!.onAttachView(this)
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }


    }


}
