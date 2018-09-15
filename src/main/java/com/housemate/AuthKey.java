package com.housemate;

import com.google.gson.annotations.SerializedName;

public class AuthKey {

    @SerializedName("key")
    private String key;

    public AuthKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
