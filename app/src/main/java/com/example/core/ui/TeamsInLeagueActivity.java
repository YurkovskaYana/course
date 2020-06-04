package com.example.core.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.core.R;
import com.example.core.data.EachTeamItem;
import com.example.core.data.LeagueItem;
import com.example.core.ui.adapters.LeagueListAdapter;
import com.example.core.ui.adapters.TeamsInLeagueAdapter;
import com.example.core.viewmodel.LeagueViewModel;
import com.example.core.viewmodel.TeamsInLeagueViewModel;

import java.util.List;

public class TeamsInLeagueActivity extends AppCompatActivity {

    public static final String TAG = "TeamsInLeagueActivity";

    String s, c;
    TeamsInLeagueViewModel teamsInLeagueViewModel;
    RecyclerView recyclerView;
    TeamsInLeagueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_in_league);

        teamsInLeagueViewModel = ViewModelProviders.of(this).get(TeamsInLeagueViewModel.class);


        s = getIntent().getStringExtra("s");
        c = getIntent().getStringExtra("c");
        subscribeObservers();
        callAllTeamsInLeague(s,c);
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
        teamsInLeagueViewModel.getTeamsInLeagueKind().observe(this, new Observer<List<EachTeamItem>>() {
            @Override
            public void onChanged(@Nullable List<EachTeamItem> eachTeamItems) {
                initSearchView(eachTeamItems);
                for (EachTeamItem item : eachTeamItems) {
                    Log.d(TAG, "onChanged: " + item.getStrTeam());
                }
            }
        });

    }

    private void callAllTeamsInLeague(String s, String c) {
        teamsInLeagueViewModel.callAllTeamsInLeague(s,c);
    }

    private void initSearchView(List<EachTeamItem> eachTeamItems) {

        recyclerView = findViewById(R.id.recViewTeamsInLeagueList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2 ));

        adapter = new TeamsInLeagueAdapter(this, eachTeamItems);
        recyclerView.setAdapter(adapter);


    }
}
