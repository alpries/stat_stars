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

    TextView tvTrophies; // API tv for trophy statistics
    TextView tvRankCurrent, tvRankXp; // API tv for ranked statistics
    TextView tvWins3v3, tvWinsSolo, tvWinsDuo; // API tv for victories statistics
    TextView tvClubName; // API tv for club name statistic
    Button btnToBrawlers; // Button to go to the brawlers statistics activity
    RequestQueue queue;
    String playerTag;

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


        tvTrophies = findViewById(R.id.tvTrophiesAllTime);
        tvRankCurrent = findViewById(R.id.tvRankCurrent);
        tvRankXp = findViewById(R.id.tvRankXp);
        tvWins3v3 = findViewById(R.id.tvWins3v3);
        tvWinsSolo = findViewById(R.id.tvWinsSolo);
        tvWinsDuo = findViewById(R.id.tvWinsDuo);
        tvClubName = findViewById(R.id.tvClubName);
        btnToBrawlers = findViewById(R.id.btnToBrawlers);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String playerTag = intent.getStringExtra("playerTag");

        queue = Volley.newRequestQueue(this);

        fetchPlayerData(playerTag);

        btnToBrawlers.setOnClickListener(v -> {
            Intent brawlerIntent = new Intent(iPlayerOverviewActivity.this, iBrawlersActivity.class);
            intent.putExtra("playerTag", playerTag);
            startActivity(brawlerIntent);
        });

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(iPlayerOverviewActivity.this, StatsStarsActivity.class);
            startActivity(backIntent);
            finish();
        });


        // In terms of API, this is easier than the listview because you wont be using any loops
        // So if somebody is less confident, take on this section
        // NOTE: MAKE SURE TO PLACE AN IMAGE OF EVERY SINGLE BRAWLER (WITH THE CORRESPONDING NAME FROM THE API) IN DRAWABLE FOLDER
    }

    private void fetchPlayerData(String playerTag) {
        String formattedTag = playerTag.replace("#", "%23");
        String apiURL = "https://api.brawlstars.com/v1/players/" + formattedTag;
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
                            String playerTag = response.getString("tag");
                            String playerName = response.getString("name");
                            int trophies = response.getInt("trophies");
                            int rankCurrent = response.getInt("expLevel"); // Assuming expLevel is used for current rank
                            int highestTrophies = response.getInt("highestTrophies");
                            int wins3v3 = response.getInt("3vs3Victories");
                            int winsSolo = response.getInt("soloVictories");
                            int winsDuo = response.getInt("duoVictories");
                            String clubName = response.getJSONObject("club").getString("name"); // Assuming club has a name field

                            // Setting the extracted data to the TextViews ENSURE ALL OF THESE MATCH AND WORK PROPERLY PLEASE
                            tvPlayerName.setText(playerName);
                            System.out.println("working example: " + playerName);
                            tvTrophies.setText(String.valueOf(trophies));
                            tvRankCurrent.setText(String.valueOf(rankCurrent));
                            tvRankXp.setText(String.valueOf(highestTrophies));
                            tvWins3v3.setText(String.valueOf(wins3v3));
                            tvWinsSolo.setText(String.valueOf(winsSolo));
                            tvWinsDuo.setText(String.valueOf(winsDuo));
                            tvClubName.setText(clubName);
                            System.out.println("Yolo");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error: "+ error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijk4NTAyOWFlLWRiMmEtNDhlZC1hY2M5LWViM2UyYzk3ZjVjNCIsImlhdCI6MTc0Mzc3OTM1Niwic3ViIjoiZGV2ZWxvcGVyLzA1ZDAzNDg3LWUyYWUtOWU4OS1iZWI5LTJjYTQ2NmVhZjhhMiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTUxLjE4MS4zNi45OSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.4V2jEkb1Fw_AEu7kMyrIpaOAlgHfwJ5RJ5MrWsbJizxb_hswK5LLg7Ny_Lyio7HNiKBwmdQBKQ-Y8xsr0W2xMQ"); // Add the API key here
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

}
