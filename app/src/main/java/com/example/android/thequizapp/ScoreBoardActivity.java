package com.example.android.thequizapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreBoardActivity extends AppCompatActivity {
    TextView flagscore;
    TextView artscore;
    TextView multiscore;
    TextView puzzlescore;
    TextView TotalPoints;
    Button reset;
    Dialog myDialog;
    Button btnYes;
    Button btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        myDialog = new Dialog(this);
        setup();
        setupScores();
        calculateTotal();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog() {

        myDialog.setContentView(R.layout.custom_dialog);
        btnYes = myDialog.findViewById(R.id.yes);
        btnNo = myDialog.findViewById(R.id.no);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetall();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        myDialog.setCancelable(false);


    }


    private void setup() {

        puzzlescore = findViewById(R.id.puzzle_score);
        multiscore = findViewById(R.id.multi_score);
        flagscore = findViewById(R.id.flag_score);
        artscore = findViewById(R.id.artscore);
        reset = findViewById(R.id.ResetButton);
        TotalPoints = findViewById(R.id.Points);

    }

    private void setupScores() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        flagscore.setText(String.valueOf(preferences.getInt("flagscore", 0)));
        artscore.setText(String.valueOf(preferences.getInt("artscore", 0)));
        multiscore.setText(String.valueOf(preferences.getInt("multiplescore", 0)));
        puzzlescore.setText(String.valueOf(preferences.getInt("puzzlescore", 0)));

    }

    private void calculateTotal() {

        int Sum = 0;
        int flagscore1 = Integer.parseInt(flagscore.getText().toString());
        int artscore1 = Integer.parseInt(artscore.getText().toString());
        int multiscore1 = Integer.parseInt(multiscore.getText().toString());
        int puzzlescore1 = Integer.parseInt(multiscore.getText().toString());

        Sum = Sum + flagscore1 + artscore1 + multiscore1 + puzzlescore1;
        TotalPoints.setText(String.valueOf(Sum));
    }

    private void resetall() {

        SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mypref.edit();
        editor.putInt("flagscore", 0);
        editor.putInt("artscore", 0);
        editor.putInt("multiplescore", 0);
        editor.putInt("puzzlescore", 0);
        editor.apply();
        recreate();

    }

}
