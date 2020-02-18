package com.bach.dv.basemvp.data.cached

class UserCached {
    private val sharedPrefUtils = UserSharedPrefUtils()
    fun saveUser(key: String, value: String) {
        sharedPrefUtils.setStringPreference(key, value)
    }

    fun getUser(key: String): String? {
        return sharedPrefUtils.getStringPreference(key)
    }

    companion object {
        private var configCached: UserCached? = null
        fun getInstance(): UserCached? {
            if (configCached == null) {
                configCached = UserCached()
            }
            return configCached
        }
    }
}