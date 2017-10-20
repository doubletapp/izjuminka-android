package ru.doubletapp.android.izjuminka.dagger;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.doubletapp.android.izjuminka.presenter.PresentersModule;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;

/**
 * Created by hash on 20/10/2017.
 */

@Singleton
@Component(modules = {AndroidApplicationModule.class, RetrofitModule.class, PresentersModule.class})
public interface ApplicationComponent {

    @NonNull
    ProfilePresenter getProfilePresenter();

    void inject(ProfileFragment profileFragment);

}
