package com.ys.gmlrme;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SingletonBGMPlayer {
	static SingletonBGMPlayer _shared = null;
	
	private MediaPlayer mediaPlayer;
	private Context context;
	private int iVolume;
	private final static int INT_VOLUME_MAX = 100;
	private final static int INT_VOLUME_MIN = 0;
	private final static float FLOAT_VOLUME_MAX = 1;
	private final static float FLOAT_VOLUME_MIN = 0;

	
	public static SingletonBGMPlayer shared(Context ctx, int resId) {
		synchronized(SingletonBGMPlayer.class) {
			if(_shared == null) {
				_shared = new SingletonBGMPlayer(ctx, resId);
			}
		}
		return _shared;
	}
	
	private SingletonBGMPlayer(Context ctx, int resId) {
		context = ctx;
		Log.i("RoomEscape-BGMPlayer", "BGMPlayer create");
		mediaPlayer = MediaPlayer.create(ctx, resId);
		mediaPlayer.setVolume(1.0f, 1.0f);
		mediaPlayer.setLooping(true);
		iVolume = INT_VOLUME_MAX;
	}

	public MediaPlayer getGetMediaPlayer() {
		return mediaPlayer;
	}
	
	public void release() {
		_shared = null;
	}
	
	public void fadeVolume() {
		final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() 
        {
            @Override
            public void run() 
            {   
                if(iVolume > INT_VOLUME_MIN) {
                	updateVolume(-1);
                } else {
                	mediaPlayer.pause();
                	mediaPlayer.stop();
                	timer.cancel();
                	timer.purge();
                	_shared = null;
                	iVolume = INT_VOLUME_MAX;
                }
            }
        };

        timer.schedule(timerTask, 5, 5);

	}
	
	private void updateVolume(int change)
	{
	    //increment or decrement depending on type of fade
	    iVolume = iVolume + change;

	    //ensure iVolume within boundaries
	    if (iVolume < INT_VOLUME_MIN)
	        iVolume = INT_VOLUME_MIN;
	    else if (iVolume > INT_VOLUME_MAX)
	        iVolume = INT_VOLUME_MAX;

	    //convert to float value
	    float fVolume = 1 - ((float) Math.log(INT_VOLUME_MAX - iVolume) / (float) Math.log(INT_VOLUME_MAX));

	    //ensure fVolume within boundaries
	    if (fVolume < FLOAT_VOLUME_MIN)
	        fVolume = FLOAT_VOLUME_MIN;
	    else if (fVolume > FLOAT_VOLUME_MAX)
	        fVolume = FLOAT_VOLUME_MAX;     

	    mediaPlayer.setVolume(fVolume, fVolume);
	}

}