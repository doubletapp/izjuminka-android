package ru.doubletapp.android.izjuminka.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import ru.doubletapp.android.izjuminka.api.profile.ProfileInteractor;
import ru.doubletapp.android.izjuminka.api.settings.EditInteractor;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;
import ru.doubletapp.android.izjuminka.view.login.LoginActivity;
import ru.doubletapp.android.izjuminka.view.settings.SettingsFragment;

/**
 * Created by hash on 21/10/2017.
 */

public class SettingsPresenter extends BasePresenter<SettingsFragment> {

    @NonNull
    AuthLocalData mAuthLocalData;

    @NonNull
    EditInteractor mEditInteractor;

    public SettingsPresenter(@NonNull AuthLocalData authLocalData,
                             @NonNull EditInteractor editInteractor) {
        mAuthLocalData = authLocalData;
        mEditInteractor = editInteractor;
    }

    @Override
    protected void onViewAttached(@NonNull SettingsFragment view) {
        view.setEmail(mAuthLocalData.getEmail());
        view.setPhone(mAuthLocalData.getPhone());
    }

    public void editProfile(@NonNull String email, @NonNull String phone) {
        mEditInteractor.editProfile(email, phone, new EditInteractor.EditProfileListener() {
            @Override
            public void onSuccessfullEdit() {
                mAuthLocalData.setEmail(email);
                mAuthLocalData.setPhone(phone);
                mView.getActivity().onBackPressed();
            }

            @Override
            public void onEditFailed() {
                mView.getActivity().onBackPressed();
            }
        });
    }


    public void logout() {
        mAuthLocalData.logout();
        LoginActivity.start(mView.getContext());
    }
}
