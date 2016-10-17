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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StageOneIntroStoryActivityTypeTwo extends Activity implements OnClickListener {

	ImageButton next_Btn;
	ImageView stage1_1_mainChar_1;

	int dialogcount = 0;
	int charactercount = 0;
	LinearLayout textbubbleFamily;
	LinearLayout textbubbleEventGirl;
	LinearLayout textbubbleKid;
	TextView textFamily;
	TextView textEventGirl;
	TextView textKid;
	
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_stageoneintrostorytypetwo);
	    
		next_Btn = (ImageButton) findViewById(R.id.stage1_1_next_btn);
		next_Btn.setOnClickListener(this);
		
		stage1_1_mainChar_1 = (ImageView) findViewById(R.id.stage1_1_mainChar_1);
		
		textbubbleFamily = (LinearLayout) findViewById(R.id.stage1_1_textbubble1);
		textbubbleEventGirl = (LinearLayout) findViewById(R.id.stage1_1_event_textbubble1);
		
		textFamily = (TextView) findViewById(R.id.stage1_1_textview1);
		textEventGirl = (TextView) findViewById(R.id.stage1_1_event_textview1);
		
		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_1_textbubble2);
		textKid = (TextView) findViewById(R.id.stage1_1_textview2);
		
		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageoneintrotypetwo);
		thisLayout.startAnimation(animationFadeIn);
		
		SingletonBGMPlayer player = SingletonBGMPlayer.shared(this, R.raw.park);
		try {
			Log.i("RoomEscape-StartScreen", "MediaPlayer start");
			player.getGetMediaPlayer().start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_1_dialog_" + Integer.toString(dialogcount + 1), "string",
				"com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_1_character_" + Integer.toString(charactercount + 1), "string",
				"com.ys.gmlrme");
		type = getString(resourceID);
		
		if(type.equalsIgnoreCase("family")) {
			textbubbleFamily.setVisibility(View.VISIBLE);
			textbubbleEventGirl.setVisibility(View.INVISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			textFamily.setText(dialogString);
		} else if(type.equalsIgnoreCase("girl")) {
			textbubbleFamily.setVisibility(View.INVISIBLE);
			textbubbleEventGirl.setVisibility(View.VISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			textEventGirl.setText(dialogString);
		} else if(type.equalsIgnoreCase("kid")) {
			textbubbleFamily.setVisibility(View.INVISIBLE);
			textbubbleEventGirl.setVisibility(View.INVISIBLE);
			textbubbleKid.setVisibility(View.VISIBLE);
			textKid.setText(dialogString);
		} else if(type.equalsIgnoreCase("popup")) {
			Intent mainIntent = new Intent(
					StageOneIntroStoryActivityTypeTwo.this,
					StageOneIntroStoryActivityTypeRullet.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageOneIntroStoryActivityTypeTwo.this.startActivity(mainIntent);
			next_Btn.setEnabled(false);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					stage1_1_mainChar_1.setImageResource(R.drawable.family_amazing);
					textbubbleKid.setVisibility(View.VISIBLE);
					textKid.setText("Çה!!!!");
					next_Btn.setEnabled(true);
				}
			}, 3500);
		} else if(type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);
				SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeTwo.this, R.raw.park).fadeVolume();
				
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneIntroStoryActivityTypeTwo.this,
								StageOneIndiaActivityTypeOne.class);
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneIntroStoryActivityTypeTwo.this.startActivity(mainIntent);
						StageOneIntroStoryActivityTypeTwo.this.finish();
						//SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeTwo.this, R.raw.park).release();
					}
				}, 810);
				
				Log.i("GMLRME-Stage1IntroType2", "next");
			}
		}
		
		dialogcount++;
		charactercount++;
	}
	
	public void onBackPressed() {
		// mHandler.pause(2000);
		SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeTwo.this, R.raw.park).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneIntroStoryActivityTypeTwo.this, R.raw.park).release();
		this.finish();
	}

}
