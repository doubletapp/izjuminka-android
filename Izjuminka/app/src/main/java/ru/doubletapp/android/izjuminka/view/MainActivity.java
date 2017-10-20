package ru.doubletapp.android.izjuminka.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.doubletapp.android.izjuminka.R;

public class MainActivity extends BaseActivity {

    public static Intent createStartIntent(Context fromContext) {
        return new Intent(fromContext, MainActivity.class);
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
