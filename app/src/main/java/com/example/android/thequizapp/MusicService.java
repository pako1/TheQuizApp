package com.example.android.thequizapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;


public class MusicService extends Service
{
    private final IBinder mBinder = new LocalBinder();
    private MediaPlayer player;
    private int length =0;

    /**
     * Class for clients to access. Because we know this service always runs in
     * the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder
    {
        MusicService getService()
        {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate()
    {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // We want this service to continue running until it is explicitly stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        destroy();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }


    public void play()
    {
        try
        {
            player = MediaPlayer.create(this,R.raw.sound);
            player.setLooping(true);
            player.setVolume(0.1f, 0.1f);
            player.start();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void pause()
    {
        if(null != player && player.isPlaying())
        {
            player.pause();
            length = player.getCurrentPosition();
        }
    }


    public void resume()
    {
        try
        {
            if(null != player && !player.isPlaying())
            {
                player.seekTo(length);
                player.start();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void destroy()
    {
        if(player != null)
        {
            if(player.isPlaying())
            {
                player.stop();
            }

            player.release();
            player = null;
        }
    }

}