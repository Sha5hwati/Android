package com.codepath.flixster.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.R;

public class ViewHolderMain extends RecyclerView.ViewHolder {
    public ImageView movie_image;
    public TextView movie_title;
    public TextView movie_overview;

    public ViewHolderMain(View movieView){
        super(movieView);
        movie_image = (ImageView) movieView.findViewById(R.id.image);
        movie_title = (TextView) movieView.findViewById(R.id.title);
        movie_overview = (TextView) movieView.findViewById(R.id.overview);
    }
}
