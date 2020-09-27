package com.codepath.flixster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private final int MAIN = 0, BACKDROP = 1;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getItemViewType(int position) {
        Float rating = Float.parseFloat(movieList.get(position).getRating());
        if(rating > 7)
            return BACKDROP;
        return MAIN;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML and returning the holder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder;
        View movieView;

        switch (viewType){
            case BACKDROP:
                movieView = inflater.inflate(R.layout.item_movie_rating5, parent, false);
                viewHolder = new ViewHolderHighRating(movieView, context);
                break;
            default:
                movieView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolderMain(movieView, context);
        }
        return viewHolder;
    }

    @Override
    // Involves populating data into the item through holder
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = this.movieList.get(position);

        switch (holder.getItemViewType()){
            case BACKDROP:
                ViewHolderHighRating rating5 = (ViewHolderHighRating) holder;
                rating5.bind(movie);
                break;
            default:
                ViewHolderMain main = (ViewHolderMain) holder;
                main.bind(movie);
        }
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }
}

