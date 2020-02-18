package com.bach.dv.basemvp.network.base.remote.response

class BaseException : RuntimeException() {
    private var type: Type? = null
    private var errorResponse: BaseErrorResponse? = null


//    @Nullable
//    private val response: Response<*>?


    fun getServerErrorCode(): String? {
        return if (errorResponse != null) errorResponse!!.getErrorCode() else ""
    }

    private fun getNetworkErrorMessage(throwable: Throwable?): String {


        //
        //        // TODO update later with Japanese
        //        if (throwable instanceof SocketTimeoutException) {
        //            return throwable.getMessage();
        //        }
        //
        //        if (throwable instanceof UnknownHostException) {
        //            return throwable.getMessage();
        //        }
        //
        //        if (throwable instanceof IOException) {
        //            return throwable.getMessage();
        //        }

        return "Server error has occurred"
    }

    private fun getHttpErrorMessage(httpCode: Int): String? {
        if (httpCode in 300..308) {
            // Redirection
            return "Redirection operation has occurred"
        }
        if (httpCode in 400..451) {
            // Client error
            return "Client error has occurred"
        }
        if (httpCode in 500..511) {
            // Server error
            return "Server error has occurred"
        }
        return "Unofficial error has occurred"
    }

    enum class Type {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-2xx HTTP status code was received from the server.
         */
        HTTP,
        /**
         * A error server with code & message
         */
        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

}