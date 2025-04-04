package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.entities.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    public interface TeamClickListener {
        void onTeamClick(int position);
        void onEditClick(Team team);
        void onDeleteClick(Team team);

        void onAddClick();
    }

    private Context context;
    private List<Team> teams;

    private TeamClickListener listener;


    public TeamAdapter(Context context, List<Team> teams, TeamClickListener listener) {
        this.context = context;
        this.teams = teams;

        this.listener = listener;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_item, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText(team.getName());
        int imgId = context.getResources().getIdentifier(team.getImagePath(), "drawable", context.getPackageName());
        if (imgId == 0) {
            Glide.with(this.context)
                    .load(team.getImagePath())
                    .placeholder(R.drawable.loading)
                    .into(holder.teamImage);
        } else {
            holder.teamImage.setImageResource(imgId);
        }

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(team);
            }
        });

        holder.editButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(team);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        ImageView teamImage;
        TextView teamName;
        ImageButton editButton, deleteButton;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamImage = itemView.findViewById(R.id.teamImage);
            teamName = itemView.findViewById(R.id.teamName);
            editButton = itemView.findViewById(R.id.editTeam);
            deleteButton = itemView.findViewById(R.id.deleteTeam);

            itemView.setOnClickListener(v -> listener.onTeamClick(getAdapterPosition()));
        }
    }
}