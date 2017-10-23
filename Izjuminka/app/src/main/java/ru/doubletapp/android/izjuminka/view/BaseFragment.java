package ru.doubletapp.android.izjuminka.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import butterknife.Unbinder;
import icepick.Icepick;
import ru.doubletapp.android.izjuminka.callbacks.BaseCallback;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;

/**
 * Created by hash on 20/10/2017.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private static final String STATE_PRESENTER_STATE = "DefaultFragment.STATE_PRESENTER_STATE";

    protected T mPresenter;
    public BaseCallback baseCallback;

    @Nullable
    protected Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            baseCallback = (BaseCallback) context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            Bundle bundle = savedInstanceState == null ? null : savedInstanceState.getBundle(STATE_PRESENTER_STATE);
            mPresenter.attachView(this, bundle);
        }
    }

    @Nullable
    protected T createPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
