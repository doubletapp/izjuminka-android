package ru.doubletapp.android.izjuminka.api.authorization;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;

/**
 * Created by hash on 21/10/2017.
 */

public class AuthInteractor {

    public interface AuthCallback {
        void onSuccessfullAuth();
        void onAuthFailed(Throwable t);
    }

    @NonNull
    AuthLocalData mAuthLocalData;

    @NonNull
    AuthRetrofit mAuthRetrofit;

    public AuthInteractor(@NonNull AuthRetrofit authRetrofit,
                          @NonNull AuthLocalData authLocalData) {
        mAuthRetrofit = authRetrofit;
        mAuthLocalData = authLocalData;
    }

    public void auth(@NonNull String vkId, @NonNull String vkToken, @NonNull AuthCallback callback) {
        AuthRequest request = new AuthRequest(vkId, vkToken);
        mAuthRetrofit.auth(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mAuthLocalData.setToken(response.body().getAuthToken());
                    callback.onSuccessfullAuth();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "FAIL!");
                callback.onAuthFailed(t);
            }
        });
    }
}
