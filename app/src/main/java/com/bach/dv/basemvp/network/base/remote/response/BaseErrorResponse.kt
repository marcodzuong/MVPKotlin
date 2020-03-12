package com.bach.dv.basemvp.network.base.remote.response

class BaseErrorResponse {
    var errors: ArrayList<Error>? = null
    fun getErrorCode(): Int? {
        return errors?.get(0)?.code
    }

    fun getErrorMessage(): String? {
        return errors?.get(0)?.message
    }

    fun setError(code: Int, message: String) {
        errors = ArrayList()
        val error = Error()
        error.code = code
        error.message = message
        errors!!.add(Error())
    }


    class Error {
        var code: Int? = null
        var message: String? = null
    }

}


