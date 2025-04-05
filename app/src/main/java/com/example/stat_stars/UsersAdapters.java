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

// Adapter class for displaying a list of Users in a RecyclerView
public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UserViewHolder> {

    // VARIABLES + EXPLANATIONS
    private final List<Users> userList; // List of users to display
    private final Context context;      // Context is needed to inflate layouts and start activities

    // CONSTRUCTOR - receives the context and list of users to display
    public UsersAdapters(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    // Called when a new ViewHolder is created. Inflates the layout for each item in the list
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recent_users, parent, false);
        return new UserViewHolder(view); // return a new holder with the inflated view
    }

    // Called when binding data to a ViewHolder. Updates the UI with the user's info.
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position); // Get current user

        // Set the username and user ID text on the UI
        holder.username.setText(user.getUsername());
        holder.userID.setText("ID: " + user.getUserID());

        // Set onClickListener for the "Go" button
        holder.goButton.setOnClickListener(v -> {
            // Toast message to show which user's stats will be opened
            Toast.makeText(context, "Going to stats for " + user.getUsername(), Toast.LENGTH_SHORT).show();

            // Start the iPlayerOverviewActivity and pass the selected user's ID
            Intent intent = new Intent(context, iPlayerOverviewActivity.class);
            intent.putExtra("userID", user.getUserID()); // pass the user ID as an intent extra
            context.startActivity(intent); // start the new activity
        });
    }

    // Returns the total number of users in the list
    @Override
    public int getItemCount() {
        return userList.size(); // size of the list determines number of rows in RecyclerView
    }

    // ViewHolder class - holds the views for each item in the RecyclerView
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView username, userID; // Displays the username and ID of the user
        Button goButton;           // "Go" button to open the stats of the selected user

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connect layout elements to variables
            username = itemView.findViewById(R.id.username);
            userID = itemView.findViewById(R.id.userID);
            goButton = itemView.findViewById(R.id.btnGO2);
        }
    }
}

