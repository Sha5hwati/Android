package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;


public class User {

    private String imageUrl;
    private String name;
    private String username;

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.username = jsonObject.getString("screen_name");
        user.imageUrl = jsonObject.getString("profile_image_url_https");
        return user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
