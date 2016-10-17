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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StageOneGreeceActivityTwo extends Activity implements OnClickListener{

	ImageButton next_Btn;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	LinearLayout textbubbleFamily;
	LinearLayout textbubbleGuide;
	LinearLayout textbubbleKid;
	TextView textFather;
	TextView textGuide;
	TextView textKid;
	int dialogcount = 0;
	int charactercount = 0;

	RelativeLayout guideLayout;
	boolean guideVisible = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stageonegreecetwo);

		next_Btn = (ImageButton) findViewById(R.id.stage1_greece_typetwo_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stage1greecetypetwo);
		thisLayout.startAnimation(animationFadeIn);

		textbubbleFamily = (LinearLayout) findViewById(R.id.stage1_greece_textbubble2);
		textbubbleGuide = (LinearLayout) findViewById(R.id.stage1_greece_guide_textbubble1);

		textFather = (TextView) findViewById(R.id.stage1_greece_textview2);
		textGuide = (TextView) findViewById(R.id.stage1_greece_guide_textview1);

		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_greece_textbubble1);
		textKid = (TextView) findViewById(R.id.stage1_greece_textview1);

		guideLayout = (RelativeLayout) findViewById(R.id.stage1_greece_guide_layout);
	}

	@Override
	public void onClick(View arg0) {
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_greece_dialog_" + Integer.toString(dialogcount + 1), "string",
				"com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_greece_character_" + Integer.toString(charactercount + 1), "string",
				"com.ys.gmlrme");
		type = getString(resourceID);
		
		if(type.equalsIgnoreCase("father")) {
			textbubbleFamily.setVisibility(View.VISIBLE);
			textbubbleGuide.setVisibility(View.INVISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			textFather.setText(dialogString);
		} else if(type.equalsIgnoreCase("girl")) {
			if(!guideVisible) {
				guideLayout.setVisibility(View.VISIBLE);
				guideVisible = true;
			}
			textbubbleFamily.setVisibility(View.INVISIBLE);
			textbubbleGuide.setVisibility(View.VISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			textGuide.setText(dialogString);
		} else if(type.equalsIgnoreCase("kid")) {
			textbubbleFamily.setVisibility(View.INVISIBLE);
			textbubbleGuide.setVisibility(View.INVISIBLE);
			textbubbleKid.setVisibility(View.VISIBLE);
			textKid.setText(dialogString);
		} else if(type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);
				
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneGreeceActivityTwo.this,
								StageOneGreeceActivityThree.class);
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneGreeceActivityTwo.this.startActivity(mainIntent);
						StageOneGreeceActivityTwo.this.finish();
					}
				}, 810);
				
				Log.i("GMLRME-Stage1IntroType2", "next");
			}
		}
		
		dialogcount++;
		charactercount++;
		
	}

}
