package com.bach.dv.basemvp.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import com.bach.dv.basemvp.ui.main.MainActivity

class MusicService : Service() {

    var player: MediaPlayer? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player?.start()
        return 1
    }


    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
    }
}