package com.example.paintsplat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    Button button;

    List<String> roomsList;

    String playerName = "";
    String roomName = "";

    FirebaseDatabase db;
    DatabaseReference roomRefr;
    DatabaseReference roomsRefr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = FirebaseDatabase.getInstance();

        //Gets the players name and assigns corresponding room name
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");
        roomName = playerName;

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        //All of the rooms that are open
        roomsList = new ArrayList<>();

        button.setOnClickListener(v -> {
            //Make room, set you to player1
            button.setText("CREATING ROOM...");
            button.setEnabled(false);
            roomName = playerName;
            roomRefr = db.getReference("rooms");
            roomRefr.setValue(playerName);
            roomRefr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    intent.putExtra("roomName", roomName);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //join existing room and make yourself player2
            button.setText("CREATE ROOM");
            button.setEnabled(true);
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            intent.putExtra("roomName", roomsList.get(position));
            startActivity(intent);
        });
        addRoomsEventListener();
        //is new room open
    }

    private void addRoomsEventListener() {
        roomsRefr = db.getReference("rooms");
        roomsRefr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //show room list
                roomsList.clear();
                Iterable<DataSnapshot> rooms = snapshot.getChildren();
                for (DataSnapshot snapshot1 : rooms) {
                    roomsList.add(snapshot1.getKey());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this,
                            android.R.layout.simple_list_item_1, roomsList);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //N/A
            }
        });
    }

}