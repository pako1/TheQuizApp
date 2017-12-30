package com.example.android.thequizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView finalTextScore;
    private TextView bestScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        finalTextScore = findViewById(R.id.roundScore);
        bestScoreView = findViewById(R.id.bestscore);
        finalTextScore.setText(""+score);

        SharedPreferences mypref =  getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);
        if (highscore>=score){
            bestScoreView.setText(""+highscore);
        }
        else{
            bestScoreView.setText(""+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore",score);
            editor.commit();
        }
    }


}
