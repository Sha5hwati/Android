package com.codepath.apps.twitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.twitter.models.Tweet;

import java.util.List;


public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweetList;

    public TweetAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweetList = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweetList.get(position);

        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweetList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweetList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        ImageView embeddedImage;
        TextView content;
        TextView username;
        TextView name;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profilePic);
            content = itemView.findViewById(R.id.content);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            embeddedImage = itemView.findViewById(R.id.embedded_image);
        }

        public void bind(Tweet tweet){
            content.setText(tweet.getContent());
            time.setText(tweet.getCreatedAt());
            name.setText(tweet.getUser().getName());
            username.setText("@"+tweet.getUser().getUsername());
            Glide.with(context).load(tweet.getUser().getImageUrl()).transform(new CircleCrop()).into(profileImage);
            Glide.with(context).load(tweet.getUrl()).into(embeddedImage);
        }
    }

}
