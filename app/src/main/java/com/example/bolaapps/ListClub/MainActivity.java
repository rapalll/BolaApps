package com.example.bolaapps.ListClub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bolaapps.ClubAdapter;
import com.example.bolaapps.ClubModel.ClubResponse;
import com.example.bolaapps.ClubModel.TeamsItem;
import com.example.bolaapps.Connection.ApiClient;
import com.example.bolaapps.Connection.ApiInterfaces;
import com.example.bolaapps.DetailClubActivity;
import com.example.bolaapps.R;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClubAdapter adapter;
    ProgressDialog progressDialog;
    List<TeamsItem> teamsItems;
    Button btnShare, btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_club);
        progressDialog = new ProgressDialog(this);
////        button = findViewById(R.id.btn_detailClub);
//        btnShare = findViewById(R.id.btn_share);
//        btnFavorite = findViewById(R.id.btn_Favorite);
        init();
        loadData();

    }
    public void sendIntent (View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Yuk cek informasi bola di BolaApps");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void favorite (View view){
        Toast.makeText(MainActivity.this, "Favorite", Toast.LENGTH_SHORT).show();


    }
    private void init (){
        adapter = new ClubAdapter(teamsItems);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setItemViewCacheSize(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData(){
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiInterfaces apiInterfaces = ApiClient.getRetrofit().create(ApiInterfaces.class);
        Call<ClubResponse> clubResponseCall = apiInterfaces.getClub();
        clubResponseCall.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                if(response.isSuccessful()){
                    ClubResponse clubResponse = response.body();
                    adapter.setData(clubResponse.getTeams());
                    recyclerView.setAdapter(adapter);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ClubResponse> call, Throwable t) {

            }
        });
    }
}
