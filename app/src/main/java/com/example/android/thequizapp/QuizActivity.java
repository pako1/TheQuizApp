package com.example.android.thequizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends BaseClass {



    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button  mButtonChoice1;
    private Button  mButtonChoice2;
    private Button  mButtonChoice3;
    private Button  mButtonChoice4;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private TextView position;
    private TextView timer;
    private String mAnswer;
    private int mScore;
    int QuestionNumber =0;
    int hearts=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        //setup the views
        setup();
        // which category is playing
        Intent intent = getIntent();
        int  intpos = intent.getIntExtra("position",0);
        String sin = Integer.toString(intpos);
        position.setText(sin);


        //updating the questions
        mQuestionLibrary.initQuestions(getBaseContext());
        updateQuestion();


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    losingLife();
                    updateQuestion();
                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    losingLife();
                    updateQuestion();
                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    losingLife();
                    updateQuestion();
                }
            }
        });


        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice4.getText().equals(mAnswer)){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    losingLife();
                    updateQuestion();
                }
            }
        });


    }


    private void updateQuestion(){
        if(QuestionNumber<mQuestionLibrary.getLength()){
        mQuestionView.setText(mQuestionLibrary.getQuestion(QuestionNumber).trim());
        mButtonChoice1.setText(mQuestionLibrary.getChoice(QuestionNumber, 1).trim());
        mButtonChoice2.setText(mQuestionLibrary.getChoice(QuestionNumber,2).trim());
        mButtonChoice3.setText(mQuestionLibrary.getChoice(QuestionNumber,3).trim());
        mButtonChoice4.setText(mQuestionLibrary.getChoice(QuestionNumber,4).trim());
        mAnswer = mQuestionLibrary.getCorrectAnswer(QuestionNumber).trim();
        QuestionNumber++;
        ctimer.cancel();
        timer.setTextColor(getResources().getColor(R.color.ColorFonts));
        ctimer.start();
        }
        else{
            Toast.makeText(this, "Quiz is over ", Toast.LENGTH_SHORT).show();
            ctimer.cancel();
            goToScore();

        }
    }

    CountDownTimer ctimer = new CountDownTimer(12000,1000) {
        @Override
        public void onTick(long l) {
            timer.setText(String.valueOf(l / 1000));
            if(l<=4000){
                timer.setTextColor(Color.RED);
            }

        }

        @Override
        public void onFinish() {
            losingLife();
            updateQuestion();
            timer.setTextColor(getResources().getColor(R.color.ColorFonts));
        }
    }.start();



    private void losingLife(){
        hearts--;
        if(hearts==2){
            heart1.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
         }
        if (hearts==1){
            heart2.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
        }
        if (hearts==0){
            heart3.setVisibility(View.GONE);
            ctimer.cancel();
            goToScore();
        }
    }

    private void goToScore(){
        Intent scoreIntent= new Intent(QuizActivity.this,ScoreActivity.class);
        scoreIntent.putExtra("score",mScore);
        scoreIntent.putExtra("position",1);
        startActivity(scoreIntent);
        finish();
    }


    private void setup(){

        timer     = findViewById(R.id.timer);
        position  = findViewById(R.id.position);
        mScoreView = findViewById(R.id.score0);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        heart1 =findViewById(R.id.heart1);
        heart2 =findViewById(R.id.heart2);
        heart3 =findViewById(R.id.heart3);

    }




    private void updateScore(int mScore){
        mScoreView.setText(String.valueOf(mScore));
    }

    @Override
    public void onPause() {
        super.onPause();
        basePause();
        if(ctimer!=null){
            ctimer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Music();


    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (ctimer!=null){
            ctimer.cancel();
        }
    }


}
