package com.example.stat_stars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;

public class iSettingsActivity extends AppCompatActivity {

    Button btnBack2;
    Button btnLogout;
    ListView recentUserList;

    @SuppressLint("WrongViewCast")
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

        btnBack2 = findViewById(R.id.btnBack2);
        btnLogout = findViewById(R.id.btnLogout);
        recentUserList = findViewById(R.id.recentuser);

        btnBack2.setOnClickListener(v -> finish());

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(iSettingsActivity.this, StatsStarsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Dummy data for testing
        List<Users> users = new ArrayList<>();
        users.add(new Users("User1", "12345"));
        users.add(new Users("User2", "67890"));

        // Set the adapter to the ListView
        UsersAdapters adapter = new UsersAdapters(this, users);
        recentUserList.setAdapter((ListAdapter) adapter);
    }
}
