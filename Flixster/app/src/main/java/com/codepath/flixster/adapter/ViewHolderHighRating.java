package com.codepath.flixster.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.MovieDetailsActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.models.GlideApp;
import com.codepath.flixster.models.Movie;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ViewHolderHighRating extends RecyclerView.ViewHolder {
    private View view;
    private ImageView movie_backdropImage;
    private TextView movie_title;
    private TextView movie_overview;
    private Context context;
    private static final int MARGIN = 50;
    private static final int RADIUS = 100;

    public ViewHolderHighRating(View movieView, Context context){
        super(movieView);
        this.context = context;
        view = movieView;
        movie_backdropImage = movieView.findViewById(R.id.backdropImage);
        movie_title = movieView.findViewById(R.id.title);
        movie_overview = movieView.findViewById(R.id.overview);
    }

    public void bind(final Movie movie){
        RoundedCornersTransformation transformation = new RoundedCornersTransformation(RADIUS, MARGIN);
        GlideApp.with(context).load(movie.getPosterPath()).transform(transformation).into(movie_backdropImage);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            movie_title.setText(movie.getTitle());
            movie_overview.setText(movie.getOverview());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first parameter is the context, second is the class of the activity to launch
                Intent i = new Intent(context, MovieDetailsActivity.class);
                // put "extras" into the bundle for access in the second activity
                i.putExtra("movie", movie);
                // brings up the second activity
                context.startActivity(i);
            }
        });
    }
}
