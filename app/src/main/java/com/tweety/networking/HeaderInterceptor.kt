package com.tweety.networking

import com.tweety.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {

        val request = chain?.request()?.newBuilder()
                ?.addHeader("Authorization", "bearer AAAAAAAAAAAAAAAAAAAAAGWkNQAAAAAAdHlRtqtVWZ0FVYr9agUwaNCYq5Q%3DpHcvjImegsDd9CXRpDg97G7NW1SYCHhRrr3ZtMfpzE5H9Z1xr8")
                ?.header("Content-Type", "application/json")
                ?.removeHeader("Pragma")
                ?.header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                ?.build()

        return chain?.proceed(request)!!
    }
}