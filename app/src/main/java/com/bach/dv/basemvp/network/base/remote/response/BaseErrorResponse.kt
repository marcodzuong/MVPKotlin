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
        errors!!.add(Error())

    }

    class Error {
        var code: String? = null
        var message: String? = null
    }

    companion object {
        class Error {
            var code: String? = null
            var message: String? = null
        }
    }
}


