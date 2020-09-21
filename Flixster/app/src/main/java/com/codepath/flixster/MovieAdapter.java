package com.codepath.flixster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    // Involves populating data into the item through holder
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = this.movieList.get(position);

        holder.movie_title.setText(movie.getTitle());
        holder.movie_overview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movie_poster;
        public TextView movie_title;
        public TextView movie_overview;

        public ViewHolder(View movieView){
            super(movieView);
            movie_poster = (ImageView) movieView.findViewById(R.id.poster);
            movie_title = (TextView) movieView.findViewById(R.id.title);
            movie_overview = (TextView) movieView.findViewById(R.id.overview);
        }
    }
}

