package com.example.assignment2_stanziani.ViewModel;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2_stanziani.Model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieViewModel extends ViewModel {
    private final MutableLiveData<List<MovieModel>> movieList = new MutableLiveData<>();

    public MovieViewModel() {
        handler.post(runnable);
    }

    public LiveData<List<MovieModel>> getMovieList() {
        return movieList;
    }

    private void updateView() {
        movieList.setValue(new ArrayList<>());
    }

    public void Search(String movieTitle) {
        String urlString = "https://www.omdbapi.com/?apikey=f3d74dcb&s=" + movieTitle + "&type=movie";
        ApiClient.get(urlString, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!movieList.getValue().isEmpty()){
                    movieList.getValue().clear();
                }
                assert response.body() != null;
                String responseData = response.body().string();
                JSONObject json  = null;
                try {
                    json = new JSONObject(responseData);
                    JSONArray searchArray = json.getJSONArray("Search");
                    for (int i = 0; i < searchArray.length(); i++) {
                        JSONObject movies = searchArray.getJSONObject(i);

                        String strImdbId = movies.optString("imdbID", "");
                        GetDetails(strImdbId);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void GetDetails(String id) {
        String urlString = "https://www.omdbapi.com/?apikey=f3d74dcb&i=" + id + "&type=movie";
        ApiClient.get(urlString, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                String responseData = response.body().string();
                JSONObject json  = null;
                try {
                        json = new JSONObject(responseData);

                        String strMovieTitle = json.optString("Title", "");
                        String strMovieStudio = json.optString("Director", "");
                        String strMovieRating = json.optString("imdbRating", "");
                        String strMovieYear = json.optString("Year", "");
                        String strMovieDescription = json.optString("Plot", "");
                        String strPoster = json.optString("Poster", "");

                        InputStream moviePosterInputStream = (InputStream) new URL(strPoster).getContent();
                        Drawable moviePosterDrawable = Drawable.createFromStream(moviePosterInputStream, "src name");

                        MovieModel movieData = new MovieModel(strMovieTitle, strMovieStudio, strMovieRating, strMovieYear, strMovieDescription, moviePosterDrawable, strPoster);

                        List<MovieModel> currentList = movieList.getValue();
                        if (currentList != null) {
                            currentList.add(movieData);
                            movieList.postValue(currentList);
                        }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private final android.os.Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateView();
        }
    };
}
