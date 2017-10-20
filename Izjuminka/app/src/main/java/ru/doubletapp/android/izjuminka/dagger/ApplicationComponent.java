package ru.doubletapp.android.izjuminka.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hash on 20/10/2017.
 */

@Singleton
@Component(modules = {AndroidApplicationModule.class, RetrofitModule.class})
public interface ApplicationComponent {

}
