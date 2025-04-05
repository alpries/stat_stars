package com.example.stat_stars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;

// Activity for the Settings Page (iSettingsActivity)
public class iSettingsActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack2; // Back button to return to the previous screen
    Button btnLogout; // Logout button that sends the user back to StatsStarsActivity (acts as logout)
    ListView recentUserList; // ListView that displays a list of recently searched users (temporary dummy data for now)

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display
        setContentView(R.layout.activity_isettings); // Connects this class to its XML layout

        // Makes sure the view uses proper padding to avoid overlap with system UI (status/nav bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // CONNECT UI ELEMENTS TO VARIABLES
        btnBack2 = findViewById(R.id.btnBack2); // Go back to the previous activity
        btnLogout = findViewById(R.id.btnLogout); // Button to simulate logging out
        recentUserList = findViewById(R.id.recentuser); // Displays a list of recent users (currently dummy data)

        btnBack2.setOnClickListener(v ->
                {
                Intent backintent = new Intent(iSettingsActivity.this, iPlayerOverviewActivity.class);
                startActivity(backintent);
                finish();
                });

     

        // LOGOUT BUTTON FUNCTIONALITY - goes back to the StatsStarsActivity
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(iSettingsActivity.this, StatsStarsActivity.class);
            // These flags make sure the app clears the backstack and restarts from the main screen
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // finish current activity so user can’t come back using back button
        });
    }
}
