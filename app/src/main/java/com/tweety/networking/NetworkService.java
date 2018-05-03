package com.tweety.networking;

import com.tweety.code.models.Tweets;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface NetworkService {

    @GET("https://api.twitter.com/1.1/statuses/user_timeline.json?count=15")
    Single<List<Tweets>> getTweetsOf(@Query("screen_name") String twitterHandle);
}
