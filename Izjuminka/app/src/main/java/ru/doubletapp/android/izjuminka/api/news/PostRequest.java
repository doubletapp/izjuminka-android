package ru.doubletapp.android.izjuminka.api.news;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class PostRequest {
    @SerializedName("description")
    private String mDescription;
    @SerializedName("photos")
    private List<String> mPhotos;
    @Nullable
    @SerializedName("point")
    private float[] mPoint;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vk_id_reference")
    private int mIdReference;

    public float[] getPoint() {
        return mPoint;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setPhotos(List<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public void setPoint(@Nullable float[] mPoint) {
        this.mPoint = mPoint;
    }

    public List<String> getPhotos() {
        return mPhotos;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getIdReference() {
        return mIdReference;
    }

    public void setIdReference(int mIdReference) {
        this.mIdReference = mIdReference;
    }
}
