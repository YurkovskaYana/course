package com.example.core.data;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.core.data.api.ApClient;
import com.example.core.data.api.ApiInterface;
import com.example.core.ui.TeamsInLeagueActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String TAG = "Repository";

    ApiInterface mApi;
    private MutableLiveData<List<SportItem>>  sportsKind;
    private MutableLiveData<List<LeagueItem>>  leagueKind;
    private MutableLiveData<List<EachTeamItem>>  teamsInLeagueKind;
    private MutableLiveData<List<TeamDetailInfoItem>>  teamDetailInfoKind;
    private MutableLiveData<List<EventItem>>  eventKind;


    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public Repository(){
        mApi= ApClient.getApiClient().create(ApiInterface.class);
        sportsKind = new MutableLiveData<>();
        leagueKind = new MutableLiveData<>();
        teamsInLeagueKind = new MutableLiveData<>();
        teamDetailInfoKind = new  MutableLiveData<>();
        eventKind = new  MutableLiveData<>();
    }

    public LiveData<List<SportItem>> getSportsKind() {
        return sportsKind;
    }

    public LiveData<List<LeagueItem>> getLeagueKind() {
        return leagueKind;
    }

    public LiveData<List<EachTeamItem>> getTeamsInLeagueKind() {
        return teamsInLeagueKind;
    }

    public LiveData<List<TeamDetailInfoItem>> getTeamDetailInfoKind() {
        return teamDetailInfoKind;
    }

    public LiveData<List<EventItem>> getEventKind() {
        return eventKind;
    }



    public void callAllSports(){
        Call<ResponseAllSports> api=mApi.getAllSpots();
        api.enqueue(new Callback<ResponseAllSports>() {
            @Override
            public void onResponse(Call<ResponseAllSports> call, Response<ResponseAllSports> response) {
                if (response.isSuccessful()){
                    List<SportItem> sportItems = new ArrayList<>(response.body().getSports());
                    sportsKind.postValue(sportItems);
                } else {
                    String error = String.valueOf(response.errorBody());
                    Log.e(TAG, "callAllSports: " + error);
                }
            }
            @Override
            public void onFailure(Call<ResponseAllSports> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void callApiAllLeague(String kindOfSportForQuery){
        Call<ResponseAllLeague> api=mApi.getAllLeague(kindOfSportForQuery);
        api.enqueue(new Callback<ResponseAllLeague>() {
            @Override
            public void onResponse(Call<ResponseAllLeague> call, Response<ResponseAllLeague> response) {
                if (response.isSuccessful()){
                    List<LeagueItem> leagueItems = new ArrayList<>(response.body().getLeague());
                    for (Iterator<LeagueItem> it = leagueItems.iterator(); it.hasNext(); ) {
                        LeagueItem item = it.next();
                        if (item.getStrCountry().equals("Europe")) {
                            it.remove();
                        }
                    }
                    leagueKind.postValue(leagueItems);
                }else {
                    String error = String.valueOf(response.errorBody());
                    Log.e(TAG, "callApiAllLeague: " + error);
                }
            }

            @Override
            public void onFailure(Call<ResponseAllLeague> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void callApiAllTeamsInLeague(String s, String c){
        Call<ResponseEachTeamForLeg> api=mApi.getTeamsInLeague(s,c);
        api.enqueue(new Callback<ResponseEachTeamForLeg>() {
            @Override
            public void onResponse(Call<ResponseEachTeamForLeg> call, Response<ResponseEachTeamForLeg> response) {
                if(response.body().getEachTeams() !=null){
                    if (response.isSuccessful()){
                        List<EachTeamItem> eachTeamItems = new ArrayList<>(response.body().getEachTeams());
                        teamsInLeagueKind.postValue(eachTeamItems);
                    }else {
                        String error = String.valueOf(response.errorBody());
                        Log.e(TAG, "callApiAllLeague: " + error);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseEachTeamForLeg> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void callApiAllTeamDetailInfo(String id){
        Call<ResponseTeamDetailInfo> api=mApi.getTeamDetailInfo(id);
        api.enqueue(new Callback<ResponseTeamDetailInfo>() {
            @Override
            public void onResponse(Call<ResponseTeamDetailInfo> call, Response<ResponseTeamDetailInfo> response) {
                if (response.isSuccessful()){
                    List<TeamDetailInfoItem> teamDetailInfoItems = new ArrayList<>(response.body().getTeamDetailInfoItem());
                    teamDetailInfoKind.postValue(teamDetailInfoItems);
                }else {
                    String error = String.valueOf(response.errorBody());
                    Log.e(TAG, "callApiAllLeague: " + error);
                }
            }

            @Override
            public void onFailure(Call<ResponseTeamDetailInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void callApiAllEvents(String id){
        Call<ResponseEvents> api=mApi.getEvents(id);
        api.enqueue(new Callback<ResponseEvents>() {
            @Override
            public void onResponse(Call<ResponseEvents> call, Response<ResponseEvents> response) {
                if (response.isSuccessful()){
                    List<EventItem> events = new ArrayList<>(response.body().getEvent());
                    eventKind.postValue(events);
                }else {
                    String error = String.valueOf(response.errorBody());
                    Log.e(TAG, "callApiAllEv: " + error);
                }
            }

            @Override
            public void onFailure(Call<ResponseEvents> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void callApiLookupItem(String id, final ImageView ivteam) {

        Call<ResponseLookupTeam> api=mApi.getLookupTeam(id);

        api.enqueue(new Callback<ResponseLookupTeam>() {
            @Override
            public void onResponse(Call<ResponseLookupTeam> call, Response<ResponseLookupTeam> response) {
                if (response.isSuccessful()){
                    Picasso.get()
                            .load(response.body().getTeams().get(0).getStrTeamBadge())
                            .into(ivteam);
                }
            }

            @Override
            public void onFailure(Call<ResponseLookupTeam> call, Throwable t) {

            }
        });

    }




}
