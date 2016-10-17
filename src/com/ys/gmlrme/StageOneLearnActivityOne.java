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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StageOneLearnActivityOne extends Activity implements
		OnClickListener {

	ImageButton next_Btn;

	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	RelativeLayout learnLayout;

	LinearLayout textbubbleKid;
	TextView textKid;

	LinearLayout textbubbleMom;
	TextView textMom;

	ImageView learnMaterial;
	ImageView learnMaterial2;
	ImageView learnMaterial3;

	int dialogcount = 0;
	int charactercount = 0;

	boolean next = false;
	boolean popup = false;
	boolean popup2 = false;
	boolean popup3 = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stageonelearnone);

		next_Btn = (ImageButton) findViewById(R.id.stage1_study1_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stage1learnone);
		thisLayout.startAnimation(animationFadeIn);

		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_study1_textbubble1);
		textKid = (TextView) findViewById(R.id.stage1_study1_textview1);

		textbubbleMom = (LinearLayout) findViewById(R.id.stage1_study1_textbubble2);
		textMom = (TextView) findViewById(R.id.stage1_study1_textview2);
		learnLayout = (RelativeLayout) findViewById(R.id.stage1_study1_learnLayout);

		learnMaterial = (ImageView) findViewById(R.id.stage1_study1_learn_material);
		learnMaterial2 = (ImageView) findViewById(R.id.stage1_study1_learn_material2);
		learnMaterial3 = (ImageView) findViewById(R.id.stage1_study1_learn_material3);
	
		SingletonBGMPlayer player = SingletonBGMPlayer.shared(this, R.raw.study);
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

		final MediaPlayer mp = MediaPlayer.create(this, R.raw.page);
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
		
		int resourceID = getResources().getIdentifier(
				"stage1_study1_dialog_" + Integer.toString(dialogcount + 1),
				"string", "com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_study1_character_"
						+ Integer.toString(charactercount + 1), "string",
				"com.ys.gmlrme");
		type = getString(resourceID);

		if (type.equalsIgnoreCase("mom")) {
			textbubbleMom.setVisibility(View.VISIBLE);
			textMom.setText(dialogString);
			textbubbleKid.setVisibility(View.INVISIBLE);
			/*
			 * textbubbleMother.setVisibility(View.VISIBLE);
			 * textbubbleGuide.setVisibility(View.INVISIBLE);
			 * textbubbleFather.setVisibility(View.INVISIBLE);
			 * textMother.setText(dialogString);
			 */
		} else if (type.equalsIgnoreCase("kid")) {
			textbubbleKid.setVisibility(View.VISIBLE);
			textKid.setText(dialogString);
			/*
			 * textbubbleMother.setVisibility(View.INVISIBLE);
			 * textbubbleGuide.setVisibility(View.INVISIBLE);
			 * textbubbleFather.setVisibility(View.VISIBLE);
			 * textFather.setText(dialogString);
			 */
		} else if (type.equalsIgnoreCase("draw")) {
			textbubbleMom.setVisibility(View.INVISIBLE);
			learnMaterial.setVisibility(View.VISIBLE);
		} else if (type.equalsIgnoreCase("draw2")) {
			textbubbleMom.setVisibility(View.INVISIBLE);
			learnMaterial.setVisibility(View.INVISIBLE);
			learnMaterial2.setVisibility(View.VISIBLE);
		} else if (type.equalsIgnoreCase("draw3")) {
			textbubbleMom.setVisibility(View.INVISIBLE);
			learnMaterial.setVisibility(View.INVISIBLE);
			learnMaterial2.setVisibility(View.INVISIBLE);
			learnMaterial3.setVisibility(View.VISIBLE);
		} else if (type.equalsIgnoreCase("popup")) {
			if (!popup) {
				Intent mainIntent = new Intent(StageOneLearnActivityOne.this,
						StageOneLearnPopUpActivieyOne.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				StageOneLearnActivityOne.this.startActivity(mainIntent);
				popup = true;

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						learnLayout.setVisibility(View.VISIBLE);
					}
				}, 500);
			}
		} else if (type.equalsIgnoreCase("popup2")) {
			if (!popup2) {
				Intent mainIntent = new Intent(StageOneLearnActivityOne.this,
						StageOneLearnPopUpActivityThree.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				StageOneLearnActivityOne.this.startActivity(mainIntent);
				popup = true;
			}
		} else if (type.equalsIgnoreCase("popup3")) {
			if (!popup3) {
				Intent mainIntent = new Intent(StageOneLearnActivityOne.this,
						StageOneLearnPopUpActivityFour.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				StageOneLearnActivityOne.this.startActivity(mainIntent);
				popup = true;
			}
		} else if (type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneLearnActivityOne.this,
								StageOneLearnActivityOne.class); // TODO °íÄ¡±â
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneLearnActivityOne.this.startActivity(mainIntent);
						StageOneLearnActivityOne.this.finish();
					}
				}, 810);

				Log.i("GMLRME-Stage1IntroType2", "next");
			}
		} else if (type.equalsIgnoreCase("qrquestion")) {
			Intent mainIntent = new Intent(StageOneLearnActivityOne.this,
					QRQuestionActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageOneLearnActivityOne.this.startActivity(mainIntent);
			StageOneLearnActivityOne.this.finish();
		}

		dialogcount++;
		charactercount++;

	}
	
	public void onBackPressed() {
		// mHandler.pause(2000);
		SingletonBGMPlayer.shared(StageOneLearnActivityOne.this, R.raw.study).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneLearnActivityOne.this, R.raw.study).release();
		this.finish();
	}

}
