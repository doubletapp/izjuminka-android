package ru.doubletapp.android.izjuminka.api.settings;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.doubletapp.android.izjuminka.api.profile.ProfileInteractor;

/**
 * Created by hash on 21/10/2017.
 */

public class EditInteractor {

    public interface EditProfileListener {
        void onSuccessfullEdit();
        void onEditFailed();
    }

    @NonNull
    EditRetrofit mEditRetrofit;

    public EditInteractor(@NonNull EditRetrofit editRetrofit) {
        mEditRetrofit = editRetrofit;
    }

    public void editProfile(@NonNull String email, @NonNull String phone, @NonNull EditProfileListener listener) {
        mEditRetrofit
                .edit(new EditRequest(phone, email))
                .enqueue(new Callback<EditResponse>() {
                    @Override
                    public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {
                        listener.onSuccessfullEdit();
                    }

                    @Override
                    public void onFailure(Call<EditResponse> call, Throwable t) {
                        listener.onEditFailed();
                    }
                });
    }
}
