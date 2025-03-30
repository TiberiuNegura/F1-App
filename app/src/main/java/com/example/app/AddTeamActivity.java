package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.entities.Driver;
import com.example.app.entities.Team;
import com.example.app.utils.XMLTeamParser;
import com.google.android.material.appbar.MaterialToolbar;

public class AddTeamActivity extends AppCompatActivity {
    private EditText teamNameInput, teamImageInput, teamWinsInput, teamPodiumsInput,
            teamChampionshipsInput, teamFoundedInput, teamCountryInput, teamPrincipalInput,
            driver1NameInput, driver1NumberInput, driver1ImageInput,
            driver2NameInput, driver2NumberInput, driver2ImageInput, teamWebPage;

    private XMLTeamParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        parser = new XMLTeamParser(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // Initialize fields
        teamNameInput = findViewById(R.id.teamNameInput);
        teamImageInput = findViewById(R.id.teamImageInput);
        teamWinsInput = findViewById(R.id.teamWinsInput);
        teamPodiumsInput = findViewById(R.id.teamPodiumsInput);
        teamChampionshipsInput = findViewById(R.id.teamChampionshipsInput);
        teamFoundedInput = findViewById(R.id.teamFoundedInput);
        teamCountryInput = findViewById(R.id.teamCountryInput);
        teamPrincipalInput = findViewById(R.id.teamPrincipalInput);
        driver1NameInput = findViewById(R.id.driver1NameInput);
        driver1NumberInput = findViewById(R.id.driver1NumberInput);
        driver1ImageInput = findViewById(R.id.driver1ImageInput);
        driver2NameInput = findViewById(R.id.driver2NameInput);
        driver2NumberInput = findViewById(R.id.driver2NumberInput);
        driver2ImageInput = findViewById(R.id.driver2ImageInput);
        teamWebPage = findViewById(R.id.webPageInput);

        Button confirmButton = findViewById(R.id.confirmAddTeam);
        confirmButton.setOnClickListener(v -> {
            Driver driver1 = new Driver(driver1NameInput.toString(), Integer.parseInt(driver1NumberInput.toString()), driver1ImageInput.toString());
            Driver driver2 = new Driver(driver2NameInput.toString(), Integer.parseInt(driver2NumberInput.toString()), driver2ImageInput.toString());
            Team team = new Team(
                    teamNameInput.toString(),
                    teamImageInput.toString(),
                    Integer.parseInt(teamWinsInput.toString()),
                    Integer.parseInt(teamPodiumsInput.toString()),
                    Integer.parseInt(teamChampionshipsInput.toString()),
                    Integer.parseInt(teamFoundedInput.toString()),
                    teamPrincipalInput.toString(),
                    driver1,
                    driver2,
                    teamWebPage.toString(),
                    teamCountryInput.toString()
            );

            //parser.saveTeam(this, team, "input.xml");
        });
    }
}
