package com.codepath.apps.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.twitter.models.Tweet;
import com.codepath.apps.twitter.models.User;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    final static String TAG = "ComposeActivity";
    final static int MAX_TWEET_LENGTH = 280;
    ImageView btnClose;
    ImageView userImage;
    TextView name;
    TextView handle;
    EditText compose;
    TextView charLimit;
    Button btnTweet;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        btnClose = findViewById(R.id.close);
        userImage = findViewById(R.id.userImage);
        name = findViewById(R.id.username);
        handle = findViewById(R.id.handle);
        compose = findViewById(R.id.compose);
        charLimit = findViewById(R.id.charsLeft);
        btnTweet = findViewById(R.id.tweet);

        client = TwitterApp.getRestClient(this);

        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        name.setText(user.getName());
        handle.setText(user.getUsername());
        Glide.with(ComposeActivity.this).load(user.getImageUrl()).transform(new CircleCrop()).into(userImage);


        compose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int charsLeft = MAX_TWEET_LENGTH - editable.toString().length();
                charLimit.setText( charsLeft + "/" + MAX_TWEET_LENGTH);
            }
        });

        btnTweet.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                String tweetContent = compose.getText().toString();
                if(tweetContent.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "Your tweet cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                 if(tweetContent.length() > MAX_TWEET_LENGTH){
                     Toast.makeText(ComposeActivity.this, "Your tweet is too long", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                     @Override
                     public void onSuccess(int statusCode, Headers headers, JSON json) {
                         try {
                             Tweet tweet = Tweet.fromJson(json.jsonObject);
                             Intent intent = new Intent();
                             intent.putExtra("tweet", Parcels.wrap(tweet));
                             setResult(RESULT_OK, intent);
                             finish();
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }

                     @Override
                     public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "onFailure to publish tweet " + response, throwable);
                     }
                 });
             }
         });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
