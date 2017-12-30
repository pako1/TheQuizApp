package com.example.android.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Πακος on 18/12/2017.
 */

public class SplashscreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashscreenActivity.this, MenuActivity.class));
        finish();
    }

}
