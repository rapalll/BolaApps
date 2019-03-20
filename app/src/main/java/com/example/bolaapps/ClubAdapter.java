package com.example.bolaapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bolaapps.ClubModel.TeamsItem;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubHolder> {

    List<TeamsItem> clubItem;

    public ClubAdapter (List<TeamsItem> clubItem){
        this.clubItem = clubItem;
    }

    @NonNull
    @Override
    public ClubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_club, parent , false);
        return new ClubHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClubHolder holder, final int position) {
        String nameClub = clubItem.get(position).getStrTeam();
//        String imageClub = clubItem.get(position).getStrTeamLogo();
        String ligaClub = clubItem.get(position).getStrLeague();


        Picasso.with(holder.itemView.getContext())
                .load(clubItem.get(position).getStrTeamBadge())
                .into(holder.clubImage);

        holder.clubLiga.setText(ligaClub);
        holder.clubName.setText(nameClub);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamsItem teamsItem = clubItem.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailClubActivity.class);
                intent.putExtra("club", new GsonBuilder().create().toJson(teamsItem));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    public void setData (List<TeamsItem> clubItem){
        this.clubItem = clubItem;
    }

    @Override
    public int getItemCount() {
        return clubItem.size();
    }


    class ClubHolder extends RecyclerView.ViewHolder{

        ImageView clubImage;
        TextView clubName;
        TextView clubLiga;

        public ClubHolder(@NonNull View itemView) {
            super(itemView);
            clubImage   = itemView.findViewById(R.id.clubImg);
            clubName    = itemView.findViewById(R.id.tv_clubName);
            clubLiga    = itemView.findViewById(R.id.tv_clubLiga);
        }
    }
}
