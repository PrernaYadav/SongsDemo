package com.example.prerna.songsdemo;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;


public class MichalAdapter extends ArrayAdapter<Michal> implements View.OnClickListener {
    ArrayList<Michal> michalArrayList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    public MichalAdapter(Context context, int resource, ArrayList<Michal> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        michalArrayList = objects;

    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        final Michal michal = michalArrayList.get(position);

        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.image1);
            holder.collection = (TextView) v.findViewById(R.id.tv_collection);
            holder.track = (TextView) v.findViewById(R.id.tv_track);
            holder.date = (TextView) v.findViewById(R.id.tv_date);
            holder.genre = (TextView) v.findViewById(R.id.tv_genre);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Picasso.with(this.getContext()).load(michal.getImage()).resize(100, 100).into(holder.imageview);
        holder.collection.setText(michalArrayList.get(position).getCollection());
        holder.track.setText(michalArrayList.get(position).getTrack());
        holder.date.setText(michalArrayList.get(position).getDate());
        holder.genre.setText(michalArrayList.get(position).getGenre());

        return v;

    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView collection;
        public TextView track;
        public TextView date;
        public TextView genre;

    }
}