package ru.doubletapp.android.izjuminka.presenter.login;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;
import ru.doubletapp.android.izjuminka.utils.StringUtils;
import ru.doubletapp.android.izjuminka.view.login.LoginInfoFragment;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginInfoPresenter extends BasePresenter<LoginInfoFragment> {

    @Inject
    AuthLocalData mData;

    @Override
    protected void onViewAttached(@NonNull LoginInfoFragment view) {
        super.onViewAttached(view);
        IzjuminkaApplication.getApplicationComponent(view.getContext())
                .inject(this);
        if (!StringUtils.isNullOrEmpty(mData.getEmail())) {
            view.setEmailText(mData.getEmail());
        }
    }

    public void onSendClick(String email, String phone) {
        if (!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(phone)) {
            mData.setEmail(email);
            mData.setPhone(phone);
            if (mView != null) mView.goToMainScreen();
        } else if (phone.length() != 10) {
            if (mView != null) mView.showError(mView.getString(R.string.info_invalid_phone));
        } else {
            if (mView != null) mView.showError(mView.getString(R.string.info_empty_fields));
        }
    }

    public void onSkipClick() {
        mData.setEmail("");
        mData.setPhone("");
        if (mView != null) mView.goToMainScreen();
    }
}
