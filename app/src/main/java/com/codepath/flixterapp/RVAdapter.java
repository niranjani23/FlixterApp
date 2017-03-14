package com.codepath.flixterapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixterapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<Movie> movies;
    RVAdapter(Context context, List<Movie> movies){
        this.movies = movies;
        this.context = context;
    }
    private Context context;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivMovieImage;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView)itemView.findViewById(R.id.tvOverview);
            ivMovieImage = (ImageView)itemView.findViewById(R.id.ivMovieImage);
        }
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public int getItemCount() {
        Log.v("movieSize", String.valueOf(movies.size()));
        return movies.size();
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Log.v("onBind", String.valueOf(position));
        holder.tvTitle.setText(movies.get(position).getOriginalTitle());
        holder.tvOverview.setText(movies.get(position).getOverview());

        Picasso.with(context).load(movies.get(position).getPosterPath()).into(holder.ivMovieImage);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

