package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        String driver1Name = intent.getStringExtra("driver1Name");
        int driver1Image = intent.getIntExtra("driver1Image", R.drawable.hellyeah);
        String driver2Name = intent.getStringExtra("driver2Name");
        int driver2Image = intent.getIntExtra("driver2Image", R.drawable.hellyeah);
        String teamPrincipal = intent.getStringExtra("teamPrincipal");
        int yearFounded = intent.getIntExtra("yearFounded", 0);
        String country = intent.getStringExtra("country");

        // le iau dintr un fisier

        // Set values
        ((TextView) findViewById(R.id.driver1Name)).setText(driver1Name);
        ((ImageView) findViewById(R.id.driver1Image)).setImageResource(driver1Image);
        ((TextView) findViewById(R.id.driver2Name)).setText(driver2Name);
        ((ImageView) findViewById(R.id.driver2Image)).setImageResource(driver2Image);
        ((TextView) findViewById(R.id.year)).setText(String.valueOf(yearFounded));
        ((TextView) findViewById(R.id.country)).setText(country);
        ((TextView) findViewById(R.id.principal)).setText(teamPrincipal);
    }
}
