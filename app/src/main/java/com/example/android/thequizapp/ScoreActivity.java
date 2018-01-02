package com.example.android.thequizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreActivity extends BaseClass {

    private TextView finalTextScore;
    private TextView bestScoreView;
    private ImageButton goMenu;
    private ImageButton playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        //setting up the views
        setup();

        finalTextScore.setText(String.valueOf(score));
        //calculating the score
        calculateScore(score);


        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound();
                Intent returnIntent = new Intent(ScoreActivity.this,MenuActivity.class);
                startActivity(returnIntent);
                finish();
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound();
                Intent goplayintent = new Intent(ScoreActivity.this,GameActivity.class);
                startActivity(goplayintent);
                finish();
            }
        });


    }


    private void setup(){

        finalTextScore = findViewById(R.id.roundScore);
        bestScoreView = findViewById(R.id.bestscore);
        goMenu = findViewById(R.id.gomenu);
        playAgain = findViewById(R.id.playagain);

    }

    private void calculateScore(int score){
        SharedPreferences mypref =  getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);

        if (highscore>=score){
            bestScoreView.setText(String.valueOf(highscore));
        }
        else{
            bestScoreView.setText(String.valueOf(score));
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore",score);
            editor.apply();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        basePause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseResume();
    }






}
