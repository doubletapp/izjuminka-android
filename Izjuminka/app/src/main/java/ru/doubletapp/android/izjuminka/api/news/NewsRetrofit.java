package ru.doubletapp.android.izjuminka.api.news;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    @NonNull
    @Multipart
    @POST("upload_image/")
    Call<ImageResponse> loadPhoto(@Part("filename") RequestBody filename,
                                  @Part MultipartBody.Part file);

    @NonNull
    @POST("my_post/")
    Call<PostRequest> postNews(@Body PostRequest request);

}
