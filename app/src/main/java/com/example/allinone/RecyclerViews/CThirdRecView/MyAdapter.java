package com.example.allinone.RecyclerViews.CThirdRecView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ThirdListItem> listItemThirdActivities;
    private Context context;

    public MyAdapter(List<ThirdListItem> listItemThirdActivities, Context context) {
        this.listItemThirdActivities = listItemThirdActivities;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.third_rec_list_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        ThirdListItem listItemThirdActivity = listItemThirdActivities.get(position);

        holder.textViewHead.setText(listItemThirdActivity.getHead());
        holder.textViewDesc.setText(listItemThirdActivity.getDesc());

        Picasso.get().load(listItemThirdActivity.getImageUrl()).into(holder.imageView);

        holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked" + listItemThirdActivity.getHead(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItemThirdActivities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;
        public LinearLayout linearLayout1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = itemView.findViewById(R.id.textViewHead);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout1 = itemView.findViewById(R.id.linearLayout);
        }
    }
}
