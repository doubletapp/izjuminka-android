package ru.doubletapp.android.izjuminka.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Unbinder;
import icepick.Icepick;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;

/**
 * Created by hash on 20/10/2017.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;

    @Nullable
    protected Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
