package com.example.android.thequizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FlagActivity extends BaseClass {

    private TextView  mScoreView;
    private Button    mButtonCheckAnswer;
    private Button    mButtonHint;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private TextView  mQuestionNumber; // den to xrisimopoiw akoma gia na dei3w se poia erwtisi eimai
    private EditText  mInput;
    ImageView imageCountry;
    private String    mAnswer;
    private String    input;
    private int       mScore;
    private int       hearts = 3;
    private int       FlagNumber = 0;
    private FlagBank mFlagLibrary = new FlagBank();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        //setup the views
        setup();

        mFlagLibrary.initCountries(getBaseContext());
        updateCountry();


        mButtonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                input = mInput.getEditableText().toString().trim();
                if(input.equals(mAnswer)) {
                    mScore+=10;
                    updateScore(mScore);
                    updateCountry();
                }
                else if(input.isEmpty()){
                    Toast.makeText(FlagActivity.this, "Enter a country", Toast.LENGTH_SHORT).show();
                }
                else{
                    flag_losingLife();
                }
            }
        });


    }


    private void setup(){
        imageCountry       = findViewById(R.id.imagecountry);
        mInput             = findViewById(R.id.flag_input);
        mQuestionNumber    = findViewById(R.id.flag_questionNumber);
        mButtonCheckAnswer = findViewById(R.id.flag_checkanswer);
        mButtonHint        = findViewById(R.id.flag_hints);
        mScoreView         = findViewById(R.id.flag_score0);
        heart1             = findViewById(R.id.flag_heart1);
        heart2             = findViewById(R.id.flag_heart2);
        heart3             = findViewById(R.id.flag_heart3);

    }

    private void goToScore(){
        Intent scoreIntent= new Intent(FlagActivity.this,ScoreActivity.class);
        scoreIntent.putExtra("score",mScore);
        scoreIntent.putExtra("position",2);
        startActivity(scoreIntent);
        finish();
    }

    private void flag_losingLife(){
        hearts--;
        if (hearts==2){
            heart1.setVisibility(View.GONE);
        }
        if (hearts==1){
            heart2.setVisibility(View.GONE);
        }
        if (hearts==0){
            heart3.setVisibility(View.GONE);
            goToScore();
        }
    }

    private void updateScore(int mScore){
        mScoreView.setText(String.valueOf(mScore));
    }

    private void updateCountry(){
        if (FlagNumber<mFlagLibrary.getFlagLength()){

            int ImageId = this.getResources().getIdentifier(mFlagLibrary.getCountryImage(FlagNumber).toLowerCase(),"drawable",getPackageName());
            imageCountry.setBackgroundResource(ImageId);
            mAnswer = mFlagLibrary.getFlagAnswer(FlagNumber).trim();
            FlagNumber++;
            mInput.getText().clear();

        }
        else{
            Toast.makeText(this,"Quiz is over",Toast.LENGTH_SHORT).show();
            goToScore();
        }

    }



}
