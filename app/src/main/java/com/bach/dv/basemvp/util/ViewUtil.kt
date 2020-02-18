package com.bach.dv.basemvp.util

import android.app.Activity
import android.content.res.Resources
import com.bach.dv.basemvp.R

class ViewUtil {
    companion object {
        fun dpToPx(dp: Int): Int {
            val density = Resources.getSystem().displayMetrics.density
            return Math.round(dp * density)
        }

        fun animTopToBottom(activity: Activity?) {
            activity?.overridePendingTransition(R.anim.alway_stand, R.anim.exit_to_bottom)
        }
    }

}