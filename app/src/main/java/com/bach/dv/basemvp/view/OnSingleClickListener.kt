package com.bach.dv.basemvp.view

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {
    companion object {
        private const val MIN_CLICK_INTERVAL = 600
    }

    abstract fun onSingleClick(view: View)
    private var mLastClickTime: Long = 0
    override fun onClick(v: View?) {
        try {
            val currentClickTime = SystemClock.uptimeMillis()
            val elapsedTime = currentClickTime - mLastClickTime
            mLastClickTime = currentClickTime
            if (elapsedTime <= MIN_CLICK_INTERVAL)
                return
        } catch (e: Exception) {
            e.printStackTrace()
        }
        v?.let { onSingleClick(it) }
    }

}