package ru.doubletapp.android.izjuminka.api.profile;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hash on 21/10/2017.
 */

public interface ProfileRetrofit {

    @NonNull
    @GET("my_post/")
    Call<MyPostsResponse> getMyPosts();

}
