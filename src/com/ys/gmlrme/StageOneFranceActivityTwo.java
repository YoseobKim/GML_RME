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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StageOneFranceActivityTwo extends Activity implements
		OnClickListener {

	ImageButton next_Btn;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	LinearLayout textbubbleMother;
	LinearLayout textbubbleGuide;
	LinearLayout textbubbleFather;
	TextView textFather;
	TextView textGuide;
	TextView textMother;
	int dialogcount = 0;
	int charactercount = 0;

	RelativeLayout guideLayout;
	boolean guideVisible = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stageonefrancetwo);

		next_Btn = (ImageButton) findViewById(R.id.stage1_france_typetwo_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stage1francetypetwo);
		thisLayout.startAnimation(animationFadeIn);

		textbubbleMother = (LinearLayout) findViewById(R.id.stage1_france_textbubble1);
		textbubbleGuide = (LinearLayout) findViewById(R.id.stage1_france2_guide_textbubble1);

		textFather = (TextView) findViewById(R.id.stage1_france_textview2);
		textGuide = (TextView) findViewById(R.id.stage1_france2_guide_textview1);

		textbubbleFather = (LinearLayout) findViewById(R.id.stage1_france_textbubble2);
		textMother = (TextView) findViewById(R.id.stage1_france_textview1);

		guideLayout = (RelativeLayout) findViewById(R.id.stage1_france2_guide_layout);
	}

	@Override
	public void onClick(View arg0) {
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_france_dialog_" + Integer.toString(dialogcount + 1),
				"string", "com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_france_character_"
						+ Integer.toString(charactercount + 1), "string",
				"com.ys.gmlrme");
		type = getString(resourceID);

		if (type.equalsIgnoreCase("mother")) {
			textbubbleMother.setVisibility(View.VISIBLE);
			textbubbleGuide.setVisibility(View.INVISIBLE);
			textbubbleFather.setVisibility(View.INVISIBLE);
			textMother.setText(dialogString);
		} else if (type.equalsIgnoreCase("guide")) {
			if (!guideVisible) {
				guideLayout.setVisibility(View.VISIBLE);
				guideVisible = true;
				
				final MediaPlayer mp = MediaPlayer.create(this, R.raw.plain_ding);
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
			textbubbleMother.setVisibility(View.INVISIBLE);
			textbubbleGuide.setVisibility(View.VISIBLE);
			textbubbleFather.setVisibility(View.INVISIBLE);
			textGuide.setText(dialogString);
		} else if (type.equalsIgnoreCase("father")) {
			textbubbleMother.setVisibility(View.INVISIBLE);
			textbubbleGuide.setVisibility(View.INVISIBLE);
			textbubbleFather.setVisibility(View.VISIBLE);
			textFather.setText(dialogString);
		} else if (type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneFranceActivityTwo.this,
								StageOneFranceActivityThree.class); //TODO ��ġ��
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneFranceActivityTwo.this
								.startActivity(mainIntent);
						StageOneFranceActivityTwo.this.finish();
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
		SingletonBGMPlayer.shared(StageOneFranceActivityTwo.this, R.raw.park).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneFranceActivityTwo.this, R.raw.park).release();
		this.finish();
	}

}
