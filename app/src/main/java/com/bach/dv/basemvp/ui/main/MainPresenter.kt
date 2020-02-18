package com.bach.dv.basemvp.ui.main

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import com.bach.dv.basemvp.base.BasePresenter

class MainPresenter : BasePresenter<IMainView>(), IMainPresenter {
    var mediaPlayer: MediaPlayer? = null


    fun playAudioByUrl(url: String) {


        mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer?.release()
        mediaPlayer = null
    }






}