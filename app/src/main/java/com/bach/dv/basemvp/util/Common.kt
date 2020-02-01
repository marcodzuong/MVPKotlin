package com.bach.dv.basemvp.util

import java.text.SimpleDateFormat
import java.util.*

class Common {
    companion object {
        fun convertStringToDate(strTime: String, format: String): Date? {

            val dateFormat = SimpleDateFormat(format)
            var date: Date? = null
            try {
                date = dateFormat.parse(strTime)
            } catch (e: Exception) {
                // e.printStackTrace();
            }

            return date
        }
    }
}