package com.example.allinone.RecyclerViews.DFourthRecView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Anime4> mData;

    public RecyclerViewAdapter(Context context, List<Anime4> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.anime_row_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategorie());

        Picasso.get().load(mData.get(position).getImage_url()).into(holder.img_thumbnail);
//      Glide.with(context).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);

        holder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AnimeActivity.class);
                i.putExtra("anime_name", mData.get(holder.getAdapterPosition()).getName());
                i.putExtra("anime_description", mData.get(holder.getAdapterPosition()).getDescription());
                i.putExtra("anime_studio", mData.get(holder.getAdapterPosition()).getStudio());
                i.putExtra("anime_category", mData.get(holder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_nb_episode", mData.get(holder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_rating", mData.get(holder.getAdapterPosition()).getRating());
                i.putExtra("anime_img", mData.get(holder.getAdapterPosition()).getImage_url());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
