package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StartScreenActivity extends Activity {

	Animation animationFadeIn;
	Animation animationFadeOut;
	ImageView startImage;
	boolean gameStart;
	final int SPLASH_DISPLAY_LENGTH = 1700;
	
	//MusicHandler mHandler;

	//MusicHandler mHandler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startscreen);

		//SingletonSoundPool.shared().release();
		
		//mHandler = new MusicHandler(this);
		//mHandler.load(R.raw.start2, true);
		//mHandler.play(2000);
		
		//SingletonBGMPlayer.shared(StartScreenActivity.this, R.raw.start2).getGetMediaPlayer().stop();
		//SingletonBGMPlayer.shared(StartScreenActivity.this, R.raw.start2).getGetMediaPlayer().release();
		
		SingletonBGMPlayer player = SingletonBGMPlayer.shared(this, R.raw.start2);
		try {
			Log.i("RoomEscape-StartScreen", "MediaPlayer start");
			player.getGetMediaPlayer().start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gameStart = false;

		// TODO Auto-generated method stub
		animationFadeIn = AnimationUtils.loadAnimation(this, R.animator.fadein);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeout);

		startImage = (ImageView) findViewById(R.id.startImage);
		startImage.startAnimation(animationFadeIn);

		startImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!gameStart) {
					// TODO Auto-generated method stub
					Log.i("GMLRME_StartScreen", "GameStart");
					gameStart = true;
					//mHandler.pause(SPLASH_DISPLAY_LENGTH);
					startImage.startAnimation(animationFadeOut);

					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							Intent mainIntent = new Intent(
									StartScreenActivity.this,
									StageSelectScreenActivity.class);
							mainIntent
									.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							StartScreenActivity.this.startActivity(mainIntent);
							StartScreenActivity.this.finish();
						}
					}, SPLASH_DISPLAY_LENGTH);
				}
			}
		});
	}
	
	public void onBackPressed() {
		//mHandler.pause(2000);
		SingletonBGMPlayer.shared(StartScreenActivity.this, R.raw.start2).getGetMediaPlayer().stop();
		this.finish();
	}

}
