package ru.doubletapp.android.izjuminka.presenter.news;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ru.doubletapp.android.izjuminka.R;
import ru.doubletapp.android.izjuminka.api.news.NewsInteractor;
import ru.doubletapp.android.izjuminka.api.news.PostRequest;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.news.addNews.AddNewsFragment;

import static ru.doubletapp.android.izjuminka.view.news.addNews.AddNewsActivity.KEY_NEWS;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class AddNewsPresenter extends BasePresenter<AddNewsFragment> {


    private static final String TAG = AddNewsPresenter.class.getSimpleName();

    @Nullable
    private News mNews;

    @NonNull
    private final NewsInteractor mNewsInteractor;

    public AddNewsPresenter(@NonNull NewsInteractor newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void attachView(@NonNull AddNewsFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);
        if (view.getArguments().containsKey(KEY_NEWS)) {
            mNews = view.getArguments().getParcelable(KEY_NEWS);
            view.showLinkedNews(mNews);
        }
    }

    public void onNextClick(String title, @NonNull String description, @Nullable List<String> items, @Nullable Location mLocation) {
        if (description.isEmpty()) {
            if (mView != null) {
                mView.showError(mView.getString(R.string.add_news_description_error));
            }
            return;
        }
        if (mView != null) mView.showLoading();

        if (items != null && !items.isEmpty()) {
            List<String> uploaded = new ArrayList<>();
            for (String url : items) {
                mNewsInteractor.loadImage(new File(url), new NewsInteractor.AddNewsCallback() {
                    @Override
                    public void imageLoaded(String url) {
                        uploaded.add(url);
                        if (uploaded.size() == items.size())
                            postNews(title, description, uploaded, mLocation);
                    }

                    @Override
                    public void loadError() {
                        if (mView != null)
                            mView.showError(mView.getString(R.string.add_news_upload_error));
                        uploaded.add("");
                        if (uploaded.size() == items.size())
                            postNews(title, description, uploaded, mLocation);
                    }

                    @Override
                    public void onErrorReceived(Throwable t) {
                        if (mView != null) mView.showError(t.getLocalizedMessage());
                        uploaded.add("");
                        if (uploaded.size() == items.size())
                            postNews(title, description, uploaded, mLocation);

                    }
                });
            }

        } else {
            postNews(title, description, null, mLocation);
        }
    }

    private void postNews(String title, String description, @Nullable List<String> uploaded, @Nullable Location location) {
        mNewsInteractor.postNews(mNews != null ? mNews.getPostId() : -1, title, description, uploaded, location, new NewsInteractor.PostCallback() {
            @Override
            public void posted(PostRequest post) {

            }

            @Override
            public void onErrorReceived(Throwable t) {

            }
        });

        if (mView != null) {
            mView.hideLoading();
            mView.close();
        }
    }
}
