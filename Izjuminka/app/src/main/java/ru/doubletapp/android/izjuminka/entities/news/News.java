package ru.doubletapp.android.izjuminka.entities.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Akimov on 21.10.2017.
 */

public class News implements Parcelable {
    @SerializedName("photos")
    private List<String> mImages;
    @SerializedName("text")
    private String mDescription;
    private boolean mIsExchanged = false;

    public News(List<String> images, String description) {
        mImages = images;
        mDescription = description;
    }

    protected News(Parcel in) {
        mImages = in.createStringArrayList();
        mDescription = in.readString();
        mIsExchanged = in.readByte() != 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(mImages);
        parcel.writeString(mDescription);
        parcel.writeByte((byte) (mIsExchanged ? 1 : 0));
    }
}
