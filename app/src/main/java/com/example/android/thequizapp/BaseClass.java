package com.example.android.thequizapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Locale;


public abstract class BaseClass extends AppCompatActivity {
    private MusicService mBoundService;
    private boolean mIsBound = false;
    MediaPlayer mMediaplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBindService();
    }

    protected void changeLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());


    }

    protected void saveLocale(String lang) {
        SharedPreferences languagepref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("languageToLoad", lang).apply();
    }

    protected void loadLocale() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String language = sharedPreferences.getString("languageToLoad", "");
        changeLocale(language);
    }

    protected void Music() {
        String music = getMusicPref();
        if (music.equals("ON")) {
            baseResume();
            Toast.makeText(this, "music on", Toast.LENGTH_SHORT).show();
        }
        if (music.equals("OFF")) {
            basePause();
            Toast.makeText(this, "music off", Toast.LENGTH_SHORT).show();

        }

    }

    protected void Sound() {
        String sound = getSoundPref();
        if (sound.equals("ON")) {
            Toast.makeText(this, "sound on", Toast.LENGTH_SHORT).show();
        }
        if (sound.equals("OFF")) {
            Toast.makeText(this, "sound off", Toast.LENGTH_SHORT).show();
        }
    }

    protected String getSoundPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return preferences.getString("SOUND", "ON");
    }

    protected String getMusicPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return preferences.getString("MUSIC", "ON");
    }

    protected void setSoundPref(String onORoff) {
        SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mypref.edit();
        if (onORoff.equals("ON")) {
            editor.putString("SOUND", "ON");
            Toast.makeText(this, "Preference has S on", Toast.LENGTH_SHORT).show();
            editor.apply();
        }
        if (onORoff.equals("OFF")) {
            editor.putString("SOUND", "OFF");
            Toast.makeText(this, "Preference has S off", Toast.LENGTH_SHORT).show();

            editor.apply();
        }
    }

    protected void setMusicPref(String onORoff) {
        SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mypref.edit();
        if (onORoff.equals("ON")) {
            editor.putString("MUSIC", "ON");
            Toast.makeText(this, "Preference has M on", Toast.LENGTH_SHORT).show();
            editor.apply();
        }
        if (onORoff.equals("OFF")) {
            editor.putString("MUSIC", "OFF");
            Toast.makeText(this, "Preference has M off", Toast.LENGTH_SHORT).show();
            editor.apply();
        }
    }

    protected void ToggleSoundOn() {
        mMediaplayer = MediaPlayer.create(this, R.raw.togglebuttonon);
        mMediaplayer.setVolume(0.2f, 0.2f);
        mMediaplayer.start();

    }

    protected void ToggleSoundOff() {
        mMediaplayer = MediaPlayer.create(this, R.raw.togglebuttonoff);
        mMediaplayer.setVolume(0.2f, 0.2f);
        mMediaplayer.start();

    }

    protected void clickSound() {
        mMediaplayer = MediaPlayer.create(this, R.raw.buttonsclick);
        mMediaplayer.setVolume(0.2f, 0.2f);
        mMediaplayer.start();
    }

    protected void baseResume() {
        try {
            if (mBoundService != null) {
                mBoundService.resume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void basePause() {
        try {
            if (mBoundService != null) {
                mBoundService.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
      public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service. Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            mBoundService = ((MusicService.LocalBinder) service).getService();

            if (mBoundService != null) {
                mBoundService.play();
            }
        }

    public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mBoundService = null;
            mBoundService.destroy();
        }
    };

    private void doBindService() {
        Intent i = new Intent(getApplicationContext(), MusicService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }
}