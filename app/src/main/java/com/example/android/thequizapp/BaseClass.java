package com.example.android.thequizapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;


public class BaseClass extends AppCompatActivity {
    private MusicService mBoundService;
    private boolean mIsBound = false;
    MediaPlayer mMediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        doBindService();
    }


    protected void ToggleSoundOn(){
        mMediaplayer = MediaPlayer.create(this,R.raw.togglebuttonon);
        mMediaplayer.setVolume(0.2f,0.2f);
        mMediaplayer.start();

    }

    protected void ToggleSoundOff(){
        mMediaplayer = MediaPlayer.create(this,R.raw.togglebuttonoff);
        mMediaplayer.setVolume(0.2f,0.2f);
        mMediaplayer.start();

    }




    protected void clickSound(){
        mMediaplayer = MediaPlayer.create(this,R.raw.buttonsclick);
        mMediaplayer.setVolume(0.2f,0.2f);
        mMediaplayer.start();
    }

    protected void baseStop(){
        try
        {
            if(mBoundService != null)
            {
                mBoundService.destroy();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    protected void baseResume()
    {
        try
        {
            if(mBoundService != null)
            {
                mBoundService.resume();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    protected void basePause()
    {
        try
        {
            if(mBoundService != null)
            {
                mBoundService.pause();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service. Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            mBoundService = ((MusicService.LocalBinder) service).getService();

            if(mBoundService != null)
            {
                 mBoundService.play();
            }
        }

        public void onServiceDisconnected(ComponentName className)
        {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mBoundService = null;
            mBoundService.destroy();
        }
    };

    private void doBindService()
    {
        // Establish a connection with the service. We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).

        Intent i = new Intent(getApplicationContext(), MusicService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private void doUnbindService()
    {
        if (mIsBound)
        {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        doUnbindService();
    }
}