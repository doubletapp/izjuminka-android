package ru.doubletapp.android.izjuminka.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.presenter.login.LoginPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;
import ru.doubletapp.android.izjuminka.view.MainActivity;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> {

    public static final String TAG = LoginFragment.class.getSimpleName();

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    protected LoginPresenter createPresenter() {
        return IzjuminkaApplication
                .getApplicationComponent(getContext())
                .getLoginPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IzjuminkaApplication.getApplicationComponent(getContext())
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this, savedInstanceState);
        baseCallback.setTitle(getString(R.string.login_title));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem next = menu.add(getString(R.string.next));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.login_vk_btn)
    public void onVkLoginClick() {
        mPresenter.onVkLoginClick();
    }

    @OnClick(R.id.login_anon_btn)
    public void onAnonLoginClick() {
        mPresenter.onAnonLoginClick();
    }

    public void openNextScreen() {
        getActivity().startActivity(MainActivity.createStartIntent((LoginActivity) getActivity()));
    }
}
