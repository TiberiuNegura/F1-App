package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.entities.Team;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    public interface TeamClickListener {
        void onTeamClick(int position);
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
        holder.teamImage.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        ImageView teamImage;
        TextView teamName;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamImage = itemView.findViewById(R.id.teamImage);
            teamName = itemView.findViewById(R.id.teamName);

            itemView.setOnClickListener(v -> listener.onTeamClick(getAdapterPosition()));
        }
    }
}