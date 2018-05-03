package com.tweety.networking

import com.tweety.code.models.Tweets
import com.tweety.code.responses.TweetsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ServiceClass(private val networkService: NetworkService) {

    fun getTweetsOfHandle(twitterHandle: String): Single<List<Tweets>> {
        return networkService.getTweetsOf(twitterHandle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }
}
