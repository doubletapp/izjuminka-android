package ru.doubletapp.android.izjuminka.api.rating;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import android.support.annotation.NonNull;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

@Module
public class RatingModule {
    @NonNull
    @Singleton
    @Provides
    RatingRetrofit provideRatingRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(RatingRetrofit.class);
    }

    @NonNull
    @Singleton
    @Provides
    RatingInteractor provideRatingInteractor(@NonNull RatingRetrofit authRetrofit) {
        return new RatingInteractor(authRetrofit);
    }
}
