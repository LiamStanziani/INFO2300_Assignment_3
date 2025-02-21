package com.example.assignment2_stanziani.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2_stanziani.ViewModel.MovieClickListener;
import com.example.assignment2_stanziani.databinding.MovieDetailsBinding;

import java.io.InputStream;
import java.net.URL;

public class MovieDetails extends AppCompatActivity {
    MovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream moviePosterInputStream = (InputStream) new URL(intent.getExtras().getString("DrawableStr")).getContent();
                    Drawable moviePosterDrawable = Drawable.createFromStream(moviePosterInputStream, "src name");
                    binding.imageDetailsView.setImageDrawable(moviePosterDrawable);
                } catch (Exception ex) {

                }
            }
        });
        thread.start();

        binding.titleDetailTxt.setText(intent.getExtras().getString("Title"));
        binding.directorDetailTxt.setText(intent.getExtras().getString("Director"));
        binding.ratingDetailsTxt.setText(intent.getExtras().getString("Rating"));
        binding.yearDetailsTxt.setText(intent.getExtras().getString("Year"));
        binding.descriptionDetailsTxt.setText(intent.getExtras().getString("Description"));

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}