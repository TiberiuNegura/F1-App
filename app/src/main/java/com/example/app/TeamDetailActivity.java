package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeamDetailActivity extends AppCompatActivity {

    TextView teamName, driver1, driver2, wins, podiums, championships;
    ImageView teamLogo;

    Button seeMoreButton, webButton;
    private ImageButton backButton; // Added back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        // Initialize Views
        teamName = findViewById(R.id.teamName);
        teamLogo = findViewById(R.id.teamLogo);
        driver1 = findViewById(R.id.driver1);
        driver2 = findViewById(R.id.driver2);
        wins = findViewById(R.id.wins);
        podiums = findViewById(R.id.podiums);
        championships = findViewById(R.id.championships);

        // Retrieve data from intent
        Intent intent = getIntent();

        String teamNameStr = intent.getStringExtra("teamName");
        int teamLogoResId = intent.getIntExtra("teamLogo", R.drawable.ferrari); // Default placeholder
        String driver1Name = intent.getStringExtra("driver1Name");
        int driver1Number = intent.getIntExtra("driver1Number", 0);
        int driver1Image = intent.getIntExtra("driver1Image", R.drawable.hellyeah);
        String driver2Name = intent.getStringExtra("driver2Name");
        int driver2Number = intent.getIntExtra("driver2Number", 0);
        int driver2Image = intent.getIntExtra("driver2Image", R.drawable.hellyeah);
        int winsCount = intent.getIntExtra("wins", 0);
        int podiumsCount = intent.getIntExtra("podiums", 0);
        int championshipsCount = intent.getIntExtra("championships", 0);
        String url = intent.getStringExtra("url");
        String teamPrincipal = intent.getStringExtra("teamPrincipal");
        int yearFounded = intent.getIntExtra("yearFounded", 0);
        String country = intent.getStringExtra("country");

        // Set data
        teamName.setText(teamNameStr);
        teamLogo.setImageResource(teamLogoResId);
        driver1.setText( driver1Name + "(" + driver1Number + ")");
        driver2.setText( driver2Name + "(" + driver2Number + ")");
        wins.setText("Wins: " + winsCount);
        podiums.setText("Podiums: " + podiumsCount);
        championships.setText("Championships: " + championshipsCount);

        seeMoreButton = findViewById(R.id.seeMoreButton);
        webButton = findViewById(R.id.goToWebButton);

        seeMoreButton.setOnClickListener(v -> {
            Intent intentMore = new Intent(TeamDetailActivity.this, TeamMoreDetailsActivity.class);
            intentMore.putExtra("teamName", teamNameStr);
            intentMore.putExtra("driver1Name", driver1Name);
            intentMore.putExtra("driver1Image", driver1Image);
            intentMore.putExtra("driver2Name", driver2Name);
            intentMore.putExtra("driver2Image", driver2Image);
            intentMore.putExtra("teamPrincipal", teamPrincipal);
            intentMore.putExtra("yearFounded", yearFounded);
            intentMore.putExtra("country", country);

            startActivity(intentMore);
        });

        webButton.setOnClickListener(v -> {
            Intent intentWeb = new Intent(TeamDetailActivity.this, WebInfoActivity.class);
            intentWeb.putExtra("webUrl", url);
            startActivity(intentWeb);
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}