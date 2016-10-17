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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class StageSelectScreenActivity extends Activity implements
		OnClickListener {

	ImageButton stage1_Btn;
	ImageButton stage2_Btn;
	ImageButton stagedummy;
	Animation animationFadeOut;
	Animation animationFadeIn;
	RelativeLayout thisLayout;
	boolean startGame = false;

	int count = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stageselectscreen);

		stage1_Btn = (ImageButton) findViewById(R.id.stage1_Btn);
		stage2_Btn = (ImageButton) findViewById(R.id.stage2_Btn);
		stagedummy = (ImageButton) findViewById(R.id.stage_lock_Btn);

		stage1_Btn.setOnClickListener(this);
		stage2_Btn.setOnClickListener(this);
		stagedummy.setOnClickListener(this);
		animationFadeIn = AnimationUtils.loadAnimation(this,
				R.animator.fadeinshort);
		animationFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.fadeoutshort);

		thisLayout = (RelativeLayout) findViewById(R.id.stageselectscreenLayout);
		thisLayout.startAnimation(animationFadeIn);

	}

	public void onBackPressed() {
		// mHandler.pause(2000);
		SingletonBGMPlayer.shared(StageSelectScreenActivity.this, R.raw.start2).getGetMediaPlayer().stop();
		this.finish();
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == stage1_Btn.getId()) {
			if (!startGame) {
				Log.i("GMLRoomEscape_StartScreen", "GameStart");
				thisLayout.startAnimation(animationFadeOut);
				startGame = true;

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								StageSelectScreenActivity.this,
								StageOneIntroStoryActivityTypeOne.class);
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						StageSelectScreenActivity.this
								.startActivity(mainIntent);
						StageSelectScreenActivity.this.finish();
					}
				}, 810);

			}

		} else if (arg0.getId() == stage2_Btn.getId()) {
			Toast.makeText(StageSelectScreenActivity.this, "stage2 Pressed",
					Toast.LENGTH_SHORT).show();
		} else if (arg0.getId() == stagedummy.getId()) {
			count++;
			if(count >= 5) {
				count = 0;
				Intent mainIntent = new Intent(
						StageSelectScreenActivity.this,
						StageSelectByPassActivity.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				StageSelectScreenActivity.this
						.startActivity(mainIntent);
			}
		}

	}
}
