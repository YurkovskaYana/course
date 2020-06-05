package com.example.core.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.R;
import com.example.core.data.EventItem;
import com.example.core.data.SportItem;
import com.example.core.ui.ItemClickListener;
import com.example.core.ui.LeagueActivity;
import com.example.core.viewmodel.EventViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    public static final String TAG = "EventAdapter";

    Context context;
    List<EventItem> items;

    public EventAdapter(Context context, List<EventItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<EventItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event,parent,false);

        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {

        holder.tvDate.setText(items.get(position).getDateEvent());
        holder.tvScoreAway.setText(items.get(position).getIntAwayScore());
        holder.tvScoreHome.setText(items.get(position).getIntHomeScore());
        holder.tvTeam.setText(items.get(position).getStrEvent());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTeam;
        TextView tvScoreAway;
        TextView tvScoreHome;
        TextView tvDate;
        View itemView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTeam=itemView.findViewById(R.id.tvTeam);
            tvScoreAway=itemView.findViewById(R.id.tvScoreAway);
            tvScoreHome=itemView.findViewById(R.id.tvScoreHome);
            tvDate=itemView.findViewById(R.id.tvDate);
            this.itemView=itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //itemClickListener.onItemClick(v, getLayoutPosition());
            Log.e(TAG, "click");

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            //this.itemClickListener = itemClickListener;
        }
    }
}
