package ru.doubletapp.android.izjuminka.api.authorization;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by hash on 21/10/2017.
 */

public interface AuthRetrofit {

    @NonNull
    @POST("auth/")
    Call<AuthResponse> auth(@Body AuthRequest request);
}
