package com.example.android.thequizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class OptionsActivity extends BaseClass implements CompoundButton.OnCheckedChangeListener {

    public ToggleButton soundButton;
    private ImageButton germanyButton;
    private ImageButton greeceButton;
    private ImageButton englishButton;
    private ToggleButton musicButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_main);
        setUpViews();

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.xyle", MODE_PRIVATE);
        musicButton.setChecked(sharedPrefs.getBoolean("music", false));

        germanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "germany", Toast.LENGTH_SHORT).show();
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "english", Toast.LENGTH_SHORT).show();
            }
        });
        greeceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "greek", Toast.LENGTH_SHORT).show();
            }
        });

        musicButton.setOnCheckedChangeListener(this);
        soundButton.setOnCheckedChangeListener(this);


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



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




    public void setUpViews() {

        germanyButton = findViewById(R.id.germanyButton);
        greeceButton = findViewById(R.id.greeceButton);
        englishButton = findViewById(R.id.englandButton);
        musicButton = findViewById(R.id.music);
        soundButton = findViewById(R.id.sound);


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            if (buttonView == musicButton) {
                musicButton.setBackgroundResource(R.drawable.off);
                ToggleSoundOff();
                basePause();

            }
            if (buttonView == soundButton) {
                soundButton.setBackgroundResource(R.drawable.off);
                ToggleSoundOff();
            }
        } else {

            if (buttonView == musicButton) {
                musicButton.setBackgroundResource(R.drawable.on);
                ToggleSoundOn();
                baseResume();

            }
            if (buttonView == soundButton) {
                soundButton.setBackgroundResource(R.drawable.on);
                ToggleSoundOn();
            }
        }


    }




}

