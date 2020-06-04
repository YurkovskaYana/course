package com.example.core.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.data.LeagueItem;
import com.example.core.data.Repository;
import com.example.core.data.TeamDetailInfoItem;

import java.util.List;

public class TeamDetailInfoViewModel extends ViewModel {
    Repository repository;

    public TeamDetailInfoViewModel(){
        repository = Repository.getInstance();
    }

    public LiveData<List<TeamDetailInfoItem>> getTeamDetailInfoKind() {
        return repository.getTeamDetailInfoKind();
    }

    public void callAllTeamDetailInfo(String id){
        repository.callApiAllTeamDetailInfo(id);
    }
}
