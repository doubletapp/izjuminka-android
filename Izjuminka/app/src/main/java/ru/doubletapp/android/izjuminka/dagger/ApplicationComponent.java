package ru.doubletapp.android.izjuminka.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.doubletapp.android.izjuminka.presenter.login.LoginPresenter;
import ru.doubletapp.android.izjuminka.view.login.LoginFragment;

import android.support.annotation.NonNull;

/**
 * Created by hash on 20/10/2017.
 */

@Singleton
@Component(modules = {AndroidApplicationModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    void inject(LoginFragment loginFragment);

    @NonNull
    LoginPresenter getLoginPresenter();
}
