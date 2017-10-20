package ru.doubletapp.android.izjuminka.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import ru.doubletapp.android.izjuminka.callbacks.BaseCallback;
import ru.doubletapp.android.izjuminka.utils.StringUtils;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseCallback {

    @Nullable
    protected Unbinder mUnbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }

    @Override
    public void setTitle(String title) {

    }
}
