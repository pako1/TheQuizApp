package com.example.android.thequizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Πακος on 20/12/2017.
 */

public class AchievmentActivity extends BaseClass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievment_main);
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



}
