package com.example.android.jsonproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.android.jsonproject.MainActivity.EXTRA_URL;
import static com.example.android.jsonproject.MainActivity.EXTRA_TITLE;
import static com.example.android.jsonproject.MainActivity.EXTRA_OVERVIEW;
import static com.example.android.jsonproject.MainActivity.EXTRA_VOTE_AVERAGE;
import static com.example.android.jsonproject.MainActivity.EXTRA_RELEASE_DATA;

public class Details extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_screen);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String overView = intent.getStringExtra(EXTRA_OVERVIEW);
        int voteCount = intent.getIntExtra(EXTRA_VOTE_AVERAGE , 0);
        String releaseData = intent.getStringExtra(EXTRA_RELEASE_DATA);

        ImageView detailsPoster = findViewById(R.id.details_poster_image);
        TextView detailsTitle = findViewById(R.id.details_original_title_text_view);
        TextView detailsOverView = findViewById(R.id.details_overview_tv);
        TextView detailsVoteAverage = findViewById(R.id.details_vote_average_tv);
        TextView detailsReleaseData = findViewById(R.id.details_release_date_tv);

        Picasso.with(this).load(imageUrl).fit().into(detailsPoster);
        detailsTitle.setText(title);
        detailsOverView.setText(overView);
        detailsVoteAverage.setText("Num Of votes " + voteCount);
        detailsReleaseData.setText("Release Data = " + releaseData);

    }
}
