package ru.doubletapp.android.izjuminka.api.authorization;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hash on 21/10/2017.
 */

public class AuthRequest {

    @SerializedName("vk_id")
    final String vkId;

    @SerializedName("vk_token")
    final String vkToken;

    public AuthRequest(String id, String token) {
        vkId = id;
        vkToken = token;
    }
}
