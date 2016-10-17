package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

public class StageOneGreeceActivityThree extends Activity implements
		OnClickListener {

	ImageButton next_Btn;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	LinearLayout textbubbleFamily;
	LinearLayout textbubbleGuide;
	LinearLayout textbubbleKid;
	TextView textFamily;
	TextView textGuide;
	TextView textKid;
	int dialogcount = 0;
	int charactercount = 0;

	RelativeLayout guideLayout;
	boolean guideVisible = false;
	int bgcount = 0;

	ImageView bg2;
	
	RelativeLayout kidandfather;
	ImageView greecemainchar;
	LinearLayout textbubbleKidandFatherKid;
	LinearLayout textbubbleKidandFatherFather;
	TextView textKidandFatherKid;
	TextView textKidandFatherFather;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_stageonegreecethree);

		next_Btn = (ImageButton) findViewById(R.id.stage1_greece_typetwo_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stage1greecetypethree);
		thisLayout.startAnimation(animationFadeIn);

		textbubbleFamily = (LinearLayout) findViewById(R.id.stage1_greece_textbubble1);
		textbubbleGuide = (LinearLayout) findViewById(R.id.stage1_greece_guide_textbubble1);

		textFamily = (TextView) findViewById(R.id.stage1_greece_textview1);
		textGuide = (TextView) findViewById(R.id.stage1_greece_guide_textview1);

		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_greece_textbubble2);
		textKid = (TextView) findViewById(R.id.stage1_greece_textview2);

		guideLayout = (RelativeLayout) findViewById(R.id.stage1_greece_guide_layout);

		bg2 = (ImageView) findViewById(R.id.stage1_greece_typeotwo_bg2);
		
		kidandfather = (RelativeLayout) findViewById(R.id.stage1_greece_kidandfather_layout);
		greecemainchar = (ImageView) findViewById(R.id.stage1_greece2_mainChar_1);
		textbubbleKidandFatherKid = (LinearLayout) findViewById(R.id.stage1_greece_kidandfather_textbubble1);
		textKidandFatherKid = (TextView) findViewById(R.id.stage1_greece_kidandfather_textview1);
		textbubbleKidandFatherFather = (LinearLayout) findViewById(R.id.stage1_greece_kidandfather_textbubble2);
		textKidandFatherFather = (TextView) findViewById(R.id.stage1_greece_kidandfather_textview2);
	}

	@Override
	public void onClick(View arg0) {
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_greece2_dialog_" + Integer.toString(dialogcount + 1), "string",
				"com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_greece2_character_" + Integer.toString(charactercount + 1), "string",
				"com.ys.gmlrme");
		type = getString(resourceID);
		
		if(type.equalsIgnoreCase("family")) {
			textbubbleFamily.setVisibility(View.VISIBLE);
			textbubbleGuide.setVisibility(View.INVISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			textFamily.setText(dialogString);
		} else if(type.equalsIgnoreCase("guide")) {
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
		} else if(type.equalsIgnoreCase("bgchange")) {
			if(bgcount == 0) {
				bgcount++;
				bg2.setVisibility(View.VISIBLE);
				textbubbleFamily.setVisibility(View.INVISIBLE);
				textbubbleGuide.setVisibility(View.INVISIBLE);
				textbubbleKid.setVisibility(View.INVISIBLE);
			} else {
				Log.e("GMLRME-stageoneindiatypetwo", "Error. Unvalid bgcount");
			}
		} else if(type.equalsIgnoreCase("arview")) {
			Toast.makeText(StageOneGreeceActivityThree.this, "ARSTART", Toast.LENGTH_SHORT).show();
			kidandfather.setVisibility(View.VISIBLE);
			guideLayout.setVisibility(View.INVISIBLE);
			textbubbleFamily.setVisibility(View.INVISIBLE);
			textbubbleGuide.setVisibility(View.VISIBLE);
			textbubbleKid.setVisibility(View.INVISIBLE);
			greecemainchar.setVisibility(View.INVISIBLE);
			
			Intent i = new Intent();
			PackageManager manager = getPackageManager();
			i = manager.getLaunchIntentForPackage("com.gmlrme.artest");
			i.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(i);
			
		} else if(type.equalsIgnoreCase("kidandfather_kid")){
			textbubbleKidandFatherKid.setVisibility(View.VISIBLE);
			textbubbleKidandFatherFather.setVisibility(View.INVISIBLE);
			textKidandFatherKid.setText(dialogString);
		} else if(type.equalsIgnoreCase("kidandfather_father")){
			textbubbleKidandFatherKid.setVisibility(View.INVISIBLE);
			textbubbleKidandFatherFather.setVisibility(View.VISIBLE);
			textKidandFatherFather.setText(dialogString);
		}
		else if(type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);
				
				SingletonBGMPlayer.shared(StageOneGreeceActivityThree.this, R.raw.greece).fadeVolume();
				//SingletonBGMPlayer.shared(StageOneGreeceActivityThree.this, R.raw.greece).getGetMediaPlayer().stop();
				//SingletonBGMPlayer.shared(StageOneGreeceActivityThree.this, R.raw.greece).release();
				
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneGreeceActivityThree.this,
								StageOneFranceActivityOne.class); // TODO °íÄ¡±â
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneGreeceActivityThree.this.startActivity(mainIntent);
						StageOneGreeceActivityThree.this.finish();
					}
				}, 810);
				
				Log.i("GMLRME-Stage1IntroType2", "next");
			}
		}
		
		dialogcount++;
		charactercount++;

	}

}
