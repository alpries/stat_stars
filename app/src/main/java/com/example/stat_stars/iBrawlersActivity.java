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
    ListView lvBrawlersList; // ListView which contains the information on all the brawlers
    String id; // This String stores the Id
    RequestQueue queue; // This is used to help with JSON requests so they can run in the background
    String url; // This saves the url for the api
    ArrayList<Brawler> brawlers1; // This array list saves all the brawlers info
    BrawlerAdapter adapter; // This adapter adapts the brawlers to the listview
    Intent intent; // Intent to save the intent from the previous activity

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




   

        intent = getIntent(); // Grab intent from the previous activity
        id = intent.getStringExtra("playerTag"); // Take the player tag from the preview page
        System.out.println(id);
        brawlers1 = new ArrayList<>(); // Initialize the arraylist
        adapter = new BrawlerAdapter(brawlers1,getApplicationContext()); // Initialize the brawler adapter
        queue = Volley.newRequestQueue(this); // Initialize the queue
        String formattedTag = id.replace("#", "%23"); // Format the id for the api
        url = "https://api.brawlstars.com/v1/players/" + formattedTag; // create the link

        // Drawables
        btnBack3 = findViewById(R.id.btnBack3); // These connect the things in the api to the java objects
        ivSettings2 = findViewById(R.id.ivSettings2);
        lvBrawlersList = findViewById(R.id.lvBrawlersList);


        getBrawlers(); // calls the function which sets up the listview
        lvBrawlersList.setAdapter(adapter); // Add the adapter to the list view
        btnBack3.setOnClickListener(v -> { // Set the back button to first activity
            Intent backIntent = new Intent(iBrawlersActivity.this, StatsStarsActivity.class);
            startActivity(backIntent);
            finish();
        });
      
//       ivSettings2.setOnClickListener(v->{
//            Intent settingsintent = new Intent(iBrawlersActivity.this, iSettingsActivity.class);
//            startActivity(settingsintent);
//            finish();
//        });

    }

    public void getBrawlers() { // This function makes a JSON request and saves it into an arraylist
        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() { // Request the json object
            @Override
            public void onResponse(JSONObject response) { // This handles the response to the JSON Object
                try {
                    JSONArray brawlers = response.getJSONArray("brawlers"); // Get the brawlers list from the JSON object
                    for(int i=0; i<brawlers.length(); i++) { // Loop which runs through the brawlers list and adds them the arraylist
                        JSONObject brawler = brawlers.getJSONObject(i); // Select the object from the brawler list
                        String name = brawler.getString("name"); // Save the values
                        int trophies = brawler.getInt("trophies");
                        String sTrophies = String.valueOf(trophies); // Change them into strings
                        Brawler brawler1 = new Brawler(name,sTrophies); // Create a brawler object
                        brawlers1.add(brawler1); // add it to the list
                        adapter.notifyDataSetChanged(); // updates as it goes
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e); // sends and exception
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { // Handles an error
                System.out.println("error: "+ error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() { // This is used for the api to gain verification
                Map<String, String> headers = new HashMap<>(); // Saves hashmap with key ip
                headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijk4NTAyOWFlLWRiMmEtNDhlZC1hY2M5LWViM2UyYzk3ZjVjNCIsImlhdCI6MTc0Mzc3OTM1Niwic3ViIjoiZGV2ZWxvcGVyLzA1ZDAzNDg3LWUyYWUtOWU4OS1iZWI5LTJjYTQ2NmVhZjhhMiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTUxLjE4MS4zNi45OSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.4V2jEkb1Fw_AEu7kMyrIpaOAlgHfwJ5RJ5MrWsbJizxb_hswK5LLg7Ny_Lyio7HNiKBwmdQBKQ-Y8xsr0W2xMQ");
                headers.put("Accept", "application/json"); // The line above is my key it only works with my ip
                return headers;
            }
        };
        queue.add(r); // Add the request to the queue
    }
}