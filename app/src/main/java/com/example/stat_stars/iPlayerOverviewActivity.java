package com.example.stat_stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Activity for the Player Overview page
public class iPlayerOverviewActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack; // Button to go back to the Stats Stars activity
    ImageView ivSettings; // Imageview to go to the settings activity

    TextView tvPlayerName; // API tv for the players in-game username

    TextView tvTrophies, tvHighestTrophies; // API tv for trophy statistics
    TextView tvRankCurrent, tvRankXp; // API tv for ranked statistics
    TextView tvWins3v3, tvWinsSolo, tvWinsDuo; // API tv for victories statistics
    TextView tvClubName; // API tv for club name statistic
    Button btnToBrawlers; // Button to go to the brawlers statistics activity
    RequestQueue queue; // for API
    String playerTag; // to hold the playerTag from the start activity

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
        ivSettings = findViewById(R.id.ivSettings);
        tvHighestTrophies = findViewById(R.id.tvHighestTrophies);
        tvTrophies = findViewById(R.id.tvTrophies);
        tvRankCurrent = findViewById(R.id.tvRankCurrent);
        tvRankXp = findViewById(R.id.tvRankXp);
        tvWins3v3 = findViewById(R.id.tvWins3v3);
        tvWinsSolo = findViewById(R.id.tvWinsSolo);
        tvWinsDuo = findViewById(R.id.tvWinsDuo);
        tvClubName = findViewById(R.id.tvClubName);
        btnToBrawlers = findViewById(R.id.btnToBrawlers);
        btnBack = findViewById(R.id.btnBack);
        //getting the playerTag given in the starting activity
        Intent intent = getIntent();
        playerTag = intent.getStringExtra("playerTag");
        //volley for API request
        queue = Volley.newRequestQueue(this);
        //function to get info from API
        fetchPlayerData(playerTag);

        //intents to take user to other pages (back to brawlers activity)
        btnToBrawlers.setOnClickListener(v -> {
            Intent brawlerIntent = new Intent(iPlayerOverviewActivity.this, iBrawlersActivity.class);
            startActivity(brawlerIntent);
            finish();
        });
        //back to starting activity
        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(iPlayerOverviewActivity.this, StatsStarsActivity.class);
            startActivity(backIntent);
            finish();
        });
        // to setting activity
        ivSettings.setOnClickListener(v -> {
            Intent settingIntent = new Intent(iPlayerOverviewActivity.this, iSettingsActivity.class);
            startActivity(settingIntent);
            finish();

        });


        // In terms of API, this is easier than the listview because you wont be using any loops
        // So if somebody is less confident, take on this section
        // NOTE: MAKE SURE TO PLACE AN IMAGE OF EVERY SINGLE BRAWLER (WITH THE CORRESPONDING NAME FROM THE API) IN DRAWABLE FOLDER
    }

    private void fetchPlayerData(String playerTag) {
        //the URL used to access the API for information, adding player tag to the end to get a specific player's info.
        String apiURL = "https://api.brawlstars.com/v1/players/" + playerTag;
        System.out.println("Player Tag: " + playerTag);
        // Create the request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Extracting data from the JSON response
                            String playerName = response.getString("name");
                            int trophies = response.getInt("trophies");
                            int expPoints = response.getInt("expPoints");
                            int rankCurrent = response.getInt("expLevel");
                            int highestTrophies = response.getInt("highestTrophies");
                            int wins3v3 = response.getInt("3vs3Victories");
                            int winsSolo = response.getInt("soloVictories");
                            int winsDuo = response.getInt("duoVictories");
                            String clubName = response.getJSONObject("club").getString("name");

                            // Setting the extracted data to the TextViews ENSURE ALL OF THESE MATCH AND WORK PROPERLY PLEASE
                            tvPlayerName.setText(playerName);
                            System.out.println("working example: " + playerName);
                            tvTrophies.setText(String.valueOf(trophies));
                            tvHighestTrophies.setText(String.valueOf(highestTrophies));
                            tvRankCurrent.setText(String.valueOf(rankCurrent));
                            tvRankXp.setText(String.valueOf(expPoints));
                            tvWins3v3.setText(String.valueOf(wins3v3));
                            tvWinsSolo.setText(String.valueOf(winsSolo));
                            tvWinsDuo.setText(String.valueOf(winsDuo));
                            tvClubName.setText(clubName);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    //error handling here
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error here! JSON issue");
                    }
                }) {
            //have to pass the API key to the API in order to use it for data. this is the way it expected us to do it...
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijc0ZGVlYWE3LTY4MzAtNDY0Mi04ZGRmLTlhYzc4ZjA1OTc0ZSIsImlhdCI6MTc0MzcyNTg2Mywic3ViIjoiZGV2ZWxvcGVyLzY4M2FlZDk0LTU5NzYtOGVhZS02NTQ1LTdkNTA0ZGQ1MmEyZCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMjU1LjI1NS4yNTUuMCJdLCJ0eXBlIjoiY2xpZW50In1dfQ.duAun0R8RVb4ne2J1qcrJHUWMNMpgC2UM3JDgEUOZQXO0zw0PPUqyP8LE7DdbEyeQC5OXZB55kbRv4_dfuZsAw"); // Add the API key here
                return headers;
            }
        };

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

}
