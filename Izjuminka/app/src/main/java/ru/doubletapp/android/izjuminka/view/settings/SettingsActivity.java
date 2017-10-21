package ru.doubletapp.android.izjuminka.view.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.view.BaseActivity;

/**
 * Created by hash on 21/10/2017.
 */

public class SettingsActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, SettingsFragment.newInstance())
                    .commit();
        }

    }
}
