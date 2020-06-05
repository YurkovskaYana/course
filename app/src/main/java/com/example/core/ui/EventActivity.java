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

import com.example.core.R;
import com.example.core.data.EventItem;
import com.example.core.data.LeagueItem;
import com.example.core.ui.adapters.EventAdapter;
import com.example.core.ui.adapters.LeagueListAdapter;
import com.example.core.viewmodel.EventViewModel;
import com.example.core.viewmodel.LeagueViewModel;

import java.util.List;

public class EventActivity extends AppCompatActivity {


    public static final String TAG = "EventActivity";

    String id;
    EventViewModel eventViewModel;
    RecyclerView recyclerView;
    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        id = getIntent().getStringExtra("id");
        subscribeObservers();
        callAllEvents(id);
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
        eventViewModel.getEventKind().observe(this, new Observer<List<EventItem>>() {
            @Override
            public void onChanged(@Nullable List<EventItem> eventItems) {
                initSearchView(eventItems);
                for (EventItem item : eventItems) {
                    Log.d(TAG, "onChanged: " + item.getStrEvent());
                }
            }
        });

    }

    private void callAllEvents(String kindOfSportForQuery) {
        eventViewModel.callAllEvents(kindOfSportForQuery);
    }

    private void initSearchView(List<EventItem> eventItems) {

        recyclerView = findViewById(R.id.recViewEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter(this, eventItems);
        recyclerView.setAdapter(adapter);


    }
}
