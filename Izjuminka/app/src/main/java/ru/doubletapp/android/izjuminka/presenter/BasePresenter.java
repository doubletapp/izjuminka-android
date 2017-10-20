package ru.doubletapp.android.izjuminka.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import icepick.Icepick;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by hash on 20/10/2017.
 */

public abstract class BasePresenter<V extends BaseFragment> {

    @Nullable
    protected V mView;

    private boolean mIsNewborn = true;

    private boolean mSaveInstanceStateCalled;

    public void attachView(@NonNull V view, @Nullable Bundle savedState) {
        mView = view;

        if (savedState != null && mIsNewborn) {
            restoreInstanceState(savedState);
        }
        mIsNewborn = false;

        onViewAttached(view);
    }

    protected void onViewAttached(@NonNull V view){}

    public void detachView() {
        onViewDetached();
        mView = null;
    }

    protected void onViewDetached() {}

    @Nullable
    final Bundle saveState() {
        mSaveInstanceStateCalled = true;
        return saveInstanceState();
    }

    protected Bundle saveInstanceState() {
        Bundle bundle = new Bundle();
        Icepick.saveInstanceState(this, bundle);
        return bundle;
    }

    protected void restoreInstanceState(@NonNull Bundle state) {
        Icepick.restoreInstanceState(this, state);
    }

    void onViewDestroyed() {
            disposePresenter();
    }

    protected void disposePresenter() {

    }

}
