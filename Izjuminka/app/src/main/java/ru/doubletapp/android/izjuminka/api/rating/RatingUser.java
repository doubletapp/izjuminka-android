package ru.doubletapp.android.izjuminka.api.rating;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

public class RatingUser {
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("photo")
    private String mPhoto;
    @SerializedName("position")
    private String mPosition;
    @SerializedName("hyip")
    private String mHype;
    @SerializedName("is_you")
    private boolean mIsYou;
    @SerializedName("user")
    private int mUser;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    public String getHype() {
        return mHype;
    }

    public void setHype(String mHype) {
        this.mHype = mHype;
    }

    public boolean isYou() {
        return mIsYou;
    }

    public void setIsYou(boolean mIsYou) {
        this.mIsYou = mIsYou;
    }

    public int getUser() {
        return mUser;
    }

    public void setUser(int mUser) {
        this.mUser = mUser;
    }
}
