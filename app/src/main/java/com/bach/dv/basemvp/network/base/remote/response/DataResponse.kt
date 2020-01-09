package com.bach.dv.basemvp.network.base.remote.response

import com.google.gson.annotations.SerializedName

open class DataResponse {
    @SerializedName("")
    var status_code: String? = null
    @SerializedName("status")
    var status: String? = null

}