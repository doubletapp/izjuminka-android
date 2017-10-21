package ru.doubletapp.android.izjuminka.presenter.profile;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.profile.ProfileFragment;

/**
 * Created by hash on 20/10/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileFragment> {

    @Override
    protected void onViewAttached(@NonNull ProfileFragment view) {
        super.onViewAttached(view);
        List<String> posts = new ArrayList<>();
        posts.add("First");
        posts.add("Second");
        posts.add("Third");
        view.onPostsLoaded(posts);
    }
}
