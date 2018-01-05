package com.example.prerna.songsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String image, collection, track, genre, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

// getting data from other activity
        image = getIntent().getStringExtra("image");
        collection = getIntent().getStringExtra("collection");
        track = getIntent().getStringExtra("track");
        genre = getIntent().getStringExtra("genre");
        date = getIntent().getStringExtra("date");

        //finding ids

        ImageView imageV = (ImageView) findViewById(R.id.imageview);
        TextView collectionV = (TextView) findViewById(R.id.collection);
        TextView trackV = (TextView) findViewById(R.id.track);
        TextView genreV = (TextView) findViewById(R.id.genre);
        TextView relaese_dateV = (TextView) findViewById(R.id.relaese_date);

// setting data
        Picasso.with(this).load(image).fit().into(imageV);
        collectionV.setText(collection);
        genreV.setText(genre);
        trackV.setText(track);
        relaese_dateV.setText(date);

    }
}
