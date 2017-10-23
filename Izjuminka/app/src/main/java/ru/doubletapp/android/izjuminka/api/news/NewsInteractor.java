package ru.doubletapp.android.izjuminka.api.news;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.doubletapp.android.izjuminka.entities.news.News;
import ru.doubletapp.android.izjuminka.utils.StringUtils;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class NewsInteractor {

    @NonNull
    private final NewsRetrofit mNewsRetrofit;

    NewsInteractor(@NonNull NewsRetrofit newsRetrofit) {
        this.mNewsRetrofit = newsRetrofit;
    }

    public void getNews(int start, int offset, @NonNull NewsLoadCallback callback) {
        mNewsRetrofit.getNews(start, offset).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onNewsReturned(response.body().getNewsList());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onErrorReceived(t);
            }
        });
    }

    public void loadImage(File file, @NonNull AddNewsCallback callback) {
        mNewsRetrofit.loadPhoto(RequestBody.create(MediaType.parse("text/plain"), file.getName()),
                MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file)))
                .enqueue(new Callback<ImageResponse>() {
                    @Override
                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus().equals("ok")) {
                                callback.imageLoaded(response.body().getUrl());
                            } else {
                                callback.loadError();
                            }
                        } else if (!response.isSuccessful()) {
                            callback.loadError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageResponse> call, Throwable t) {
                        callback.onErrorReceived(t);
                    }
                });
    }

    public void postNews(int id, String title, String description, @Nullable List<String> urls, @Nullable Location location, @NonNull PostCallback callback) {
        PostRequest request = new PostRequest();
        request.setDescription(description);
        if (id != -1) request.setIdReference(id);
        if (!StringUtils.isNullOrEmpty(title)) request.setTitle(title);
        if (urls != null) request.setPhotos(urls);
        if (location != null)
            request.setPoint(new float[]{(float) location.getLatitude(), (float) location.getLongitude()});
        mNewsRetrofit.postNews(request).enqueue(new Callback<PostRequest>() {
            @Override
            public void onResponse(Call<PostRequest> call, Response<PostRequest> response) {
                if (response.isSuccessful())
                    callback.posted(response.body());
            }

            @Override
            public void onFailure(Call<PostRequest> call, Throwable t) {

            }
        });
    }

    public interface NewsBaseCallback {
        void onErrorReceived(Throwable t);
    }

    public interface NewsLoadCallback extends NewsBaseCallback {
        void onNewsReturned(List<News> newsList);
    }

    public interface AddNewsCallback extends NewsBaseCallback {

        void imageLoaded(String url);

        void loadError();
    }

    public interface PostCallback extends NewsBaseCallback {
        void posted(PostRequest post);
    }
}
