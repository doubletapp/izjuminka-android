package ru.doubletapp.android.izjuminka.dagger;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hash on 20/10/2017.
 */

@Module
public class RetrofitModule {

    @NonNull
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return new RetrofitCreator().createRetrofit(gson);
    }

}
