package ru.doubletapp.android.izjuminka.presenter.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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

    private static final String TAG = NewsPresenter.class.getSimpleName();
    private int mPrevStart = 0;
    private int mBaseOffset = 10;
    private boolean mIsLoading = false;

    @NonNull
    private final NewsInteractor mNewsInteractor;

    private List<News> mNewsList = new ArrayList<>();


    public NewsPresenter(@NonNull NewsInteractor newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void attachView(@NonNull NewsFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);

    }

    public void getNews() {
        mIsLoading = true;
        mNewsInteractor.getNews(mPrevStart, mBaseOffset, new NewsInteractor.NewsLoadCallback() {
            @Override
            public void onNewsReturned(List<News> newsList) {
                mPrevStart += mBaseOffset;
                if (mView != null) {
                    for (News news : newsList)
                        if (!mNewsList.contains(news)) mNewsList.add(news);
                    mView.showNews(mNewsList);
                }
                mIsLoading = false;
            }

            @Override
            public void onErrorReceived(Throwable t) {
                if (mView != null) mView.showError(t.getLocalizedMessage());
                mIsLoading = false;
            }
        });
    }

    public boolean isLoading() {
        return mIsLoading;
    }
}
