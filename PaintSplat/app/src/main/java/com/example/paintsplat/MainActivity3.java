package com.example.paintsplat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    Button button1;
    Button button2;

    TextView textView;

    String playerName = "";
    String roomName = "";
    String role = "";
    String message = "";

    FirebaseDatabase db;
    DatabaseReference messageRefr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);

        button2 = findViewById(R.id.button2);
        button2.setEnabled(true);

        db = FirebaseDatabase.getInstance();

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomName = extras.getString("roomName");
            if (roomName.equals(playerName)) {
                role = "host";
            } else {
                role = "guest";
            }
        }

        button1.setOnClickListener(v -> {
            button1.setEnabled(false);
            message = role + ":Says hi!";
            //messageRefr.setValue(message);
        });

        button2.setOnClickListener(v -> {
            button2.setText("START NEW GAME");
            button2.setEnabled(true);
            startGameEventListener();
        });
    }

    private void startGameEventListener() {
        DatabaseReference roomRef = db.getReference("rooms/" + roomName +"/"+playerName);
        roomRef.child("name").setValue(playerName);
        roomRef.child("count").setValue(0);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        preferences.edit().putString("roomName", roomName).apply();
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomRef.removeEventListener(this);
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                button2.setText("START NEW GAME");
                button2.setEnabled(true);
                Toast.makeText(MainActivity3.this, "ERROR STARTING A NEW GAME", Toast.LENGTH_SHORT).show();
            }
        });
    }

}