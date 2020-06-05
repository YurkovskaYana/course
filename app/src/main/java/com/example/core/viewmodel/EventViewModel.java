package com.example.core.viewmodel;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.data.EventItem;
import com.example.core.data.LeagueItem;
import com.example.core.data.Repository;

import java.util.List;

public class EventViewModel extends ViewModel {
    Repository repository;

    public EventViewModel(){
        repository = Repository.getInstance();
    }

    public LiveData<List<EventItem>> getEventKind() {
        return repository.getEventKind();
    }

    public void callAllEvents(String id){
        repository.callApiAllEvents(id);
    }

    public void callTeamDetailInEvent(String id, ImageView iv){
        repository.callApiLookupItem(id, iv);
    }
}
