package com.example.android.thequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends BaseClass {


    private QuestionsLibrary mQuestionLibrary = new QuestionsLibrary();

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
    private int QuestionNumber =0;
    private int hearts=3;
    MediaPlayer mMediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);



        setup();
        // which category is playing
        Intent intent = getIntent();
        int  intpos = intent.getIntExtra("position",0);
        String sin = Integer.toString(intpos);
        position.setText(sin);

        //setup the views

        //updating the questions
        updateQuestion();



        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice1.getText()== mAnswer){
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
                if(mButtonChoice2.getText()== mAnswer){
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


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if(mButtonChoice1.getText()== mAnswer){
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
                if(mButtonChoice3.getText()== mAnswer){
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
        mQuestionView.setText(mQuestionLibrary.getQuestion(QuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(QuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(QuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(QuestionNumber));
        mAnswer = mQuestionLibrary.getCorrectAnswer(QuestionNumber);
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
        if(hearts==3){
            heart3.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
         }
        if (hearts==2){
            heart2.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
        }
        if (hearts==1){
            heart1.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
        }
        if(hearts==0){
           ctimer.cancel();
           goToScore();
        }
    }

    private void goToScore(){
        Intent scoreIntent= new Intent(QuizActivity.this,ScoreActivity.class);
        scoreIntent.putExtra("score",mScore);
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
        mButtonChoice4 = findViewById(R.id.choice4); // not implemented yet
        heart1 =findViewById(R.id.heart1);
        heart2 =findViewById(R.id.heart2);
        heart3 =findViewById(R.id.heart3);

    }




    private void updateScore(int score){
        mScoreView.setText(""+ mScore);
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
        baseResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (ctimer!=null){
            ctimer.cancel();
        }
    }


}
