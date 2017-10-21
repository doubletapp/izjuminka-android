package ru.doubletapp.android.izjuminka.presenter.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.doubletapp.android.izjuminka.api.profile.MyPostsResponse;
import ru.doubletapp.android.izjuminka.api.profile.ProfileInteractor;
import ru.doubletapp.android.izjuminka.api.profile.ProfileRetrofit;
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
        getMyPosts();
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

    public void getMyPosts() {
        mProfileInteractor.getMyPost(new ProfileInteractor.GetMyPostsListener() {
            @Override
            public void onSuccessfullGetPosts(@NonNull List<MyPostsResponse.Results> results) {
                mView.onPostsLoaded(results);
            }

            @Override
            public void onGetPostsFailed(@NonNull Throwable t) {
                mView.showError(t.getLocalizedMessage());
            }
        });
    }
}
