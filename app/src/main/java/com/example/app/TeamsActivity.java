package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.entities.Team;
import com.example.app.utils.XMLTeamParser;

import java.util.ArrayList;
import java.util.List;

public class TeamsActivity extends AppCompatActivity implements TeamAdapter.TeamClickListener {

    RecyclerView recyclerView;
    TeamAdapter adapter;
    List<Team> teams;

    XMLTeamParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        recyclerView = findViewById(R.id.teamRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.parser = new XMLTeamParser(this);

        teams = this.parser.getTeams();

//        teams = new ArrayList<>();
//        teams.add(new Team("Ferrari", R.drawable.ferrari));
//        teams.add(new Team("Red bull", R.drawable.redbull));
//        teams.add(new Team("Mclaren", R.drawable.mclaren));
//        teams.add(new Team("Mercedes", R.drawable.mercedes));
//        teams.add(new Team("Williams", R.drawable.williams));
//        teams.add(new Team("Aston Martin", R.drawable.aston));
//        teams.add(new Team("Haas", R.drawable.haas));
//        teams.add(new Team("Visa CashApp Racing Bulls", R.drawable.vcarb));
//        teams.add(new Team("Kick Sauber", R.drawable.sauber));
//        teams.add(new Team("Alpine", R.drawable.alpine));

        adapter = new TeamAdapter(this, teams, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTeamClick(int position) {
        Intent intent = new Intent(this, TeamDetailActivity.class);

        Team team = teams.get(position);
        int imgDriver1Id = this.getResources().getIdentifier(team.getDriver1().getImagePath(), "drawable", this.getPackageName());
        int imgDriver2Id = this.getResources().getIdentifier(team.getDriver2().getImagePath(), "drawable", this.getPackageName());

        int a = R.drawable.hellyeah;

        intent.putExtra("teamName", team.getName());
        intent.putExtra("teamLogo", team.getImagePath()); // Passing the image
        intent.putExtra("driver1Name", team.getDriver1().getName());
        intent.putExtra("driver1Image", imgDriver1Id);
        intent.putExtra("driver1Number", team.getDriver1().getNumber());
        intent.putExtra("driver2Name", team.getDriver2().getName());
        intent.putExtra("driver2Image", imgDriver2Id);
        intent.putExtra("driver2Number", team.getDriver2().getNumber());
        intent.putExtra("wins", team.getWins());
        intent.putExtra("podiums", team.getPodiums());
        intent.putExtra("championships", team.getChampionships());
        intent.putExtra("url", team.getUrl());
        intent.putExtra("teamPrincipal", team.getTeamPrincipal());
        intent.putExtra("country", team.getCountry());
        intent.putExtra("yearFounded", team.getFoundedYear());


        startActivity(intent);
    }
}