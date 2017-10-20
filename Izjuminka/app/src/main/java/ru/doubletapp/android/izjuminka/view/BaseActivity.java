package ru.doubletapp.android.izjuminka.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    protected Unbinder mUnbinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }
}
