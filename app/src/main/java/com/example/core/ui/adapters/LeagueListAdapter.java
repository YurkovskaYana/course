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
import com.example.core.data.LeagueItem;
import com.example.core.ui.ItemClickListener;
import com.example.core.ui.TeamsInLeagueActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueListAdapter extends RecyclerView.Adapter<LeagueListAdapter.ViewHolder>{
    Context context;
    List<LeagueItem> items;

    public LeagueListAdapter(Context context, List<LeagueItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<LeagueItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LeagueListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liga,parent,false);

        return new LeagueListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueListAdapter.ViewHolder holder, int position) {

            holder.tvName.setText(items.get(position).getStrLeague());
            Picasso.get()
                    .load(items.get(position).getStrBadge())
                    .into(holder.ivLogo);

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //Toast.makeText(context, "ClickItem "+ items.get(position).getStrSport(), Toast.LENGTH_LONG).show();
                    Intent search = new Intent(context, TeamsInLeagueActivity.class);
                    search.putExtra("s", items.get(position).getStrSport());
                    search.putExtra("c", items.get(position).getStrCountry());
                    context.startActivity(search);
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

            tvName = itemView.findViewById(R.id.tvNameOfLiga);
            ivLogo = itemView.findViewById(R.id.ivImgOfLiga);
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
