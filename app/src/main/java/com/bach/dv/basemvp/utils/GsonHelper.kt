package com.bach.dv.basemvp.utils

import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.lang.reflect.Type
import java.util.*

class GsonHelper {
    companion object {
        private var hasGson: Boolean = false
        private var gson: Gson? = null
        fun hasGson(): Boolean? {
            if (hasGson == null) {
                try {
                    Class.forName("com.google.gson.Gson")
                    hasGson = true
                } catch (e: Exception) {
                    hasGson = false
                }
            }
            return hasGson
        }

        fun getInstance(): Gson {
            if (gson == null) {
                gson = Gson()
            }
            return gson as Gson
        }



        fun <T> fromJson(str: String?, type: Type): T? {
            try {
                return if (str == null) null else getInstance().fromJson(str, type) as T
            } catch (e: Exception) {
                Log.e("GsonHelper", "Cannot parse JSON to object", e)
                return null
            }

        }
    }

    fun toJson(objects: Objects): String? {
        try {
            return if (objects == null) null else getInstance().toJson(objects)
        } catch (e: Exception) {
            Log.e("GsonHelper", "Cannot convert object to JSON", e)
            return null
        }
    }
}