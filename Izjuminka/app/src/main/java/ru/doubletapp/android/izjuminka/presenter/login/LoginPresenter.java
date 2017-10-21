package ru.doubletapp.android.izjuminka.presenter.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import javax.inject.Inject;

import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.api.authorization.AuthInteractor;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;
import ru.doubletapp.android.izjuminka.view.login.LoginFragment;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginPresenter extends BasePresenter<LoginFragment> {

    @Inject
    AuthLocalData mData;

    @Inject
    AuthInteractor mAuthInteractor;

    @Override
    protected void onViewAttached(@NonNull LoginFragment view) {
        super.onViewAttached(view);
        if (mView != null) {
            IzjuminkaApplication.getApplicationComponent(mView.getContext())
                    .inject(this);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mData.setUserId(res.userId);
                mData.setVkToken(res.accessToken);
                mData.setEmail(res.email);
                mAuthInteractor.auth(res.userId, res.accessToken, new AuthInteractor.AuthCallback() {
                    @Override
                    public void onSuccessfullAuth() {
                        if (mView != null) mView.openNextScreen();
                    }

                    @Override
                    public void onAuthFailed(Throwable t) {
                        if (mView != null) mView.openNextScreen();
                    }
                });
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                if (mView != null) mView.showError(error.errorMessage);
            }
        });
    }

    public void onVkLoginClick() {
        if (mView != null) {
            VKSdk.login(mView.getActivity(), VKScope.STATS, VKScope.EMAIL);
        }
    }

    public void onAnonLoginClick() {
        // TODO: 20.10.2017 go to paper post
    }
}
