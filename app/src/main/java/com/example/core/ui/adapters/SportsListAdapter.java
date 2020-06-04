package com.example.core.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.R;
import com.example.core.data.SportItem;
import com.example.core.ui.ItemClickListener;
import com.example.core.ui.LeagueActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SportsListAdapter extends RecyclerView.Adapter<SportsListAdapter.ViewHolder>{

    Context context;
    List<SportItem> items;

    public SportsListAdapter(Context context, List<SportItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<SportItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SportsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sport,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsListAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getStrSport());
        Picasso.get()
                .load(items.get(position).getStrSportThumb())
                .into(holder.ivLogo);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(context, "ClickItem "+ items.get(position).getStrSport(), Toast.LENGTH_LONG).show();
                Intent search = new Intent(context, LeagueActivity.class);
                search.putExtra("CHOSEN", items.get(position).getStrSport());
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

            tvName = itemView.findViewById(R.id.tvSportName);
            ivLogo = itemView.findViewById(R.id.itemSportImage);
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
