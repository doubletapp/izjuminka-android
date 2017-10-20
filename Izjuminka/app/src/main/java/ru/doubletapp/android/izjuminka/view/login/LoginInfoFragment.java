package ru.doubletapp.android.izjuminka.view.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.callbacks.LoginActivityCallback;
import ru.doubletapp.android.izjuminka.presenter.login.LoginInfoPresenter;
import ru.doubletapp.android.izjuminka.view.BaseFragment;
import ru.doubletapp.android.izjuminka.view.MainActivity;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class LoginInfoFragment extends BaseFragment<LoginInfoPresenter> {

    public static final String TAG = LoginInfoFragment.class.getSimpleName();
    @Nullable
    private LoginActivityCallback mCallback;

    @BindView(R.id.login_info_email)
    EditText mEmail;
    @BindView(R.id.login_info_phone)
    EditText mPhone;

    private String mPhoneValue = "";

    public static LoginInfoFragment newInstance() {
        Bundle args = new Bundle();
        LoginInfoFragment fragment = new LoginInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    protected LoginInfoPresenter createPresenter() {
        return IzjuminkaApplication.getApplicationComponent(getActivity())
                .getLoginInfoPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (LoginActivityCallback) baseCallback;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_info, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this, savedInstanceState);
        init();
    }

    private void init() {
        baseCallback.setTitle(getString(R.string.login_title));
        final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "+7 ([000]) [000]-[00]-[00]",
                true,
                mPhone,
                null,
                (maskFilled, extractedValue) -> {
                    mPhoneValue = extractedValue;
                }
        );
        mPhone.addTextChangedListener(listener);
        mPhone.setOnFocusChangeListener(listener);
        mPhone.setHint(listener.placeholder());
    }

    @OnClick(R.id.login_info_send_btn)
    public void onSendClick() {
        mPresenter.onSendClick(mEmail.getText().toString(), mPhoneValue);
    }

    public void setEmailText(String email) {
        mEmail.setText(email);
    }

    public void onSkipClick() {
        mPresenter.onSkipClick();
    }

    public void goToMainScreen() {
        mCallback.openMainScreen();
    }
}
