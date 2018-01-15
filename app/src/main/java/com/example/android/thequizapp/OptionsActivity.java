package com.example.android.thequizapp;



import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;



public class OptionsActivity extends BaseClass {

    public ToggleButton soundButton;
    private ImageButton germanyButton;
    private ImageButton greeceButton;
    private ImageButton englishButton;
    private ToggleButton musicButton;
    private String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_main);
        setUpViews();


        germanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "german", Toast.LENGTH_SHORT).show();
                lang = "de";
                changeLocale(lang);
                saveLocale(lang);
                recreate();

            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "english", Toast.LENGTH_SHORT).show();
                lang = "en";
                changeLocale(lang);
                saveLocale(lang);
                recreate();
            }
        });
        greeceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Toast.makeText(OptionsActivity.this, "greek", Toast.LENGTH_SHORT).show();
                lang = "el";
                changeLocale(lang);
                saveLocale(lang);
                recreate();
            }
        });

        musicButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    ToggleSoundOn();
                    setMusicPref("ON");
                    setupOnorOff();
                    Music();
                }
                if(!isChecked){
                    ToggleSoundOff();
                    setMusicPref("OFF");
                    setupOnorOff();
                    Music();

                }
            }
        });


        soundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


            }
        });

    }



    @Override
    protected void onPause() {
        super.onPause();
        basePause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music();
    }

    @Override
    protected void onStart(){
        super.onStart();
        setupOnorOff();
        Music();
    }




    private void setupOnorOff(){

        String check = getMusicPref();
        if (check.equals("ON")){
            musicButton.setBackgroundResource(R.drawable.on);
        }
        if (check.equals("OFF")){
            musicButton.setBackgroundResource(R.drawable.off);
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setUpViews() {
        germanyButton = findViewById(R.id.germanyButton);
        greeceButton = findViewById(R.id.greeceButton);
        englishButton = findViewById(R.id.englandButton);
        musicButton = findViewById(R.id.music);
        soundButton = findViewById(R.id.sound);

    }



}

