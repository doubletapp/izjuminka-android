package ru.doubletapp.android.izjuminka.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.utils.ActivityUtils;
import ru.doubletapp.android.izjuminka.view.BaseActivity;
import ru.doubletapp.android.izjuminka.view.SplashActivity;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_content_container)
    FrameLayout container;

    public static Intent createStartIntent(Context fromContext) {
        return new Intent(fromContext, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUnbinder = ButterKnife.bind(this);

        init();
    }

    private void init() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActivityUtils.replaceFragment(getSupportFragmentManager(), container.getId(),
                LoginFragment.newInstance(), LoginFragment.TAG, true, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().findFragmentById(container.getId()) instanceof LoginFragment) {
            getSupportFragmentManager().findFragmentById(container.getId()).onActivityResult(requestCode, resultCode, data);
        }
    }
}
