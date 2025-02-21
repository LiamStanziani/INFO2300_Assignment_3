package com.example.assignment2_stanziani.ViewModel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2_stanziani.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView director;
    TextView rating;
    TextView year;
    String description;
    MovieClickListener clickListener;

    public MyViewHolder(@NonNull View movieView, MovieClickListener clickListener) {
        super(movieView);

        imageView = movieView.findViewById(R.id.imageView);
        title = movieView.findViewById(R.id.title_txt);
        director = movieView.findViewById(R.id.director_text);
        year = movieView.findViewById(R.id.year_text);
        rating = movieView.findViewById(R.id.rating_text);
        this.clickListener = clickListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCLick(v, getAdapterPosition());
            }
        });

    }
}
