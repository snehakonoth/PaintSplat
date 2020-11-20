package com.example.paintsplat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity5 extends AppCompatActivity {

    Button button;

    TextView textView1;
    TextView textView2;

    FirebaseDatabase db;
    DatabaseReference roomRefr;

    String playerName = "";
    String roomName = "";
    Long highest = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        button = findViewById(R.id.button);
        button.setEnabled(true);

        textView1 = findViewById(R.id.textView1); /*For displaying the winner*/
        textView2 = findViewById(R.id.textView2);

        db = FirebaseDatabase.getInstance();
        int count = getIntent().getIntExtra("dot_count",0);
        //Gets the players name and assigns corresponding room name
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");
        roomName = preferences.getString("roomName", "");;
        roomRefr = db.getReference("rooms/" + roomName + "/" + playerName);
        roomRefr.child("count").setValue(count);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        findWinner("rooms/"+roomName);

    }

    private void findWinner(String reference) {
        DatabaseReference roomsRefr = db.getReference(reference);
        roomsRefr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //show room list
                String winner = "";
                Iterable<DataSnapshot> rooms = snapshot.getChildren();
                for (DataSnapshot snapshot1 : rooms) {
                    String name = (String) snapshot1.child("name").getValue();
                    Long count = (Long) snapshot1.child("count").getValue();
                    if(count!=null) {
                        Log.e("result",name);
                        Log.e("result",""+count);

                        if (count > highest) {
                            highest = count;
                            winner = name;
                        }
                    }
                }
                textView1.setText("THE WINNER IS: " + winner);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //N/A
            }
        });
    }

    private void goHomeEventListener() {
        roomRefr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Go To Home Page
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                button.setText("HOME");
                button.setEnabled(true);
                Toast.makeText(MainActivity5.this, "ERROR CONNECTING", Toast.LENGTH_SHORT).show();
            }
        });
    }

}