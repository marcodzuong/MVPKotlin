package com.bach.dv.basemvp.data.cached

import com.bach.dv.basemvp.util.SharedPrefsUtils

class UserSharedPrefUtils : SharedPrefsUtils() {
    override val getKeyShare: String
        get() = "User_cached"
}