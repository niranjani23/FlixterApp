package com.codepath.flixterapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixterapp.R;
import com.codepath.flixterapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by niranjani on 3/12/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get data item for position
        Movie movie = getItem(position);

        //check the existing view being reused
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        // clear out image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        //populate data
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        //load image using Picasso library
        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);


        //return view
        return convertView;


    }
}
