package com.example.android.thequizapp;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView position;
    private TextView timer;
    private String mAnswer;
    private int mScore;
    private int QuestionNumber =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        timer = findViewById(R.id.timer);           //not implemented yet
        position  = findViewById(R.id.position);
        mScoreView = findViewById(R.id.score0);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4); // not implemented yet


        Intent intent = getIntent();
        int  intpos = intent.getIntExtra("position",0);
        String sin = Integer.toString(intpos);
        position.setText(sin);
        updateQuestion();


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText()== mAnswer){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText()== mAnswer){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText()== mAnswer){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText()== mAnswer){
                    mScore+=10;
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(QuizActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(QuizActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
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
        QuestionNumber++;}
        else{
            Toast.makeText(this, "Quiz is over ", Toast.LENGTH_SHORT).show();
            Intent scoreIntent= new Intent(QuizActivity.this,ScoreActivity.class);
            scoreIntent.putExtra("score",mScore);
            startActivity(scoreIntent);
        }
    }





    private void updateScore(int score){
        mScoreView.setText(""+ mScore);
    }
}
