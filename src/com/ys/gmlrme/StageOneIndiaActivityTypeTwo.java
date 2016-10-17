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

public class StageOneIndiaActivityTypeTwo extends Activity implements OnClickListener {

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
	ImageView bg3;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_stageoneindiatypetwo);
	    
		next_Btn = (ImageButton) findViewById(R.id.stage1_india_typetwo_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageoneindiatypetwo);
		thisLayout.startAnimation(animationFadeIn);
	    
		textbubbleFamily = (LinearLayout) findViewById(R.id.stage1_india_textbubble1);
		textbubbleGuide = (LinearLayout) findViewById(R.id.stage1_india_guide_textbubble1);
		
		textFamily = (TextView) findViewById(R.id.stage1_india_textview1);
		textGuide = (TextView) findViewById(R.id.stage1_india_guide_textview1);
		
		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_india_textbubble2);
		textKid = (TextView) findViewById(R.id.stage1_india_textview2);
		
		guideLayout = (RelativeLayout) findViewById(R.id.stage1_india_guide_layout);
		
		bg2 = (ImageView) findViewById(R.id.stage1_india_typeotwo_bg2);
		bg3 = (ImageView) findViewById(R.id.stage1_india_typeotwo_bg3);
	}

	@Override
	public void onClick(View arg0) {
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_india_dialog_" + Integer.toString(dialogcount + 1), "string",
				"com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_india_characte_" + Integer.toString(charactercount + 1), "string",
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
			} else if(bgcount == 1) {
				bgcount++;
				bg3.setVisibility(View.VISIBLE);
				textbubbleFamily.setVisibility(View.INVISIBLE);
				textbubbleGuide.setVisibility(View.INVISIBLE);
				textbubbleKid.setVisibility(View.INVISIBLE);
			} else {
				Log.e("GMLRME-stageoneindiatypetwo", "Error. Unvalid bgcount");
			}
		} else if(type.equalsIgnoreCase("arview")) {
			Toast.makeText(StageOneIndiaActivityTypeTwo.this, "ARSTART", Toast.LENGTH_SHORT).show();
			Intent i = new Intent();
			PackageManager manager = getPackageManager();
			i = manager.getLaunchIntentForPackage("com.gmlrme.artest");
			i.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(i);
		} else if(type.equalsIgnoreCase("next")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);
				
				SingletonBGMPlayer.shared(StageOneIndiaActivityTypeTwo.this, R.raw.india).fadeVolume();
				//SingletonBGMPlayer.shared(StageOneIndiaActivityTypeTwo.this, R.raw.india).getGetMediaPlayer().stop();
				//SingletonBGMPlayer.shared(StageOneIndiaActivityTypeTwo.this, R.raw.india).release();
				
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneIndiaActivityTypeTwo.this,
								StageOneGreeceActivityOne.class);
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneIndiaActivityTypeTwo.this.startActivity(mainIntent);
						StageOneIndiaActivityTypeTwo.this.finish();
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
		SingletonBGMPlayer.shared(StageOneIndiaActivityTypeTwo.this, R.raw.india).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneIndiaActivityTypeTwo.this, R.raw.india).release();
		this.finish();
	}

}
