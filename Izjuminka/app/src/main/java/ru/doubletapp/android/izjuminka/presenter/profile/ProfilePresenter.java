package ru.doubletapp.android.izjuminka.presenter.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.doubletapp.android.izjuminka.api.profile.ProfileInteractor;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;

/**
 * Created by hash on 20/10/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileFragment>
    implements ProfileInteractor.ProfileAvatarListener {

    @NonNull
    private ProfileInteractor mProfileInteractor;

    public ProfilePresenter(@NonNull ProfileInteractor profileInteractor) {
        mProfileInteractor = profileInteractor;
    }

    @Override
    protected void onViewAttached(@NonNull ProfileFragment view) {
        super.onViewAttached(view);
        getUserAvatar();
        List<String> posts = new ArrayList<>();
        posts.add("First");
        posts.add("Second");
        posts.add("Third");
        view.onPostsLoaded(posts);
    }

    private void getUserAvatar() {
        mProfileInteractor.getProfile(this);
    }


    @Override
    public void onGetAvatar(@Nullable String avatarUrl, @NonNull String username) {
        if (avatarUrl != null && mView != null)
            mView.setProfileAvatar(avatarUrl);
        if (mView != null)
            mView.setUsername(username);
    }
}
