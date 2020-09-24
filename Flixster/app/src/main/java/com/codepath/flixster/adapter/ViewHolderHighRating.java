package com.codepath.flixster.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.R;

public class ViewHolderHighRating extends RecyclerView.ViewHolder {
    public ImageView movie_backdropImage;
    public TextView movie_title;
    public TextView movie_overview;

    public ViewHolderHighRating(View movieView){
        super(movieView);
        movie_backdropImage = (ImageView) movieView.findViewById(R.id.backdropImage);
        movie_title = (TextView) movieView.findViewById(R.id.title);
        movie_overview = (TextView) movieView.findViewById(R.id.overview);
    }
}
