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
}
