package ru.doubletapp.android.izjuminka.api.news;

import java.util.List;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public interface NewsRetrofit {

    List<News> getNews();

}
