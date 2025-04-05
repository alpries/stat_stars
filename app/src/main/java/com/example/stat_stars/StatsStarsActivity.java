package com.example.stat_stars;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Activity for the initial page
public class StatsStarsActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    EditText etPlayerTag; // user Player Tag (Example Tag: #UPOQU9U2) Input
    String playerTag; // this needs to hold the player tag that is typed in order to send it to the next activity. intent share is already set up. (WRONA)
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

//sends user to brawlstars or its google play page (ADDITIONAL FEATURE) also, see app icon.
        btnBrawlers.setOnClickListener(v->{
            try {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.supercell.brawlstars");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    //opens the google playstore so user can get brawlstars
                    Intent playstoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.supercell.brawlstars"));
                    startActivity(playstoreIntent);
                }
            } catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, "Playstore cannot be accessed...", Toast.LENGTH_LONG).show();
                //lets the user know that brawlstars is 1) not on phone and 2) playstore cant be used through this app
            }

        });

        btnGo.setOnClickListener(v->{
            String playerTag = etPlayerTag.getText().toString().trim(); // Get the text and trim whitespace
            System.out.println("Player Tag: " + playerTag);
            Intent intent = new Intent(StatsStarsActivity.this, iPlayerOverviewActivity.class);
            intent.putExtra("playerTag", playerTag);
            startActivity(intent);
        }); //this NEEDS to pass the player's username to the next page!!!!






        // HELPFUL NOTE: USE THE PUZZLE ACTIVITY TO REMEMBER INTENTS -->
        // Intent intent = new Intent(MainActivity.this, GameActivity.class);
        // startActivity(intent);
    }
}
