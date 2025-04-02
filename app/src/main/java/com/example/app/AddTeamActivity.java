package com.example.app;

import android.os.Bundle;
import android.util.Xml;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.entities.Driver;
import com.example.app.entities.Team;
import com.example.app.utils.XMLTeamManager;

public class AddTeamActivity extends AppCompatActivity {
    private EditText teamNameInput, teamImageInput, teamWinsInput, teamPodiumsInput,
            teamChampionshipsInput, teamFoundedInput, teamCountryInput, teamPrincipalInput,
            driver1NameInput, driver1NumberInput, driver1ImageInput,
            driver2NameInput, driver2NumberInput, driver2ImageInput, teamWebPage;

    private Team currentTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

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

        boolean isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit) {
            teamNameInput.setText(getIntent().getStringExtra("teamName"));
            teamImageInput.setText(getIntent().getStringExtra("teamLogo"));
            teamWinsInput.setText(String.valueOf(getIntent().getIntExtra("wins", 0)));
            teamPodiumsInput.setText(String.valueOf(getIntent().getIntExtra("podiums", 0)));
            teamChampionshipsInput.setText(String.valueOf(getIntent().getIntExtra("championships", 0)));
            teamFoundedInput.setText(String.valueOf(getIntent().getIntExtra("yearFounded", 0)));
            teamPrincipalInput.setText(getIntent().getStringExtra("teamPrincipal"));
            teamCountryInput.setText(getIntent().getStringExtra("country"));
            teamWebPage.setText(getIntent().getStringExtra("web_url"));
            driver1NameInput.setText(getIntent().getStringExtra("driver1Name"));
            driver1NumberInput.setText(String.valueOf(getIntent().getIntExtra("driver1Number", 0)));
            driver1ImageInput.setText(getIntent().getStringExtra("driver1Image"));
            driver2NameInput.setText(getIntent().getStringExtra("driver2Name"));
            driver2NumberInput.setText(String.valueOf(getIntent().getIntExtra("driver2Number", 0)));
            driver2ImageInput.setText(getIntent().getStringExtra("driver2Image"));

            Driver driver1 = new Driver(
                    getIntent().getStringExtra("driver1Name"),
                    getIntent().getIntExtra("driver1Number", 0),
                    getIntent().getStringExtra("driver1Image")
            );
            Driver driver2 = new Driver(
                    getIntent().getStringExtra("driver2Name"),
                    getIntent().getIntExtra("driver2Number", 0),
                    getIntent().getStringExtra("driver2Image")
            );

            currentTeam = new Team(
                    getIntent().getStringExtra("teamName"),
                    getIntent().getStringExtra("teamLogo"),
                    getIntent().getIntExtra("wins", 0),
                    getIntent().getIntExtra("podiums", 0),
                    getIntent().getIntExtra("championships", 0),
                    getIntent().getIntExtra("yearFounded", 0),
                    getIntent().getStringExtra("teamPrincipal"),
                    driver1,
                    driver2,
                    getIntent().getStringExtra("web_url"),
                    getIntent().getStringExtra("country")
            );
        }

        Button confirmButton = findViewById(R.id.confirmAddTeam);
        confirmButton.setOnClickListener(v -> {
            Driver driver1 = new Driver(driver1NameInput.getText().toString(), Integer.parseInt(driver1NumberInput.getText().toString()), driver1ImageInput.getText().toString());
            Driver driver2 = new Driver(driver2NameInput.getText().toString(), Integer.parseInt(driver2NumberInput.getText().toString()), driver2ImageInput.getText().toString());
            Team team = new Team(
                    teamNameInput.getText().toString(),
                    teamImageInput.getText().toString(),
                    Integer.parseInt(teamWinsInput.getText().toString()),
                    Integer.parseInt(teamPodiumsInput.getText().toString()),
                    Integer.parseInt(teamChampionshipsInput.getText().toString()),
                    Integer.parseInt(teamFoundedInput.getText().toString()),
                    teamPrincipalInput.getText().toString(),
                    driver1,
                    driver2,
                    teamWebPage.getText().toString(),
                    teamCountryInput.getText().toString()
            );

            if(!isEdit) {
                XMLTeamManager.addTeam(team, this, "teams.xml");
            } else {
                XMLTeamManager.editTeam(currentTeam, team, this);
            }

            finish();
        });
    }
}
