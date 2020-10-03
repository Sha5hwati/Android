package com.codepath.flixster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.MovieDetailsActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.models.GlideApp;
import com.codepath.flixster.models.Movie;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ViewHolderMain extends RecyclerView.ViewHolder {
    private View view;
    private ImageView movie_image;
    private TextView movie_title;
    private TextView movie_overview;
    private Context context;
    private int margin = 10;
    private int radius = 100;

    public ViewHolderMain(View movieView, Context context){
        super(movieView);
        this.context = context;
        view = movieView;
        movie_image = (ImageView) movieView.findViewById(R.id.image);
        movie_title = (TextView) movieView.findViewById(R.id.title);
        movie_overview = (TextView) movieView.findViewById(R.id.overview);
    }

    public void bind(final Movie movie){
        RoundedCornersTransformation transformation = new RoundedCornersTransformation(radius, margin);
        GlideApp.with(context).load(movie.getPosterPath()).transform(transformation).into(movie_image);
        movie_title.setText(movie.getTitle());
        movie_overview.setText(movie.getOverview());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first parameter is the context, second is the class of the activity to launch
                Intent i = new Intent(context, MovieDetailsActivity.class);
                // put "extras" into the bundle for access in the second activity
                i.putExtra("movie", movie);
                Pair<View, String> transitionTitle = Pair.create((View)movie_title, "transitionTitle");
                Pair<View, String> transitionOverview = Pair.create((View)movie_title, "transitionOverview");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, transitionTitle, transitionOverview);
                // brings up the second activity
                context.startActivity(i, options.toBundle());
            }
        });
    }

}
