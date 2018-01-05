package com.example.prerna.songsdemo;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    // URL to get contacts JSON
    private static String url = "https://itunes.apple.com/search?term=Michael+jackson";
    private static String data;
    ArrayList<Michal> michalArrayList;

    MichalAdapter michalAdapter;
    String image, collection, track, genre, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        michalArrayList = new ArrayList();

        //calling method for json data
        getData();

        // listview item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Michal michal = michalAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("image", michal.getImage());
                intent.putExtra("collection", michal.getCollection());
                intent.putExtra("genre", michal.getGenre());
                intent.putExtra("track", michal.getTrack());
                intent.putExtra("date", michal.getDate());

                startActivity(intent);

            }
        });
    }

    //method parsing json response

    public void settingData() {
        try {

            JSONObject result = new JSONObject(data);
            JSONArray routearray = result.getJSONArray("results");

            for (int i = 0; i < routearray.length(); i++) {
//            for (int i = 0; i < 20; i++) {

                if (routearray.getJSONObject(i).has("collectionName") &&
                        routearray.getJSONObject(i).has("trackName") &&
                        routearray.getJSONObject(i).has("releaseDate") &&
                        routearray.getJSONObject(i).has("primaryGenreName") &&
                        routearray.getJSONObject(i).has("artworkUrl100")) {
                    collection = routearray.getJSONObject(i).getString("collectionName");
                    track = routearray.getJSONObject(i).getString("trackName");
                    date = routearray.getJSONObject(i).getString("releaseDate");
                    genre = routearray.getJSONObject(i).getString("primaryGenreName");
                    image = routearray.getJSONObject(i).getString("artworkUrl100");

                    Michal m = new Michal();
                    m.setCollection(collection);
                    m.setDate(date);
                    m.setTrack(track);
                    m.setGenre(genre);
                    m.setImage(image);

                    michalArrayList.add(m);
                }
            }
//setting data on list
            michalAdapter = new MichalAdapter(this, R.layout.list_item, michalArrayList);
            listView.setAdapter(michalAdapter);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR", "settingData: ", e.getCause());
        }

    }

    //method of getting response
    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        data = response.trim();
                        settingData();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
