package ru.doubletapp.android.izjuminka.api.news;

import retrofit2.Call;
import retrofit2.http.*;

import android.support.annotation.NonNull;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public interface NewsRetrofit {

    @NonNull
    @GET("news/")
    Call<NewsResponse> getNews(@Query("offset") int offset,
                               @Query("count") int count);

}
