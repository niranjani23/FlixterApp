package com.codepath.flixterapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.codepath.flixterapp.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.codepath.flixterapp.R.id.lv_Movies;

public class MovieActivity extends AppCompatActivity {
    ArrayList<Movie> movies;

    RVAdapter adapter ;

    RecyclerView lvItems;

    ImageView ivMovie ;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context =  this;

        setContentView(R.layout.activity_movie);

        lvItems = (RecyclerView)findViewById(lv_Movies);
        movies = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        lvItems.setLayoutManager(llm);

        adapter = new RVAdapter(this, movies);
        lvItems.setAdapter(adapter);

        ivMovie = (ImageView) findViewById(R.id.ivMovieImage);
        //set a onclick listener for when the button gets clicked
        ivMovie.setOnClickListener(new View.OnClickListener() {
            //Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(context, MovieDetails.class);
                startActivity(mainIntent);
            }
        });

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                Log.v("success", String.valueOf(response));
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    Log.v("movieSizeActivity", String.valueOf(movies));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("DEBUG", movies.toString());
            }

        });

    }



}
