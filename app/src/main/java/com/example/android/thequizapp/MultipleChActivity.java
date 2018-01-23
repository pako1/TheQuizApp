package com.example.android.thequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//1. Mou leipei na ftiaxw diki toy pinaka me erwtiseis kai apantiseis.


public class MultipleChActivity extends BaseClass {

    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mRScoreView;
    private TextView mRQuestionView;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioChoice1;
    private RadioButton mRadioChoice2;
    private RadioButton mRadioChoice3;
    private RadioButton mRadioChoice4;
    private ImageButton HomeRButton;
    private TextView SkipRQuestion;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private Button submit;
    private RadioButton radiob;
    private TextView position;
    private TextView timer;
    private String mAnswer;
    private int mScore;
    private int QuestionNumber = 0;
    private int hearts = 3;
    CountDownTimer ctimer = new CountDownTimer(12000, 1000) {
        @Override
        public void onTick(long l) {
            timer.setText(String.valueOf(l / 1000));
            if (l <= 4000) {
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
    private int skiptimes = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_ch);
        setup();

        Intent intent = getIntent();
        int intpos = intent.getIntExtra("position", 0);
        String sin = Integer.toString(intpos);
        position.setText(sin);

        //updating the questions
        mQuestionLibrary.initQuestions(getBaseContext());
        updateQuestion();

        HomeRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Intent returnIntent = new Intent(MultipleChActivity.this, MenuActivity.class);
                startActivity(returnIntent);
                finish();
            }
        });


        SkipRQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                if (skiptimes > 0) {
                    skiptimes--;
                    updateQuestion();
                } else {
                    Toast.makeText(MultipleChActivity.this, "You have used all your skips", Toast.LENGTH_SHORT).show();
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected_id = mRadioGroup.getCheckedRadioButtonId();
                radiob = findViewById(selected_id);

                if (selected_id == R.id.radibutton1) {
                    if (radiob.getText().toString().equals(mAnswer)) {
                        mScore += 10;
                        updateScore(mScore);
                        updateQuestion();
                        Toast.makeText(MultipleChActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MultipleChActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                        losingLife();
                        updateQuestion();
                    }

                } else if (selected_id == R.id.radibutton2) {
                    if (radiob.getText().toString().equals(mAnswer)) {
                        mScore += 10;
                        updateScore(mScore);
                        updateQuestion();
                        Toast.makeText(MultipleChActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MultipleChActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                        losingLife();
                        updateQuestion();
                    }
                } else if (selected_id == R.id.radibutton3) {
                    if (radiob.getText().toString().equals(mAnswer)) {
                        mScore += 10;
                        updateScore(mScore);
                        updateQuestion();
                        Toast.makeText(MultipleChActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MultipleChActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                        losingLife();
                        updateQuestion();
                    }
                } else if (selected_id == R.id.radibutton4) {
                    if (radiob.getText().toString().equals(mAnswer)) {
                        mScore += 10;
                        updateScore(mScore);
                        updateQuestion();
                        Toast.makeText(MultipleChActivity.this, "Nice done!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MultipleChActivity.this, "oh wrong", Toast.LENGTH_SHORT).show();
                        losingLife();
                        updateQuestion();
                    }
                }
            }
        });


    }

    private void updateQuestion() {
        if (QuestionNumber < mQuestionLibrary.getLength()) {
            mRQuestionView.setText(mQuestionLibrary.getQuestion(QuestionNumber).trim());
            mRadioChoice1.setText(mQuestionLibrary.getChoice(QuestionNumber, 1).trim());
            mRadioChoice2.setText(mQuestionLibrary.getChoice(QuestionNumber, 2).trim());
            mRadioChoice3.setText(mQuestionLibrary.getChoice(QuestionNumber, 3).trim());
            mRadioChoice4.setText(mQuestionLibrary.getChoice(QuestionNumber, 4).trim());
            mAnswer = mQuestionLibrary.getCorrectAnswer(QuestionNumber).trim();
            QuestionNumber++;
            ctimer.cancel();
            timer.setTextColor(getResources().getColor(R.color.ColorFonts));
            ctimer.start();
        } else {
            Toast.makeText(this, "Quiz is over ", Toast.LENGTH_SHORT).show();
            ctimer.cancel();
            goToScore();

        }
    }

    private void losingLife() {
        hearts--;
        if (hearts == 2) {
            heart1.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
        }
        if (hearts == 1) {
            heart2.setVisibility(View.GONE);
            ctimer.cancel();
            ctimer.start();
        }
        if (hearts == 0) {
            heart3.setVisibility(View.GONE);
            ctimer.cancel();
            goToScore();
        }
    }

    private void goToScore() {
        Intent scoreIntent = new Intent(MultipleChActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("score", mScore);
        scoreIntent.putExtra("position", 2);
        startActivity(scoreIntent);
        finish();
    }

    private void updateScore(int mScore) {
        mRScoreView.setText(String.valueOf(mScore));
    }


    private void setup() {
        submit = findViewById(R.id.submit);
        mRadioGroup = findViewById(R.id.RadioGroup);
        SkipRQuestion = findViewById(R.id.skipQuestion);
        HomeRButton = findViewById(R.id.homeButton);
        timer = findViewById(R.id.timer);
        position = findViewById(R.id.position);
        mRScoreView = findViewById(R.id.score0);
        mRQuestionView = findViewById(R.id.question);
        mRadioChoice1 = findViewById(R.id.radibutton1);
        mRadioChoice2 = findViewById(R.id.radibutton2);
        mRadioChoice3 = findViewById(R.id.radibutton3);
        mRadioChoice4 = findViewById(R.id.radibutton4);
        heart1 = findViewById(R.id.Rheart1);
        heart2 = findViewById(R.id.Rheart2);
        heart3 = findViewById(R.id.Rheart3);
    }

    @Override
    public void onPause() {
        super.onPause();
        basePause();
        if (ctimer != null) {
            ctimer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Music();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ctimer != null) {
            ctimer.cancel();
        }
    }


}
