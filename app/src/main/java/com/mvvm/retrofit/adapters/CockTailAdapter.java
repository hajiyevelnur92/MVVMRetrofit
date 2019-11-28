package com.mvvm.retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.retrofit.R;

import com.mvvm.retrofit.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public abstract class CockTailAdapter extends RecyclerView.Adapter<CockTailAdapter.NewsViewHolder> {

    private Context context;
    private ArrayList<Drink> articles;
    public abstract void onItemClick (Drink drink);

    public CockTailAdapter(Context context, ArrayList<Drink> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public CockTailAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cocktail_item, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CockTailAdapter.NewsViewHolder holder, int position) {
        final Drink drink = this.articles.get(position);
        holder.title.setText(articles.get(position).getStrDrink());
        Picasso.get().load(articles.get(position).getStrDrinkThumb()).into(holder.imgLink);

        holder.itemView.setOnClickListener(v -> onItemClick(drink));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imgLink;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            imgLink = itemView.findViewById(R.id.imglink);

        }
    }
}
