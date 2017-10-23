package ru.doubletapp.android.izjuminka.api.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsResponse {

    @SerializedName("news")
    List<News> newsList;
    @SerializedName("count")
    int count;

    public int getCount() {
        return count;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
