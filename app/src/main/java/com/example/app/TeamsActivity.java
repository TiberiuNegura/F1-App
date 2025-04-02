package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.entities.Team;
import com.example.app.utils.XMLTeamManager;

import java.util.List;

public class TeamsActivity extends AppCompatActivity implements TeamAdapter.TeamClickListener {

    RecyclerView recyclerView;
    TeamAdapter adapter;
    List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        recyclerView = findViewById(R.id.teamRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        XMLTeamManager.readXml(this, "teams.xml");

        teams = XMLTeamManager.getTeams();

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

        ImageButton addButton = findViewById(R.id.addTeam);
        addButton.setOnClickListener(v -> {
            onAddClick();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        teams = XMLTeamManager.getTeams();
        adapter.setTeams(teams);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTeamClick(int position) {
        Intent intent = new Intent(this, TeamDetailActivity.class);

        Team team = teams.get(position);
        int imgDriver1Id = this.getResources().getIdentifier(team.getDriver1().getImagePath(), "drawable", this.getPackageName());
        int imgDriver2Id = this.getResources().getIdentifier(team.getDriver2().getImagePath(), "drawable", this.getPackageName());

        intent.putExtra("teamName", team.getName());
        intent.putExtra("teamLogo", team.getImagePath()); // Passing the image
        intent.putExtra("driver1Name", team.getDriver1().getName());
        intent.putExtra("driver1Image", team.getDriver1().getImagePath());
        intent.putExtra("driver1Number", team.getDriver1().getNumber());
        intent.putExtra("driver2Name", team.getDriver2().getName());
        intent.putExtra("driver2Image", team.getDriver2().getImagePath());
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

    @Override
    public void onEditClick(Team team) {
        Intent intent = new Intent(this, AddTeamActivity.class);
        intent.putExtra("isEdit", true);

        intent.putExtra("teamName", team.getName());
        intent.putExtra("teamLogo", team.getImagePath());
        intent.putExtra("wins", team.getWins());
        intent.putExtra("podiums", team.getPodiums());
        intent.putExtra("championships", team.getChampionships());
        intent.putExtra("yearFounded", team.getFoundedYear());
        intent.putExtra("teamPrincipal", team.getTeamPrincipal());
        intent.putExtra("country", team.getCountry());
        intent.putExtra("web_url", team.getUrl());
        intent.putExtra("driver1Name", team.getDriver1().getName());
        intent.putExtra("driver1Number", team.getDriver1().getNumber());
        intent.putExtra("driver1Image", team.getDriver1().getImagePath());
        intent.putExtra("driver2Name", team.getDriver2().getName());
        intent.putExtra("driver2Number", team.getDriver2().getNumber());
        intent.putExtra("driver2Image", team.getDriver2().getImagePath());

        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDeleteClick(Team team) {
        teams.remove(team);
        XMLTeamManager.saveTeams(teams, this);
        adapter.setTeams(teams);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAddClick() {
        Intent intent = new Intent(TeamsActivity.this, AddTeamActivity.class);
        startActivity(intent);
    }
}