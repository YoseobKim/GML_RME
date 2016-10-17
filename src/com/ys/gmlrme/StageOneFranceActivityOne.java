package com.ys.gmlrme;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class StageOneFranceActivityOne extends Activity implements OnClickListener {

	ImageButton next_Btn;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_stageonefranceone);

		next_Btn = (ImageButton) findViewById(R.id.stage1_france_typeone_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageonefrancetypeone);
		thisLayout.startAnimation(animationFadeIn);
		
		SingletonBGMPlayer player = SingletonBGMPlayer
				.shared(this, R.raw.france);
		try {
			Log.i("RoomEscape-StartScreen", "MediaPlayer start");
			player.getGetMediaPlayer().start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final MediaPlayer mp = MediaPlayer.create(this, R.raw.flight);
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				mp.stop();
				mp.release();
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		if (!next) {
			thisLayout.startAnimation(animationFadeOut);
			next = true;

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent mainIntent = new Intent(
							StageOneFranceActivityOne.this,
							StageOneFranceActivityTwo.class);
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					StageOneFranceActivityOne.this.startActivity(mainIntent);
					StageOneFranceActivityOne.this.finish();
				}
			}, 810);

		}
	}
	
	public void onBackPressed() {
		// mHandler.pause(2000);
		SingletonBGMPlayer.shared(StageOneFranceActivityOne.this, R.raw.france).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneFranceActivityOne.this, R.raw.france).release();
		this.finish();
	}

}
