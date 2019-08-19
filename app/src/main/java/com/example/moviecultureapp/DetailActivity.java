package com.example.moviecultureapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity  extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();
        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        nameOfMovie = (TextView) findViewById(R.id.title);
        plotSynopsis = (TextView) findViewById(R.id.plotSynopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        releaseDate = (TextView) findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("original_title")){
            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this).load(thumbnail)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);

        }else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + i == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
