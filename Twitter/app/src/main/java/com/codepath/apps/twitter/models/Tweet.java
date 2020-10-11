package com.codepath.apps.twitter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    private String content;
    private String createdAt;
    private User user;
    private String url;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.content = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        JSONArray urls = jsonObject.getJSONObject("entities").getJSONArray("urls");
        tweet.url = "";
        if(urls.length() != 0)
            tweet.url = urls.getJSONObject(0).getString("url");
        return tweet;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<Tweet>();
        for(int i=0; i<jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
