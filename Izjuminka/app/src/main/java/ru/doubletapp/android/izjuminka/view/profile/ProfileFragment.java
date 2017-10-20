package ru.doubletapp.android.izjuminka.view.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by hash on 20/10/2017.
 */

public class ProfileFragment extends BaseFragment<ProfilePresenter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        IzjuminkaApplication.getApplicationComponent(getContext()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    private void initialize() {
        // TODO: add some init
    }

    @Nullable
    @Override
    protected ProfilePresenter createPresenter() {
        return IzjuminkaApplication
                .getApplicationComponent(getContext())
                .getProfilePresenter();
    }
}
