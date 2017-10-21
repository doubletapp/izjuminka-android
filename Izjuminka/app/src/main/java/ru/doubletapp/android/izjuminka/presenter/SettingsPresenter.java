package ru.doubletapp.android.izjuminka.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import ru.doubletapp.android.izjuminka.storage.AuthLocalData;
import ru.doubletapp.android.izjuminka.view.settings.SettingsFragment;

/**
 * Created by hash on 21/10/2017.
 */

public class SettingsPresenter extends BasePresenter<SettingsFragment> {

    @NonNull
    AuthLocalData mAuthLocalData;

    public SettingsPresenter(@NonNull AuthLocalData authLocalData) {
        mAuthLocalData = authLocalData;
    }

    @Override
    protected void onViewAttached(@NonNull SettingsFragment view) {
        view.setEmail(mAuthLocalData.getEmail());
        view.setPhone(mAuthLocalData.getPhone());
    }
}
