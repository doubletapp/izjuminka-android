package ru.doubletapp.android.izjuminka.presenter.rating;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import ru.doubletapp.android.izjuminka.api.rating.RatingInteractor;
import ru.doubletapp.android.izjuminka.api.rating.RatingUser;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.rating.RatingFragment;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class RatingPresenter extends BasePresenter<RatingFragment> {

    @Inject
    RatingInteractor mRatingIterator;

    public RatingPresenter(@NonNull RatingInteractor ratingInteractor) {
        mRatingIterator = ratingInteractor;
    }

    @Override
    public void attachView(@NonNull RatingFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);
        getUsers();
    }

    public void getUsers() {
        if (mView != null) mView.setLoading(true);
        mRatingIterator.getRating(new RatingInteractor.RatingCallback() {
            @Override
            public void ratingReceived(List<RatingUser> users) {
                if (mView != null) {
                    mView.showRating(users);
                    mView.setLoading(false);
                }
            }

            @Override
            public void errorOccurred(Throwable t) {
                if (mView != null) {
                    mView.showError(t.getLocalizedMessage());
                    mView.setLoading(false);
                }
            }
        });
    }
}
