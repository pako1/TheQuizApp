package com.example.android.thequizapp;

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
    Button   reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        setup();
        setupScore();

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
    }

    private void setupScore(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        flagscore.setText(String.valueOf(preferences.getInt("flagscore",0)));
        artscore.setText(String.valueOf(preferences.getInt("artscore",0)));
    }

}
