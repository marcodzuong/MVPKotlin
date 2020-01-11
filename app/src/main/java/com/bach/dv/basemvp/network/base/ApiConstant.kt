package com.bach.dv.basemvp.network.base

interface ApiConstant {
    companion object {
        val SUCCESS_CODE: Any = 200
        const val TOKEN = "Authorization"
        const val DEVICE_ID = "Device-Id"
        const val APP_VERSION = "Current-App-Version"
        const val BEARER = "Bearer "
    }
}