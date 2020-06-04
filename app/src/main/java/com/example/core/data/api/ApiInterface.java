package com.example.core.data.api;

import com.example.core.data.ResponseAllLeague;
import com.example.core.data.ResponseAllSports;
import com.example.core.data.ResponseEachTeamForLeg;
import com.example.core.data.ResponseTeamDetailInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search_all_leagues.php?")
    Call<ResponseAllLeague> getAllLeague(@Query("s") String kindOfSportForQuery);

    @GET("all_sports.php")
    Call<ResponseAllSports> getAllSpots();


    /*@GET("eventspastleague.php?")
    Call<ResponseAllEvents> getAllEvent(@Query("id") String id);*/

    @GET("lookupteam.php?")
    Call<ResponseTeamDetailInfo> getTeamDetailInfo(@Query("id") String id);

    @GET("search_all_teams.php?")
    Call<ResponseEachTeamForLeg> getTeamsInLeague(@Query("s") String kindOfSportForQuery, @Query("c") String country);
}
