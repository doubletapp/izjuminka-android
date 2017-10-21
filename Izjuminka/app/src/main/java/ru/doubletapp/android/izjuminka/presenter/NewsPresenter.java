package ru.doubletapp.android.izjuminka.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import ru.doubletapp.android.izjuminka.api.news.NewsInteractor;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.view.news.NewsFragment;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsPresenter extends BasePresenter<NewsFragment> {

    @NonNull
    private final NewsInteractor mNewsInteractor;


    public NewsPresenter(@NonNull NewsInteractor newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void attachView(@NonNull NewsFragment view, @Nullable Bundle savedState) {
        super.attachView(view, savedState);

    }

    public void getNews() {
        List<News> news = mNewsInteractor.getNews();
        if (mView != null)  mView.showNews(news);
    }
}
