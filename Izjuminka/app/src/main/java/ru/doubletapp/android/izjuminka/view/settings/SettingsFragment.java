package ru.doubletapp.android.izjuminka.view.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.presenter.SettingsPresenter;
import ru.doubletapp.android.izjuminka.view.BaseActivity;
import ru.doubletapp.android.izjuminka.view.BaseFragment;

/**
 * Created by hash on 21/10/2017.
 */

public class SettingsFragment extends BaseFragment<SettingsPresenter> {

    @BindView(R.id.settings_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.settings_phone)
    EditText mPhoneEdit;
    @BindView(R.id.settings_email)
    EditText mEmailEdit;

    private String mPhone;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_profile, menu);
    }

    private void setupView() {
        BaseActivity activity = ((BaseActivity) getActivity());
        activity.setSupportActionBar(mToolbar);
        ActionBar bar = activity.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(getString(R.string.settings_title));
        }
        mToolbar.setNavigationOnClickListener(view -> getActivity().onBackPressed());

        final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "+7 ([000]) [000]-[00]-[00]",
                true,
                mPhoneEdit,
                null,
                (maskFilled, extractedValue) -> {
                    mPhone = extractedValue;
                }
        );
        mPhoneEdit.addTextChangedListener(listener);
        mPhoneEdit.setOnFocusChangeListener(listener);
        mPhoneEdit.setHint(listener.placeholder());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_save:
                mPresenter.editProfile(
                        mEmailEdit.getText().toString(),
                        mPhoneEdit.getText().toString());
                break;
            default:
                return false;
        }
        return true;
    }

    @Nullable
    @Override
    protected SettingsPresenter createPresenter() {
        return IzjuminkaApplication
                .getApplicationComponent(getContext())
                .getSettingsPresenter();
    }

    public void setEmail(@NonNull String email) {
        mEmailEdit.setText(email);
    }

    public void setPhone(@NonNull String phone) {
        mPhoneEdit.setText(phone);
    }

    @OnClick(R.id.settings_logout)
    void logout() {
        mPresenter.logout();
    }
}
