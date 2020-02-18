package com.bach.dv.basemvp.ui.main

import android.media.AudioManager
import android.media.MediaPlayer
import com.bach.dv.basemvp.ui.base.BasePresenter

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