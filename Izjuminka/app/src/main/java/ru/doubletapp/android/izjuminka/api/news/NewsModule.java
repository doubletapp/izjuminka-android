package ru.doubletapp.android.izjuminka.api.news;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

@Module
public class NewsModule {

    @NonNull
    @Singleton
    @Provides
    NewsInteractor provideNewsInteractor(@NonNull NewsRetrofit newsRetrofit) {
        return new NewsInteractor(newsRetrofit);
    }

    @NonNull
    @Singleton
    @Provides
    NewsRetrofit provideNewsRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(NewsRetrofit.class);
    }
}
