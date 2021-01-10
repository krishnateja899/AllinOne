package com.example.allinone.RecyclerViews.BSecondRecView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

    private Context context;
    private User[] data;

    public GithubAdapter(Context context, User[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GithubAdapter.GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item_sec_recview, parent, false);

        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubAdapter.GithubViewHolder holder, int position) {
        User user = data[position];
        holder.txtUser.setText(user.getLogin());
        //Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
        Picasso.get().load(user.getAvatarUrl()).into(holder.imgUser);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getLogin() + "is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class GithubViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUser;
        private TextView txtUser;

        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            txtUser = itemView.findViewById(R.id.txtUser);
        }
    }
}
