package com.example.stat_stars;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

// Activity for the Brawlers Stats page
public class iBrawlersActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack3; // Button to take user back to Player Overview activity
    ImageView ivSettings2; // ImageView to go to settings activity
    ListView lvBrawlersList;
    ArrayList<Brawler> brawlers;

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
        lvBrawlersList = findViewById(R.id.lvBrawlersList);
        brawlers = new ArrayList<>();
        brawlers.add(new Brawler("Shelly","100")); // To test
        brawlers.add(new Brawler("Colt","100"));
        brawlers.add(new Brawler("Nita","100"));
        brawlers.add(new Brawler("Larry & Lawrie","300"));
        brawlers.add(new Brawler("Brock","300"));
        brawlers.add(new Brawler("Jessie","300"));
        brawlers.add(new Brawler("Emz","300"));
        brawlers.add(new Brawler("Finx","300"));
        brawlers.add(new Brawler("Sandy","300"));
        brawlers.add(new Brawler("Dynamike","300"));
        BrawlerAdapter adapter = new BrawlerAdapter(brawlers,getApplicationContext());
        lvBrawlersList.setAdapter(adapter);

        // This will also be heavy API / image loading so whoever does this be ready for war
    }
}