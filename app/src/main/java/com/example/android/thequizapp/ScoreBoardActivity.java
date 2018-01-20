package com.example.android.thequizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreBoardActivity extends AppCompatActivity {
    TextView flagscore;
    TextView artscore;
    TextView TotalPoints;
    Button   reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        setup();
        setupScores();
        calculateTotal();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mypref.edit();
                editor.putInt("flagscore", 0);
                editor.putInt("artscore", 0);
                editor.apply();
                recreate();
            }
        });

    }


    private void setup(){
        flagscore = findViewById(R.id.flag_score);
        artscore  = findViewById(R.id.artscore);
        reset     = findViewById(R.id.ResetButton);
        TotalPoints =findViewById(R.id.Points);
    }

    private void setupScores(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        flagscore.setText(String.valueOf(preferences.getInt("flagscore",0)));
        artscore.setText(String.valueOf(preferences.getInt("artscore",0)));
    }

    private void calculateTotal(){
        int Sum=0;
        int flagscore1 = Integer.parseInt(flagscore.getText().toString());
        int artscore1  = Integer.parseInt(artscore.getText().toString());
        Sum=Sum+flagscore1+artscore1 ;
        TotalPoints.setText(String.valueOf(Sum));
    }

}
