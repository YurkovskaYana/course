package com.example.core.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.core.R;
import com.example.core.data.LeagueItem;
import com.example.core.data.TeamDetailInfoItem;
import com.example.core.ui.adapters.LeagueListAdapter;
import com.example.core.viewmodel.LeagueViewModel;
import com.example.core.viewmodel.TeamDetailInfoViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamDetailInfoActivity extends AppCompatActivity {

    public static final String TAG = "TeamDetailInfoActivity";

    String id;
    TeamDetailInfoViewModel teamDetailInfoViewModel;
    ImageView iv;
    TextView teamName, teamYear, teamSport, teamLeague, teamDescription;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail_info);

        teamDetailInfoViewModel = ViewModelProviders.of(this).get(TeamDetailInfoViewModel.class);

        id = getIntent().getStringExtra("TEAMID");
        init();
        subscribeObservers();
        callAllTeamDetailInfo(id);
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
        teamDetailInfoViewModel.getTeamDetailInfoKind().observe(this, new Observer<List<TeamDetailInfoItem>>() {
            @Override
            public void onChanged(@Nullable List<TeamDetailInfoItem> teamDetailInfoItems) {
                for (final TeamDetailInfoItem item : teamDetailInfoItems){
                    Picasso.get()
                            .load(item.getStrTeamBadge())
                            .resize(500, 500)
                            .into(iv);
                    teamName.setText(item.getStrTeam());
                    teamYear.setText(item.getIntFormedYear());
                    teamSport.setText(item.getStrSport());
                    teamLeague.setText(item.getStrLeague());
                    teamDescription.setText(item.getStrDescriptionEN());
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent next = new Intent(TeamDetailInfoActivity.this, EventActivity.class);
                            next.putExtra("id", item.getIdTeam());
                            startActivity(next);
                        }
                    });
                }
            }
        });

    }

    private void callAllTeamDetailInfo(String id) {
        teamDetailInfoViewModel.callAllTeamDetailInfo(id);
    }

    private void init() {
        teamDescription=findViewById(R.id.tvDescriptionOfTeamText);
        teamLeague=findViewById(R.id.tvLegOfTeamText);
        teamName=findViewById(R.id.tvNameOfTeamText);
        teamSport=findViewById(R.id.tvSportOfTeamText);
        teamYear=findViewById(R.id.tvYearOfTeamText);
        iv=findViewById(R.id.ivDetails);
        b = findViewById(R.id.bt_games);
    }
}
