package ru.doubletapp.android.izjuminka.dagger;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;

/**
 * Created by hash on 20/10/2017.
 */

public class RetrofitCreator {

    private static final String BASE_URL = "http://api.izjuminka.media/";
    private static final int TIMEOUT_SECONDS = 30;

    @NonNull
    Retrofit createRetrofit(@NonNull AuthLocalData authHolder, @NonNull Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            String credentials = "";
            if (authHolder.isLoggedIn()) {
                credentials = Credentials.basic(authHolder.getUserId(), authHolder.getToken());
            } else {
                credentials = Credentials.basic("admin", "6cceb3f9721e4baab54e95ca8cb196e1");
            }
            requestBuilder.addHeader("Authorization", credentials);
            request = requestBuilder.build();
            return chain.proceed(request);
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        okHttpBuilder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        builder.client(okHttpBuilder.build());
        return builder.build();
    }


}
