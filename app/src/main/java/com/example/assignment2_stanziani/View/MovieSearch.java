package com.example.assignment2_stanziani.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment2_stanziani.Model.MovieModel;
import com.example.assignment2_stanziani.ViewModel.MovieClickListener;
import com.example.assignment2_stanziani.ViewModel.MovieViewModel;
import com.example.assignment2_stanziani.ViewModel.MyAdapter;
import com.example.assignment2_stanziani.databinding.MovieSearchBinding;

import java.util.List;

public class MovieSearch extends AppCompatActivity implements MovieClickListener {
    MovieSearchBinding binding;
    MovieViewModel viewModel;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MovieSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.movieRecyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        viewModel.getMovieList().observe(this, movieList -> {
            myAdapter = new MyAdapter(getApplicationContext(), viewModel.getMovieList());
            binding.movieRecyclerView.setAdapter(myAdapter);
            myAdapter.setClickListener(this);
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View currentFocus = getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
                SetValues();
            }
        });
    }

    private void SetValues() {
        if (binding.movieSearchView.getQuery().toString().isEmpty() || binding.movieSearchView.getQuery() == null) {
            Toast.makeText(this, "Please input a movie name into the search field", Toast.LENGTH_SHORT).show();
        }
        else {
            viewModel.Search(binding.movieSearchView.getQuery().toString());
        }
    }

    @Override
    public void onCLick(View v, int pos) {
        MovieModel currentMovie = viewModel.getMovieList().getValue().get(pos);
        Intent intentObj = new Intent(this, MovieDetails.class );
        intentObj.putExtra("DrawableStr", currentMovie.getMovieDrawableString());
        intentObj.putExtra("Title", currentMovie.getMovieTitle());
        intentObj.putExtra("Director", currentMovie.getStudio());
        intentObj.putExtra("Rating", currentMovie.getRating());
        intentObj.putExtra("Year", currentMovie.getReleaseYear());
        intentObj.putExtra("Description", currentMovie.getMovieDescription());
        startActivity(intentObj);
    }
}