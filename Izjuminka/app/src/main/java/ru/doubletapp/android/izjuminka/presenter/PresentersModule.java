package ru.doubletapp.android.izjuminka.presenter;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.android.izjuminka.api.profile.ProfileInteractor;
import ru.doubletapp.android.izjuminka.api.news.NewsInteractor;
import ru.doubletapp.android.izjuminka.api.settings.EditInteractor;
import ru.doubletapp.android.izjuminka.presenter.login.LoginInfoPresenter;
import ru.doubletapp.android.izjuminka.presenter.login.LoginPresenter;
import ru.doubletapp.android.izjuminka.presenter.news.AddNewsPresenter;
import ru.doubletapp.android.izjuminka.presenter.news.NewsPresenter;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;

/**
 * Created by hash on 20/10/2017.
 */

@Module
public class PresentersModule {
    @NonNull
    @Provides
    ProfilePresenter provideProfilePresenter(@NonNull ProfileInteractor profileInteractor) {
        return new ProfilePresenter(profileInteractor);
    }

    @NonNull
    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @NonNull
    @Provides
    LoginInfoPresenter provideLoginInfoPresenter() {
        return new LoginInfoPresenter();
    }

    @NonNull
    @Provides
    NewsPresenter provideNewsPresenter(@NonNull NewsInteractor newsInteractor) {
        return new NewsPresenter(newsInteractor);
    }

    @NonNull
    @Provides
    SettingsPresenter provideSettingsPresenter(@NonNull AuthLocalData authLocalData,
                                               @NonNull EditInteractor editInteractor) {
        return new SettingsPresenter(authLocalData, editInteractor);
    }

    @NonNull
    @Provides
    AddNewsPresenter provideAddNewsPresenter() {
        return new AddNewsPresenter();
    }
}
