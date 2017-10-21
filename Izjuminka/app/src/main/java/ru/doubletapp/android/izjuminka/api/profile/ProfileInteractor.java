package ru.doubletapp.android.izjuminka.api.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hash on 21/10/2017.
 */

public class ProfileInteractor {

    @NonNull
    ProfileRetrofit mProfileRetrofit;

    public ProfileInteractor(@NonNull ProfileRetrofit profileRetrofit) {
        mProfileRetrofit = profileRetrofit;
    }

    public interface ProfileAvatarListener {
        void onGetAvatar(@Nullable String avatarUrl, @NonNull String username);
    }

    public interface GetMyPostsListener {
        void onSuccessfullGetPosts(@NonNull List<MyPostsResponse.Results> results);
        void onGetPostsFailed(@NonNull Throwable t);
    }

    public void getProfile(@NonNull ProfileAvatarListener listener) {
        VKRequest photoRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,"photo_100"));
        photoRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                @SuppressWarnings("unchecked")
                VKList<VKApiUserFull> usersArray = (VKList<VKApiUserFull>) response.parsedModel;

                for (VKApiUserFull userFull : usersArray) {
                    listener.onGetAvatar(userFull.photo_100, userFull.first_name + " " + userFull.last_name);
                }
            }
        });
    }

    public void getMyPost(@NonNull GetMyPostsListener listener) {
        mProfileRetrofit.getMyPosts().enqueue(new Callback<MyPostsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyPostsResponse> call, @NonNull Response<MyPostsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                    listener.onSuccessfullGetPosts(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyPostsResponse> call, @NonNull Throwable t) {
                listener.onGetPostsFailed(t);
            }
        });

    }




}
