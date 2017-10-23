package ru.doubletapp.android.izjuminka.api.rating;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class RatingInteractor {

    @NonNull
    private RatingRetrofit mRatingRetrofit;

    public RatingInteractor(@NonNull RatingRetrofit ratingRetrofit) {
        mRatingRetrofit = ratingRetrofit;
    }

    public void getRating(@NonNull RatingCallback callback) {
        mRatingRetrofit.getRating().enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        callback.ratingReceived(response.body().getTopUsers());
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {
                callback.errorOccurred(t);
            }
        });
    }

    public interface RatingCallback {

        void ratingReceived(List<RatingUser> users);

        void errorOccurred(Throwable t);
    }
}
