package com.example.core.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.core.R;
import com.example.core.data.SportItem;
import com.example.core.ui.adapters.SportsListAdapter;
import com.example.core.viewmodel.ChooseSportsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    SportsListAdapter adapter;
    ChooseSportsViewModel chooseSportsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_list);

        chooseSportsViewModel = ViewModelProviders.of(this).get(ChooseSportsViewModel.class);
        subscribeObservers();
        callAllSports();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void subscribeObservers() {
        chooseSportsViewModel.getSportsKind().observe(this, new Observer<List<SportItem>>() {
            @Override
            public void onChanged(@Nullable List<SportItem> sportItems) {
                initSearchView(sportItems);
                for (SportItem item : sportItems) {
                    Log.d(TAG, "onChanged: " + item.getStrSport());
                }
            }
        });

    }

    private void callAllSports() {
        chooseSportsViewModel.callAllSports();
    }

    private void initSearchView(List<SportItem> sportItems) {

        recyclerView = findViewById(R.id.recViewSportsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SportsListAdapter(this, sportItems);
        recyclerView.setAdapter(adapter);


    }

}
