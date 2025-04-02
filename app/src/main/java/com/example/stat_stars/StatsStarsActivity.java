package com.example.stat_stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Activity for the initial page
public class StatsStarsActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    EditText etPlayerTag; // user Player Tag (Example Tag: #UPOQU9U2) Input
    Button btnGo; // Go button for once the user has entered player tag
    Button btnBrawlers; // Button that takes them to the brawl stars app (using intents!)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statsstars);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Drawables
        etPlayerTag = findViewById(R.id.etPlayerTag); // MAKE SURE THIS CAPS AT 9 OR WHATEVER MAX TAG LENGTH IS
        btnGo = findViewById(R.id.btnGo);
        btnBrawlers = findViewById(R.id.btnBrawlers);






        // HELPFUL NOTE: USE THE PUZZLE ACTIVITY TO REMEMBER INTENTS -->
        // Intent intent = new Intent(MainActivity.this, GameActivity.class);
        // startActivity(intent);
    }
}
