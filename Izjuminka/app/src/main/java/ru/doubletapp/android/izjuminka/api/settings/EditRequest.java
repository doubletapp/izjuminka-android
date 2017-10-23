package ru.doubletapp.android.izjuminka.api.settings;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hash on 21/10/2017.
 */

public class EditRequest {

    final String phone;
    final String email;

    public EditRequest(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
