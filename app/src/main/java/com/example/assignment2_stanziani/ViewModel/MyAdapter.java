package com.example.assignment2_stanziani.ViewModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2_stanziani.Model.MovieModel;
import com.example.assignment2_stanziani.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    LiveData<List<MovieModel>> movies;
    MovieClickListener clickListener;

    public MyAdapter(Context context, LiveData<List<MovieModel>> movies) {
        this.context = context;
        this.movies = movies;
    }

    public void setClickListener(MovieClickListener myListener) {
        this.clickListener = myListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout, parent, false);
        return new MyViewHolder(movieView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieModel movie = movies.getValue().get(position);
        holder.title.setText(movie.getMovieTitle());
        holder.director.setText(movie.getStudio());
        holder.rating.setText(movie.getRating());
        holder.year.setText(movie.getReleaseYear());
        holder.description = movie.getMovieDescription();
        holder.imageView.setImageDrawable(movie.getMovieImage());
    }

    @Override
    public int getItemCount() {
        if (movies.getValue() != null) {
            return movies.getValue().size();
        }
        return -1;
    }
}
