package ru.doubletapp.android.izjuminka.api.news;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.annotation.NonNull;

import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsRemoteRepository implements NewsRetrofit {

    @NonNull
    private final NewsRetrofit mNewsRetrofit;

    NewsRemoteRepository(@NonNull NewsRetrofit newsRetrofit) {
        mNewsRetrofit = newsRetrofit;
    }

    @Override
    public List<News> getNews() {

        List<News> result = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            result.add(new News(Arrays.asList("http://kot-pes.com/wp-content/uploads/2015/06/Shiba-Inu.jpg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWXK8lx5fwMqPI5U1WMY6RsPpz3Smt0RlG1w7QNEkCLAF2JAwI"), "Собака прыгает по пляжу. По пляжу прыгает собака. Собака счастлива. В мире все спокойно"));
        }
        return result;
    }
}
