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

/**
 * Created by hash on 21/10/2017.
 */

public class ProfileInteractor {

    public interface ProfileAvatarListener {
        void onGetAvatar(@Nullable String avatarUrl, @NonNull String username);
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


}
