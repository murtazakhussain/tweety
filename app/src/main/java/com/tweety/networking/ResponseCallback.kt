package com.tweety.networking

import org.json.JSONObject

import java.io.IOException
import java.net.SocketTimeoutException

import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException

/**
 * Created by murtaza on 25/01/2018.
 */
abstract class ResponseCallback<T> : DisposableObserver<T>() {

    protected abstract fun onSuccess(t: T)

    protected abstract fun onError(error: NetworkError)

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val error = NetworkError(e)
        if (e is HttpException) {
            val responseBody = e.response().errorBody()
            //view.onUnknownError(getErrorMessage(responseBody));
        } else if (e is SocketTimeoutException) {
            // view.onTimeout();
        } else if (e is IOException) {
            // view.onNetworkError();
        } else if (error.isAuthFailure) {
            // view.onNetworkError();
        } else {
            // view.onUnknownError(e.getMessage());
        }
        onError(error)
    }

    override fun onComplete() {

    }

    private fun getErrorMessage(responseBody: ResponseBody): String? {
        try {
            val jsonObject = JSONObject(responseBody.string())
            return jsonObject.getString("message")
        } catch (e: Exception) {
            return e?.message
        }

    }
}