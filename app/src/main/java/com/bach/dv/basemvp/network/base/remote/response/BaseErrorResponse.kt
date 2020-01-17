package com.bach.dv.basemvp.network.base.remote.response

class BaseErrorResponse {

    var errors: MutableList<Error>? = null
    fun getErrorCode(): String? {
        return errors?.get(0)?.code
    }

    fun getErrorMessage(): String? {
        return errors?.get(0)?.message
    }

    fun setError(code: String, message: String) {
        errors = mutableListOf()
        var error = Error()
        error.code = code
        error.message = message
        errors!!.add(Error())
    }


    companion object {
        class Error {
            var code: String? = null
            var message: String? = null
        }
    }

}


