package com.example.stat_stars;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Activity for the Settings page
public class iSettingsActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack2; // Takes you back to the PREVIOUS activity user was just on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_isettings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Drawables
        btnBack2 = findViewById(R.id.btnBack2);


        // I Don't know what we want to include on the settings page but we need 4 activities so try to come up with something
        // Also for the actual settings icon, use a SetOnClickListener to make this work!
    }
}