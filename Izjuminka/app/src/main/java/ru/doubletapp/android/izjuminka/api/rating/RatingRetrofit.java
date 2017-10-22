package ru.doubletapp.android.izjuminka.api.rating;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public interface RatingRetrofit {

    @NonNull
    @GET("top_users/")
    Call<RatingResponse> getRating();
}
