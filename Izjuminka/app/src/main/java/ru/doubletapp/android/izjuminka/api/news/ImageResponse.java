package ru.doubletapp.android.izjuminka.api.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

class ImageResponse {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("url")
    private String mUrl;

    public String getStatus() {
        return mStatus;
    }

    public String getUrl() {
        return mUrl;
    }
}

