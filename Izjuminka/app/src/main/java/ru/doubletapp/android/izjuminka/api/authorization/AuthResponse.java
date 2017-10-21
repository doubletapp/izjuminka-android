package ru.doubletapp.android.izjuminka.api.authorization;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hash on 21/10/2017.
 */

public class AuthResponse {

    @SerializedName("vk_id")
    private String mVkId;
    @SerializedName("vk_token")
    private String mVkToken;
    @SerializedName("auth_token")
    private String mAuthToken;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("is_phone_confirmed")
    private boolean mIsPhoneConfirmed;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("is_email_confirmed")
    private boolean mIsEmailConfirmed;
    @SerializedName("point")
    private String mPoint;
    @SerializedName("city")
    private String mCity;

    public String getVkId() {
        return mVkId;
    }

    public void setVkId(String mVkId) {
        this.mVkId = mVkId;
    }

    public String getVkToken() {
        return mVkToken;
    }

    public void setVkToken(String mVkToken) {
        this.mVkToken = mVkToken;
    }

    public String getAuthToken() {
        return mAuthToken;
    }

    public void setAuthToken(String mAuthToken) {
        this.mAuthToken = mAuthToken;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public boolean getIsPhoneConfirmed() {
        return mIsPhoneConfirmed;
    }

    public void setIsPhoneConfirmed(boolean mIsPhoneConfirmed) {
        this.mIsPhoneConfirmed = mIsPhoneConfirmed;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public boolean getIsEmailConfirmed() {
        return mIsEmailConfirmed;
    }

    public void setIsEmailConfirmed(boolean mIsEmailConfirmed) {
        this.mIsEmailConfirmed = mIsEmailConfirmed;
    }

    public String getPoint() {
        return mPoint;
    }

    public void setPoint(String mPoint) {
        this.mPoint = mPoint;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }
}
