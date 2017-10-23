package ru.doubletapp.android.izjuminka.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.callbacks.BaseCallback;
import ru.doubletapp.android.izjuminka.callbacks.LoginActivityCallback;
import ru.doubletapp.android.izjuminka.utils.ActivityUtils;
import ru.doubletapp.android.izjuminka.view.BaseActivity;
import ru.doubletapp.android.izjuminka.view.MainActivity;
import ru.doubletapp.android.izjuminka.view.SplashActivity;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginActivity extends BaseActivity implements LoginActivityCallback {

    @BindView(R.id.login_toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_content_container)
    FrameLayout container;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActivityUtils.replaceFragment(getSupportFragmentManager(), container.getId(),
                LoginFragment.newInstance(), LoginFragment.TAG, false, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(container.getId());
        if (fragment instanceof LoginInfoFragment) {
            MenuItem skip = menu.add(getString(R.string.skip));
            skip.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            skip.setOnMenuItemClickListener(item -> {
                ((LoginInfoFragment) fragment).onSkipClick();
                return true;
            });
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().findFragmentById(container.getId()) instanceof LoginFragment) {
            getSupportFragmentManager().findFragmentById(container.getId()).onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void openLoginInfoScreen() {
        ActivityUtils.replaceFragment(getSupportFragmentManager(), container.getId(), LoginInfoFragment.newInstance(),
                LoginInfoFragment.TAG, false, null);
        invalidateOptionsMenu();
    }

    @Override
    public void openMainScreen() {
        startActivity(MainActivity.createStartIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {
        invalidateOptionsMenu();
        finish();
    }
}
