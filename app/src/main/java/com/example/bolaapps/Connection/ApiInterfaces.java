package com.example.bolaapps.Connection;

import com.example.bolaapps.ClubModel.ClubResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaces {

    @GET("api/v1/json/1/lookup_all_teams.php?id=4335")
    Call<ClubResponse> getClub();

}
