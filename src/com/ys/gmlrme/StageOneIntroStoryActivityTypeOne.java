package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class StageOneIntroStoryActivityTypeOne extends Activity implements
		OnClickListener {

	ImageButton next_Btn;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_stageoneintrostory);

		next_Btn = (ImageButton) findViewById(R.id.stage1_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageoneintrotypeone);
		thisLayout.startAnimation(animationFadeIn);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (!next) {
			thisLayout.startAnimation(animationFadeOut);
			next = true;
			SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeOne.this, R.raw.start2).fadeVolume();
			
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent mainIntent = new Intent(
							StageOneIntroStoryActivityTypeOne.this,
							StageOneIntroStoryActivityTypeTwo.class);
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					StageOneIntroStoryActivityTypeOne.this
							.startActivity(mainIntent);
					StageOneIntroStoryActivityTypeOne.this.finish();		
					//SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeOne.this, R.raw.start2).getGetMediaPlayer().stop();
					//SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeOne.this, R.raw.start2).release();
				}
			}, 810);

		}
	}
	
	public void onBackPressed() {
		// mHandler.pause(2000);
		SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeOne.this, R.raw.start2).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeOne.this, R.raw.start2).release();
		this.finish();
	}

}
