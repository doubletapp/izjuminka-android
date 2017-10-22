package ru.doubletapp.android.izjuminka.api.rating;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Akimov on 22.10.2017.
 */

class RatingResponse {
    @SerializedName("top_users")
    private List<RatingUser> mTopUsers;

    public List<RatingUser> getTopUsers() {
        return mTopUsers;
    }

    public void setTopUsers(List<RatingUser> mTopUsers) {
        this.mTopUsers = mTopUsers;
    }
}
