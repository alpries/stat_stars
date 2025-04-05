package com.example.stat_stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Activity for the Brawlers Stats page
public class iBrawlersActivity extends AppCompatActivity {

    // VARIABLES + EXPLANATIONS
    Button btnBack3; // Button to take user back to Player Overview activity
    ImageView ivSettings2; // ImageView to go to settings activity
    ListView lvBrawlersList;
    Intent intent;
    String id;
    RequestQueue queue;
    String url;
    ArrayList<Brawler> brawlers1;
    BrawlerAdapter adapter;

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



        brawlers1 = new ArrayList<>();
        adapter = new BrawlerAdapter(brawlers1,getApplicationContext());
        queue = Volley.newRequestQueue(this);
        id = "#8YVVRLLQJ";
        String formattedTag = id.replace("#", "%23");
        url = "https://api.brawlstars.com/v1/players/" + formattedTag;
        // Drawables
        btnBack3 = findViewById(R.id.btnBack3);
        ivSettings2 = findViewById(R.id.ivSettings2);
        lvBrawlersList = findViewById(R.id.lvBrawlersList);
        getBrawlers();
        lvBrawlersList.setAdapter(adapter);


        btnBack3.setOnClickListener(v->{
            Intent backintent = new Intent(iBrawlersActivity.this, iPlayerOverviewActivity.class);
            startActivity(backintent);
            finish();
        });

        ivSettings2.setOnClickListener(v->{
            Intent settingsintent = new Intent(iBrawlersActivity.this, iSettingsActivity.class);
            startActivity(settingsintent);
            finish();
        });

//        btnGo.setOnClickListener(v->{
//            String playerTag = etPlayerTag.getText().toString().trim(); // Get the text and trim whitespace
//            System.out.println("Player Tag: " + playerTag);
//            Intent intent = new Intent(StatsStarsActivity.this, iPlayerOverviewActivity.class);
//            intent.putExtra("playerTag", playerTag);
//            startActivity(intent);
    }

    public void getBrawlers() {
        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray brawlers = response.getJSONArray("brawlers");
                    for(int i=0; i<brawlers.length(); i++) {
                        JSONObject brawler = brawlers.getJSONObject(i);
                        String name = brawler.getString("name");
                        int trophies = brawler.getInt("trophies");
                        String sTrophies = String.valueOf(trophies);
                        Brawler brawler1 = new Brawler(name,sTrophies);
                        brawlers1.add(brawler1);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error: "+ error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijk4NTAyOWFlLWRiMmEtNDhlZC1hY2M5LWViM2UyYzk3ZjVjNCIsImlhdCI6MTc0Mzc3OTM1Niwic3ViIjoiZGV2ZWxvcGVyLzA1ZDAzNDg3LWUyYWUtOWU4OS1iZWI5LTJjYTQ2NmVhZjhhMiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTUxLjE4MS4zNi45OSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.4V2jEkb1Fw_AEu7kMyrIpaOAlgHfwJ5RJ5MrWsbJizxb_hswK5LLg7Ny_Lyio7HNiKBwmdQBKQ-Y8xsr0W2xMQ");
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(r);
    }
}