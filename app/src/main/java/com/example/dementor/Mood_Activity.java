package com.example.dementor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Mood_Activity extends AppCompatActivity {

    TextView textView;
    Button button;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        button = findViewById(R.id.submit);
        ratingBar = findViewById(R.id.rating_Bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), s + " Star", Toast.LENGTH_SHORT).show();
            }
            //void startActivity(new Intent(Mood_Activity.this, MainActivity.class));
            //Mood_Activity.this.finish();
        });
    }

    public void submitStars(View view){
        System.out.println("Star Amount: "+ratingBar.getRating());
    }

}