package ru.doubletapp.android.izjuminka.entities.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class News {
    @SerializedName("mImages")
    private List<String> mImages;
    @SerializedName("mDescription")
    private String mDescription;
    private boolean mIsExchanged = false;

    public News(List<String> images, String description) {
        mImages = images;
        mDescription = description;
    }

    public List<String> getImages() {
        return mImages;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isExchanged() {
        return mIsExchanged;
    }

    public void setExchanged(boolean exchanged) {
        mIsExchanged = exchanged;
    }
}
