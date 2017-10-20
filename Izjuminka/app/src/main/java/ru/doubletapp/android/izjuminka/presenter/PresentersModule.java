package ru.doubletapp.android.izjuminka.presenter;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.doubletapp.android.izjuminka.presenter.login.LoginInfoPresenter;
import ru.doubletapp.android.izjuminka.presenter.login.LoginPresenter;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;

/**
 * Created by hash on 20/10/2017.
 */

@Module
public class PresentersModule {
    @NonNull
    @Provides
    ProfilePresenter provideProfilePresenter() {
        return new ProfilePresenter();
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
}
