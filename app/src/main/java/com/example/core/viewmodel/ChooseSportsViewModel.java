package com.example.core.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.data.Repository;
import com.example.core.data.ResponseAllSports;
import com.example.core.data.SportItem;
import com.example.core.data.api.ApClient;
import com.example.core.data.api.ApiInterface;
import com.example.core.ui.MainActivity;
import com.example.core.ui.adapters.SportsListAdapter;

import java.util.List;

public class ChooseSportsViewModel extends ViewModel {

    Repository repository;

    public ChooseSportsViewModel(){
        repository = Repository.getInstance();
    }

    public LiveData<List<SportItem>> getSportsKind() {
        return repository.getSportsKind();
    }


    public void callAllSports(){
        repository.callAllSports();
    }





}
