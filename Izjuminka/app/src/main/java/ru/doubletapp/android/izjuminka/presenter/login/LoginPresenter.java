package ru.doubletapp.android.izjuminka.presenter.login;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.login.LoginFragment;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginPresenter extends BasePresenter<LoginFragment> {

    @Override
    protected void onViewAttached(@NonNull LoginFragment view) {
        super.onViewAttached(view);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onVkLoginClick() {
        if (mView != null) {
            VKSdk.login(mView.getActivity(), VKScope.STATS);
        }
    }
}
