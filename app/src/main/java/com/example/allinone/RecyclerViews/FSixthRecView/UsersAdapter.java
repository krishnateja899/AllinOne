package com.example.allinone.RecyclerViews.FSixthRecView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<UserItems> userItems;
    private Context context;

    public UsersAdapter(List<UserItems> userItems, Context context) {
        this.userItems = userItems;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sixth_rec_users_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        UserItems userItems1 = userItems.get(position);
        Picasso.get().load(userItems1.getAvatar()).into(holder.userImage);
        holder.userName.setText(userItems1.getName());
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
        }
    }
}
