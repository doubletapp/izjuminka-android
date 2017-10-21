package ru.doubletapp.android.izjuminka.view.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import ru.doubletapp.android.izjuminka.IzjuminkaApplication;
import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.presenter.profile.ProfilePresenter;
import ru.doubletapp.android.izjuminka.view.BaseActivity;
import ru.doubletapp.android.izjuminka.view.BaseFragment;
import ru.doubletapp.android.izjuminka.view.settings.SettingsActivity;

/**
 * Created by hash on 20/10/2017.
 */

public class ProfileFragment extends BaseFragment<ProfilePresenter> {

    @BindView(R.id.profile_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.profile_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.profile_avatar)
    ImageView mProfileAvatar;
    @BindView(R.id.profile_username)
    TextView mProfileUsername;

    private MyPostsAdapter mPostsAdapter;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IzjuminkaApplication.getApplicationComponent(getContext()).inject(this);
        mPostsAdapter = new MyPostsAdapter();
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
        ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle("OPANA");
        mRecyclerView.setAdapter(mPostsAdapter);
        Glide.with(this).load(R.drawable.default_profile_avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(mProfileAvatar);
    }

    @Nullable
    @Override
    protected ProfilePresenter createPresenter() {
        return IzjuminkaApplication
                .getApplicationComponent(getContext())
                .getProfilePresenter();
    }

    public void onPostsLoaded(List<String> posts) {
        mPostsAdapter.setData(posts);
    }

    public void setProfileAvatar(@NonNull String profileAvatar) {
        Glide.with(this).load(profileAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(mProfileAvatar);
    }

    public void setUsername(@NonNull String username) {
        mProfileUsername.setText(username);
    }

    @OnClick(R.id.profile_settings)
    void onSettingsClick() {
        SettingsActivity.start(getContext());
    }
}
