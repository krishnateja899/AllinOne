package com.example.allinone.RecyclerViews.AFirstRecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private List<String> data;

    public ProgrammingAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ProgrammingAdapter.ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingAdapter.ProgrammingViewHolder holder, int position) {
        String title = data.get(position);
        holder.button11.setText(String.valueOf(title));
        holder.txtTitle.setText(String.valueOf(title));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        Button button11;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            button11 = itemView.findViewById(R.id.button11);
        }
    }
}
