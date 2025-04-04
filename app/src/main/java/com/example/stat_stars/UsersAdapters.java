package com.example.stat_stars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UserViewHolder> {

    private final List<Users> userList;
    private final Context context;

    public UsersAdapters(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recent_users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position);
        holder.username.setText(user.getUsername());
        holder.userID.setText("ID: " + user.getUserID());

        holder.goButton.setOnClickListener(v -> {
            Toast.makeText(context, "Going to stats for " + user.getUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, iPlayerOverviewActivity.class);
            intent.putExtra("userID", user.getUserID());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView username, userID;
        Button goButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            userID = itemView.findViewById(R.id.userID);
            goButton = itemView.findViewById(R.id.btnGO2);
        }
    }
}
