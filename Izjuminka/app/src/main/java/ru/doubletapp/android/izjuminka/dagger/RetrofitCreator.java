package ru.doubletapp.android.izjuminka.dagger;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hash on 20/10/2017.
 */

public class RetrofitCreator {

    private static final String BASE_URL = "http://vk.com";
    private static final int TIMEOUT_SECONDS = 30;

    @NonNull
    Retrofit createRetrofit(@NonNull Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        okHttpBuilder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        builder.client(okHttpBuilder.build());
        return builder.build();
    }


}
