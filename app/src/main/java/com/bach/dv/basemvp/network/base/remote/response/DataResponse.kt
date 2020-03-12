package com.bach.dv.basemvp.network.base.remote.response

import com.google.gson.annotations.SerializedName

open class DataResponse {
    @SerializedName("status_code")
    var statusCode: Int = 0
    @SerializedName("status")
    lateinit var status: String

}