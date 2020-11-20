package com.example.paintsplat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity4 extends AppCompatActivity implements UpdateCount{
        private int updatedCount = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(new MovementView(this,this));

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                intent.putExtra("dot_count",updatedCount);
                startActivity(intent);
                finish();
            }, 10000);
        }

        @Override
        public void updateCount(int count) {
            updatedCount = count;
        }
    }