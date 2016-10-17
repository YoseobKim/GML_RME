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

public class StageOneEndActivity extends Activity implements OnClickListener {

	ImageButton next_Btn;
	ImageView stage1_end_mainChar_1;

	int dialogcount = 0;
	int charactercount = 0;
	LinearLayout textbubbleKid;
	TextView textKid;

	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean next = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_stageoneend);

		next_Btn = (ImageButton) findViewById(R.id.stage1_end_next_btn);
		next_Btn.setOnClickListener(this);

		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageoneend);
		thisLayout.startAnimation(animationFadeIn);

		textbubbleKid = (LinearLayout) findViewById(R.id.stage1_end_textbubble1);
		textKid = (TextView) findViewById(R.id.stage1_end_textview1);

	}

	@Override
	public void onClick(View arg0) {
		String dialogString;
		String type;

		int resourceID = getResources().getIdentifier(
				"stage1_end_dialog_" + Integer.toString(dialogcount + 1),
				"string", "com.ys.gmlrme");
		dialogString = getString(resourceID);
		Log.i("GMLRME-Stage1IntroType2", "" + dialogString);

		resourceID = getResources().getIdentifier(
				"stage1_end_character_" + Integer.toString(charactercount + 1),
				"string", "com.ys.gmlrme");
		type = getString(resourceID);

		if (type.equalsIgnoreCase("kid")) {
			textbubbleKid.setVisibility(View.VISIBLE);
			textKid.setText(dialogString);
		} else if (type.equalsIgnoreCase("end")) {
			if (!next) {
				thisLayout.startAnimation(animationFadeOut);
				next = true;
				next_Btn.setEnabled(false);

				// SingletonBGMPlayer.shared(StageOneEndActivity.this,
				// R.raw.study).getGetMediaPlayer().stop();
				// SingletonBGMPlayer.shared(StageOneEndActivity.this,
				// R.raw.study).release();
				SingletonBGMPlayer
						.shared(StageOneEndActivity.this, R.raw.study)
						.fadeVolume();

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageOneEndActivity.this,
								StageSelectScreenActivity.class);
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageOneEndActivity.this.startActivity(mainIntent);
						StageOneEndActivity.this.finish();
						SingletonBGMPlayer player = SingletonBGMPlayer.shared(
								StageOneEndActivity.this, R.raw.start2);
						try {
							Log.i("RoomEscape-StartScreen", "MediaPlayer start");
							player.getGetMediaPlayer().start();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		SingletonBGMPlayer.shared(StageOneEndActivity.this, R.raw.study)
				.getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageOneEndActivity.this, R.raw.study)
				.release();
		this.finish();
	}

}
