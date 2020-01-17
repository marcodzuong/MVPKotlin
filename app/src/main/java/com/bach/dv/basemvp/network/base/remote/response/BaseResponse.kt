package com.bach.dv.basemvp.network.base.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> : DataResponse() {
    @Expose
    @SerializedName("data")
    var data: T? = null

}