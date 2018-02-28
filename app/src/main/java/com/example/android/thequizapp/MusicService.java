package com.example.android.thequizapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;


public class MusicService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private MediaPlayer player;
    private int length = 0;


    public class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override

    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        destroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public void play() {
        try {
            if (player == null || !player.isPlaying()) {
                player = MediaPlayer.create(this, R.raw.sound);
                player.setLooping(true);
                player.setVolume(0.6f, 0.6f);
                // player.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (player.isPlaying() && null != player) {
            player.pause();
            length = player.getCurrentPosition();
        }
    }

    public void resume() {
        try {
            if (player != null && !player.isPlaying()) {
                player.seekTo(length);
                player.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }

            player.release();
            player = null;
        }
    }

}