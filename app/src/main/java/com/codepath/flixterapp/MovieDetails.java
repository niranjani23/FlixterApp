package com.codepath.flixterapp;

import android.os.Bundle;
import android.util.Log;

import com.codepath.flixterapp.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by niranjani on 3/23/17.
 */

public class MovieDetails {
    public void onCreate(Bundle savedInstanceState) {

        String url = "https://api.themoviedb.org/3/movie/209112/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                Log.v("success", String.valueOf(response));

            }

        });
    }
}
