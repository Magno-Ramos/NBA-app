package com.app.pagingexample.model

data class NetworkState(
    val status: Int,
    val message: String? = null
) {
    companion object {
        fun loading() = NetworkState(LOADING)
        fun success() = NetworkState(SUCCESS)
        fun error(message: String) = NetworkState(ERROR, message)

        const val LOADING = 0
        const val SUCCESS = 1
        const val ERROR = 2
    }
}