package com.example.android.thequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {

    CheckBox ONE,TWO,THREE;
    Button Confirm;
    int mScore = 0;
    TextView question;
    String quest = "mention 2 of the greek rebelions in 1921";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
            setup();
            question.setText(quest);
            Confirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

               checkQuestionOneAnswers();

                }
            });

    }

    private void checkQuestionOneAnswers() {
        boolean isQuestionOneMarioChecked = ONE.isChecked();
        boolean isQuestionOneDonkeyChecked = TWO.isChecked();
        boolean isQuestionOnePortalChecked = THREE.isChecked();

        if (isQuestionOneMarioChecked && isQuestionOneDonkeyChecked && !isQuestionOnePortalChecked) {
            mScore += 10;
            goToScore();
        }else{
            Toast.makeText(this, "Wrong choice,try again", Toast.LENGTH_SHORT).show();
        }

   }

   private void setup(){

       ONE = findViewById(R.id.checkBox1);
       TWO = findViewById(R.id.checkBox2);
       THREE = findViewById(R.id.checkBox3);
       Confirm = findViewById(R.id.button1);
       question = findViewById(R.id.questionCheck);

   }

    private void goToScore() {

        Intent scoreIntent = new Intent(CheckBoxActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("score", mScore);
        scoreIntent.putExtra("position", 4);
        startActivity(scoreIntent);
        finish();

    }



}
