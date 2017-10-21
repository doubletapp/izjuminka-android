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
    NewsRemoteRepository provideNewsRemoteRepository(@NonNull NewsRetrofit newsRetrofit) {
        return new NewsRemoteRepository(newsRetrofit);
    }

    @NonNull
    @Singleton
    @Provides
    NewsInteractor provideNewsInteractor(@NonNull NewsRemoteRepository newsRemoteRepository) {
        return new NewsInteractor(newsRemoteRepository);
    }

    @NonNull
    @Singleton
    @Provides
    NewsRetrofit provideNewsRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(NewsRetrofit.class);
    }
}
