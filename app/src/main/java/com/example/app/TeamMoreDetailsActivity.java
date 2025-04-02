package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;

public class TeamMoreDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_more_details);

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Get data from intent
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("teamName");
        String driver1Name = intent.getStringExtra("driver1Name");
        String driver1Image = intent.getStringExtra("driver1Image");
        String driver2Name = intent.getStringExtra("driver2Name");
        String driver2Image = intent.getStringExtra("driver2Image");
        String teamPrincipal = intent.getStringExtra("teamPrincipal");
        int yearFounded = intent.getIntExtra("yearFounded", 0);
        String country = intent.getStringExtra("country");
        int imgId = 0;

        ((TextView) findViewById(R.id.driver1Name)).setText(driver1Name);
        imgId = this.getResources().getIdentifier(driver1Image, "drawable", this.getPackageName());
        ImageView driver1ImageView = findViewById(R.id.driver1Image);
        if (imgId == 0) {
            Glide.with(this)
                    .load(driver1Image)
                    .placeholder(R.drawable.loading)
                    .into(driver1ImageView);
        } else {
            driver1ImageView.setImageResource(imgId);
        }
        ((TextView) findViewById(R.id.driver2Name)).setText(driver2Name);
        imgId = this.getResources().getIdentifier(driver2Image, "drawable", this.getPackageName());
        ImageView driver2ImageView = findViewById(R.id.driver2Image);
        if (imgId == 0) {
            Glide.with(this)
                    .load(driver2Image)
                    .placeholder(R.drawable.loading)
                    .into(driver2ImageView);
        } else {
            driver2ImageView.setImageResource(imgId);
        }
        String founded = String.valueOf(yearFounded);
        ((TextView) findViewById(R.id.year)).setText(founded);
        ((TextView) findViewById(R.id.country)).setText(country);
        ((TextView) findViewById(R.id.principal)).setText(teamPrincipal);
    }
}
