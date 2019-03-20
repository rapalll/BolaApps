package com.example.bolaapps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.bolaapps.ClubModel.TeamsItem;
import com.google.gson.GsonBuilder;

public class DetailClubActivity extends AppCompatActivity {


    TeamsItem teamsItem;
//    Toolbar toolbar;
    ImageView clubImg, clubPoster;
    TextView nameClub, nameLiga, nameManager, nameStadium, nameDetalClub,
            locatinStadium, capacityStadium, countryName, webOfficial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_club);

//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        clubImg = findViewById(R.id.imgClub);
        nameClub = findViewById(R.id.tv_teamName);
        nameLiga = findViewById(R.id.tv_LigaName);
        nameManager = findViewById(R.id.tv_ManagerName);
        nameStadium = findViewById(R.id.tv_StadiumName);
        nameDetalClub = findViewById(R.id.tv_DescClub);
        locatinStadium = findViewById(R.id.tv_LocationName);
        capacityStadium = findViewById(R.id.tv_Capacity_Stadium);
        countryName = findViewById(R.id.tv_AsalNegara);
        webOfficial = findViewById(R.id.tv_WebOfficial);

        loadDataClub();
    }
    private void loadDataClub(){
        teamsItem = new GsonBuilder().create().fromJson(getIntent().getStringExtra("club"), TeamsItem.class);
//        toolbar.setTitle(teamsItem.getStrTeam());
        Glide.with(DetailClubActivity.this).load(teamsItem.getStrTeamBadge()).into(clubImg);
//        Glide.with(DetailClubActivity.this).load(teamsItem.getStrTeamBadge()).into(clubPoster);
        nameClub.setText(teamsItem.getStrTeam());
        nameLiga.setText(teamsItem.getStrLeague());
        nameManager.setText(teamsItem.getStrManager());
        nameStadium.setText(teamsItem.getStrStadium());
        nameDetalClub.setText(teamsItem.getStrDescriptionEN());
        locatinStadium.setText(teamsItem.getStrStadiumLocation());
        capacityStadium.setText(teamsItem.getIntStadiumCapacity());
        countryName.setText(teamsItem.getStrCountry());
        webOfficial.setText(teamsItem.getStrWebsite());
    }
}
