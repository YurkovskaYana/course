package com.example.core.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.data.EachTeamItem;
import com.example.core.data.LeagueItem;
import com.example.core.data.Repository;

import java.util.List;

public class TeamsInLeagueViewModel extends ViewModel {

    Repository repository;

    public TeamsInLeagueViewModel(){
        repository = Repository.getInstance();
    }

    public LiveData<List<EachTeamItem>> getTeamsInLeagueKind() {
        return repository.getTeamsInLeagueKind();
    }

    public void callAllTeamsInLeague(String s, String c){
        repository.callApiAllTeamsInLeague(s,c);
    }
}
