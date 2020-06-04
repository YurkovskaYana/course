package com.example.core.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.data.LeagueItem;
import com.example.core.data.Repository;
import com.example.core.data.SportItem;

import java.util.List;

public class LeagueViewModel extends ViewModel {

    Repository repository;

    public LeagueViewModel(){
        repository = Repository.getInstance();
    }

    public LiveData<List<LeagueItem>> getLeagueKind() {
        return repository.getLeagueKind();
    }

    public void callAllLeagues(String kindOfSportForQuery){
        repository.callApiAllLeague(kindOfSportForQuery);
    }
}
