package ru.doubletapp.android.izjuminka.presenter.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.doubletapp.android.izjuminka.api.news.NewsInteractor;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.presenter.BasePresenter;
import ru.doubletapp.android.izjuminka.view.news.NewsFragment;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsPresenter extends BasePresenter<NewsFragment> {
    private int mPrevStart = 0;
    private int mBaseOffset = 10;

    @NonNull
    private final NewsInteractor mNewsInteractor;

    List<News> mNewsList = new ArrayList<>();


    public NewsPresenter(@NonNull NewsInteractor newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void attachView(@NonNull NewsFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);

    }

    public void getNews() {
        mNewsInteractor.getNews(mPrevStart, mBaseOffset, new NewsInteractor.NewsCallback() {
            @Override
            public void onNewsReturned(List<News> newsList) {
                mPrevStart += mBaseOffset;
                if (mView != null) {
                    mNewsList.addAll(newsList);
                    mView.showNews(mNewsList);
                }
            }

            @Override
            public void onGetNewsFailed(Throwable t) {
                if (mView != null) mView.showError(t.getLocalizedMessage());
            }
        });
    }
}
