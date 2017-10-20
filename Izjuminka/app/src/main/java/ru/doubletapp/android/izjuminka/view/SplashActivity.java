package ru.doubletapp.android.izjuminka.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;
import ru.doubletapp.android.izjuminka.view.login.LoginActivity;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class SplashActivity extends BaseActivity {


    @Inject
    AuthLocalData mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        IzjuminkaApplication.getApplicationComponent(this)
                .inject(this);

        init();
    }

    private void init() {
        if (!mData.getToken().isEmpty() && !mData.getUserId().isEmpty()) {
            startActivity(MainActivity.createStartIntent(this));
        } else {
            startActivity(LoginActivity.createStartIntent(this));
        }
    }
}
