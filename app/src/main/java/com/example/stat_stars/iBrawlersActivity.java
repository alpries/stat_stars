package com.example.stat_stars;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Activity for the Brawlers Stats page
public class iBrawlersActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack3; // Button to take user back to Player Overview activity
    ImageView ivSettings2; // ImageView to go to settings activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ibrawlers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Drawables
        btnBack3 = findViewById(R.id.btnBack3);
        ivSettings2 = findViewById(R.id.ivSettings2);

        // The actual brawler.java and list view and XML still needs to be created
        // This will also be heavy API / image loading so whoever does this be ready for war
    }
}