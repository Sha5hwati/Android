package com.instalite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.instalite.GlideApp;

import com.instalite.Post;
import com.instalite.R;
import com.parse.ParseFile;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.PostHolder> {

    private Context context;
    private List<Post> posts;

    public HomeAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{

        TextView username;
        TextView username2;
        ImageView image;
        ImageView profilePic;
        TextView description;
        TextView createdAt;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            username2 = itemView.findViewById(R.id.username2);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.post_image);
            profilePic = itemView.findViewById(R.id.profilePic);
            createdAt = itemView.findViewById(R.id.creationTime);
        }

        public void bind(Post post){
            username.setText(post.getUser().getUsername());
            username2.setText(post.getUser().getUsername());
            description.setText(post.getDescription());
            String timeStamp = TimeFormatter.getTimeDifference(post.getCreated().toString());
            createdAt.setText(timeStamp + " ago");

            GlideApp.with(context).load(post.getImage().getUrl()).into(image);
            ParseFile file = (ParseFile) post.getUser().get("profilePicture");
            if(file == null){
                GlideApp.with(context).load(R.drawable.select_profile_icon).transform(new CircleCrop()).into(profilePic);
            }
            else {
                GlideApp.with(context).load(file.getUrl()).transform(new CircleCrop()).into(profilePic);
            }
        }
    }
}