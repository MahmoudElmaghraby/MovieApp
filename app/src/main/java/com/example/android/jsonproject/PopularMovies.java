package com.example.android.jsonproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.android.jsonproject.MainActivity.EXTRA_OVERVIEW;
import static com.example.android.jsonproject.MainActivity.EXTRA_TITLE;
import static com.example.android.jsonproject.MainActivity.EXTRA_URL;
import static com.example.android.jsonproject.MainActivity.EXTRA_VOTE_AVERAGE;
import static com.example.android.jsonproject.MainActivity.EXTRA_RELEASE_DATA;
import static com.example.android.jsonproject.MainActivity.MY_API_KEY;

public class PopularMovies extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    public void parseJSON() {

        String url = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + MY_API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject hit = jsonArray.optJSONObject(i);

                                String imageUrl = hit.getString("poster_path");
                                String creator = hit.getString("title");
                                String overView = hit.getString("overview");
                                int voteCount = hit.getInt("vote_count");
                                String releaseDate = hit.getString("release_date");

                                mExampleList.add(new ExampleItem(imageUrl, creator, overView, voteCount, releaseDate));

                            }


                            mExampleAdapter = new ExampleAdapter(PopularMovies.this, mExampleList);

                            if (isNetworkConnected()) {
                                mRecyclerView.setAdapter(mExampleAdapter);
                            }else {
                                Toast.makeText(PopularMovies.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                            }

                            mExampleAdapter.setOnItemClickListener(PopularMovies.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.top_rated:
                Intent intent1 = new Intent(PopularMovies.this , MainActivity.class);
                startActivity(intent1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, Details.class);
        startActivity(intent);

        Intent detailIntent = new Intent(this, Details.class);
        ExampleItem clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_OVERVIEW, clickedItem.getmOverView());
        detailIntent.putExtra(EXTRA_VOTE_AVERAGE, clickedItem.getmVote());
        detailIntent.putExtra(EXTRA_RELEASE_DATA, clickedItem.getmReleaseDate());

        startActivity(detailIntent);

    }

    public boolean isNetworkConnected(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null ;

    }

}
