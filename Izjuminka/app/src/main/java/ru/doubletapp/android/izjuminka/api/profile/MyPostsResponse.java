package ru.doubletapp.android.izjuminka.api.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hash on 21/10/2017.
 */

public class MyPostsResponse {


    @SerializedName("count")
    private int mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    @SerializedName("results")
    private List<Results> mResults;

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String mNext) {
        this.mNext = mNext;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String mPrevious) {
        this.mPrevious = mPrevious;
    }

    public List<Results> getResults() {
        return mResults;
    }

    public void setResults(List<Results> mResults) {
        this.mResults = mResults;
    }

    public static class Results {
        @SerializedName("id")
        private int mId;
        @SerializedName("author")
        private String mAuthor;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("validate_status")
        private String mValidateStatus;
        @SerializedName("validate_message")
        private String mValidateMessage;
        @SerializedName("create_datetime")
        private String mCreateDatetime;
        @SerializedName("point")
        private String mPoint;
        @SerializedName("city")
        private String mCity;
        @SerializedName("vk_url")
        private String mVkUrl;
        @SerializedName("cash")
        private double mCash;

        public int getId() {
            return mId;
        }

        public void setId(int mId) {
            this.mId = mId;
        }

        public String getAuthor() {
            return mAuthor;
        }

        public void setAuthor(String mAuthor) {
            this.mAuthor = mAuthor;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String mDescription) {
            this.mDescription = mDescription;
        }

        public String getValidateStatus() {
            return mValidateStatus;
        }

        public void setValidateStatus(String mValidateStatus) {
            this.mValidateStatus = mValidateStatus;
        }

        public String getValidateMessage() {
            return mValidateMessage;
        }

        public void setValidateMessage(String mValidateMessage) {
            this.mValidateMessage = mValidateMessage;
        }

        public String getCreateDatetime() {
            return mCreateDatetime;
        }

        public void setCreateDatetime(String mCreateDatetime) {
            this.mCreateDatetime = mCreateDatetime;
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

        public String getVkUrl() {
            return mVkUrl;
        }

        public void setVkUrl(String mVkUrl) {
            this.mVkUrl = mVkUrl;
        }

        public double getCash() {
            return mCash;
        }

        public void setCash(double cash) {
            mCash = cash;
        }
    }
}
