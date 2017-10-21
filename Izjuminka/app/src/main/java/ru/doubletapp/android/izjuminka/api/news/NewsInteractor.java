package ru.doubletapp.android.izjuminka.api.news;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.doubletapp.android.izjuminka.entities.news.News;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsInteractor {

    @NonNull
    private final NewsRetrofit mNewsRetrofit;

    NewsInteractor(@NonNull NewsRetrofit newsRetrofit) {
        this.mNewsRetrofit = newsRetrofit;
    }

    public void getNews(int start, int offset, @NonNull NewsCallback callback) {
        mNewsRetrofit.getNews(start, offset).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onNewsReturned(response.body().getNewsList());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onGetNewsFailed(t);
            }
        });
    }

    public interface NewsCallback {
        void onNewsReturned(List<News> newsList);

        void onGetNewsFailed(Throwable t);
    }
}
