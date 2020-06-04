package com.example.core.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.core.R;
import com.example.core.data.LeagueItem;
import com.example.core.data.SportItem;
import com.example.core.ui.adapters.LeagueListAdapter;
import com.example.core.ui.adapters.SportsListAdapter;
import com.example.core.viewmodel.ChooseSportsViewModel;
import com.example.core.viewmodel.LeagueViewModel;

import java.util.List;

public class LeagueActivity extends AppCompatActivity {

    public static final String TAG = "LeagueActivity";

    String positionChosen;
    LeagueViewModel leagueViewModel;
    RecyclerView recyclerView;
    LeagueListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        leagueViewModel = ViewModelProviders.of(this).get(LeagueViewModel.class);

        positionChosen = getIntent().getStringExtra("CHOSEN");
        subscribeObservers();
        callAllLeagues(positionChosen);
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
        leagueViewModel.getLeagueKind().observe(this, new Observer<List<LeagueItem>>() {
            @Override
            public void onChanged(@Nullable List<LeagueItem> leagueItems) {
                initSearchView(leagueItems);
                for (LeagueItem item : leagueItems) {
                    Log.d(TAG, "onChanged: " + item.getStrLeague());
                }
            }
        });

    }

    private void callAllLeagues(String kindOfSportForQuery) {
        leagueViewModel.callAllLeagues(kindOfSportForQuery);
    }

    private void initSearchView(List<LeagueItem> leagueItems) {

        recyclerView = findViewById(R.id.recViewLeagueList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2 ));

        adapter = new LeagueListAdapter(this, leagueItems);
        recyclerView.setAdapter(adapter);


    }
}
