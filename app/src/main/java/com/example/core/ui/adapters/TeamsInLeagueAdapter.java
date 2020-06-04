package com.example.core.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.R;
import com.example.core.data.EachTeamItem;
import com.example.core.ui.ItemClickListener;
import com.example.core.ui.TeamDetailInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamsInLeagueAdapter  extends RecyclerView.Adapter<TeamsInLeagueAdapter.ViewHolder>{

    Context context;
    List<EachTeamItem> items;

    public TeamsInLeagueAdapter(Context context, List<EachTeamItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<EachTeamItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TeamsInLeagueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team_in_league,parent,false);

        return new TeamsInLeagueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsInLeagueAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getStrTeam());
        Picasso.get()
                .load(items.get(position).getStrTeamBadge())
                .into(holder.ivLogo);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(context, "ClickItem "+ items.get(position).getStrSport(), Toast.LENGTH_LONG).show();
                Intent next = new Intent(context, TeamDetailInfoActivity.class);
                next.putExtra("TEAMID", items.get(position).getIdTeam());
                context.startActivity(next);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName;
        ImageView ivLogo;
        View itemView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvNameOfTeamInLiga);
            ivLogo = itemView.findViewById(R.id.ivImgOfTeamInLiga);
            this.itemView=itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getLayoutPosition());

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

}
