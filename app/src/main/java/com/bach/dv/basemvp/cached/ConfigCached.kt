package com.bach.dv.basemvp.cached

import android.annotation.SuppressLint
import com.bach.dv.basemvp.utils.SharedPrefsUtils
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ConfigCached : SharedPrefsUtils() {
    override val getKeyShare: String
        get() = "config_cached"

    companion object{
        val KEY_LIST_ACCOUNT = "list_account"
        @SuppressLint("StaticFieldLeak")
        private var configCached: ConfigCached? = null
        fun getConfigCached(): ConfigCached? {
            if (configCached == null) {
                configCached = ConfigCached()
            }
            return configCached
        }
    }



}