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

// Activity for the Player Overview page
public class iPlayerOverviewActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack; // Button to go back to the Stats Stars activity
    ImageView ivSettings; // Imageview to go to the settings activity

    TextView tvPlayerName; // API tv for the players in-game username

    ImageView ivFavoriteBrawler; // Image of the users favorite brawler
    TextView tvFavoriteBrawler; // API tv for favorite brawler name

    TextView tvTrophiesSeason, tvTrophiesAllTime; // API tv for trophy statistics
    TextView tvRankCurrent, tvRankHighest; // API tv for ranked statistics
    TextView tvWins3v3, tvWinsSolo, tvWinsDuo; // API tv for victories statistics
    TextView tvClubName; // API tv for club name statistic
    Button btnToBrawlers; // Button to go to the brawlers statistics activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iplayer_overview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Drawables
        tvPlayerName = findViewById(R.id.tvPlayerName);
        ivFavoriteBrawler = findViewById(R.id.ivFavoriteBrawler);
        tvFavoriteBrawler = findViewById(R.id.tvFavoriteBrawler);
        tvTrophiesSeason = findViewById(R.id.tvTrophiesSeason);
        tvTrophiesAllTime = findViewById(R.id.tvTrophiesAllTime);
        tvRankCurrent = findViewById(R.id.tvRankCurrent);
        tvRankHighest = findViewById(R.id.tvRankHighest);
        tvWins3v3 = findViewById(R.id.tvWins3v3);
        tvWinsSolo = findViewById(R.id.tvWinsSolo);
        tvWinsDuo = findViewById(R.id.tvWinsDuo);
        tvClubName = findViewById(R.id.tvClubName);
        btnToBrawlers = findViewById(R.id.btnToBrawlers);


        // In terms of API, this is easier than the listview because you wont be using any loops
        // So if somebody is less confident, take on this section
        // NOTE: MAKE SURE TO PLACE AN IMAGE OF EVERY SINGLE BRAWLER (WITH THE CORRESPONDING NAME FROM THE API) IN DRAWABLE FOLDER
    }
}