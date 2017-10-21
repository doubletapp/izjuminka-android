package ru.doubletapp.android.izjuminka.dagger;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.doubletapp.android.izjuminka.api.profile.ProfileModule;
import ru.doubletapp.android.izjuminka.presenter.PresentersModule;
import ru.doubletapp.android.izjuminka.presenter.login.LoginInfoPresenter;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;
import ru.doubletapp.android.izjuminka.storage.StorageModule;
import ru.doubletapp.android.izjuminka.view.SplashActivity;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;
import ru.doubletapp.android.izjuminka.presenter.login.LoginPresenter;
import ru.doubletapp.android.izjuminka.view.login.LoginFragment;

import android.support.annotation.NonNull;

/**
 * Created by hash on 20/10/2017.
 */

@SuppressWarnings("NullableProblems")
@Singleton
@Component(modules = {AndroidApplicationModule.class, RetrofitModule.class, PresentersModule.class, StorageModule.class,
        ProfileModule.class})
public interface ApplicationComponent {

    @NonNull
    ProfilePresenter getProfilePresenter();

    void inject(ProfileFragment profileFragment);

    void inject(LoginFragment loginFragment);

    @NonNull
    LoginPresenter getLoginPresenter();

    void inject(LoginPresenter presenter);

    void inject(SplashActivity activity);

    @NonNull
    LoginInfoPresenter getLoginInfoPresenter();

    void inject(LoginInfoPresenter presenter);
}
