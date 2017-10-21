package ru.doubletapp.android.izjuminka.api.news;

import android.support.annotation.NonNull;

import java.util.List;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsInteractor {

    @NonNull
    private final NewsRemoteRepository mNewsRemoteRepository;

    NewsInteractor(@NonNull NewsRemoteRepository newsRemoteRepository) {
        this.mNewsRemoteRepository = newsRemoteRepository;
    }

    public List<News> getNews() {
        return mNewsRemoteRepository.getNews();
    }
}
