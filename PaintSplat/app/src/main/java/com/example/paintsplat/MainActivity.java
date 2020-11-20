package com.example.paintsplat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    Player player = new Player();

    FirebaseDatabase db;
    DatabaseReference playerRefr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);

        db = FirebaseDatabase.getInstance();

        //Checks if player is in system and gets their reference
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        player.name = preferences.getString("playerName", "");
        player.count = 0;
        if(!player.name.equals("")){
            playerRefr = db.getReference("players/" + player.name);
            addEventListener();
            playerRefr.setValue("");
        }

        //Logs player in
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.name = editText.getText().toString();
                player.count = 0;
                editText.setText("");
                if(!player.name.equals("")){
                    button.setText("LOGGING IN...");
                    button.setEnabled(false);
                    playerRefr = db.getReference("players/" + player.name);
                    addEventListener();
                }
            }
        });
    }

    private void addEventListener() {
        //read from db
        playerRefr.addValueEventListener(new ValueEventListener() {
            //Log in worked, saves name of player and goes to next screen
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!player.name.equals("")){
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("playerName", player.name);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }
            }

            //Log in error
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                button.setText("LOG IN");
                button.setEnabled(true);
                Toast.makeText(MainActivity.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}