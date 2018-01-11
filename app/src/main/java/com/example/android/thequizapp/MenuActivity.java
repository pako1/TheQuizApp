package com.example.android.thequizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends BaseClass  {

    private Button newgame;
    private Button store;
    private Button achievments;
    private Button options;
    private ImageButton fblike;
    private ImageButton share;
    private ImageButton twitter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
       setUp();
       loadLocale();
       recreate();



        newgame.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickSound();
               Intent gameIntent = new Intent(MenuActivity.this, GameActivity.class);
               startActivity(gameIntent);

           }
       });



       achievments.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickSound();
               Intent achievIntent = new Intent(MenuActivity.this, AchievmentActivity.class);
               startActivity(achievIntent);

           }
       });

       options.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clickSound();
               Intent optionsIntent = new Intent(MenuActivity.this, OptionsActivity.class);
               startActivity(optionsIntent);
           }
       });

       store.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                clickSound();
               Intent storeIntent = new Intent(MenuActivity.this, StoreActivity.class);
               startActivity(storeIntent);
           }
       });

       twitter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String urltw= "https://twitter.com/?lang=el";
               Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
               twitterIntent.setData(Uri.parse(urltw));
               startActivity(twitterIntent);
           }
       });


       fblike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String urlfb= "https://www.facebook.com/";
               Intent fbIntent = new Intent(Intent.ACTION_VIEW);
               fbIntent.setData(Uri.parse(urlfb));
               startActivity(fbIntent);
           }
       });

       share.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent sendIntent = new Intent();
               String sharebody  = "Start playing the game with me guys ";
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
               sendIntent.setType("text/plain");
               startActivity(Intent.createChooser(sendIntent,"share" ));

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
        Sound();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setUp() {
        newgame = findViewById(R.id.newgame);
        options = findViewById(R.id.options);
        achievments = findViewById(R.id.achievments);
        store = findViewById(R.id.store);
        twitter = findViewById(R.id.twitter);
        fblike = findViewById(R.id.likefb);
        share = findViewById(R.id.share);

    }


}
