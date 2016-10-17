package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class StageSelectByPassActivity extends Activity implements
		OnClickListener {

	ImageButton btnIntro;
	ImageButton btnIndia;
	ImageButton btnGreece;
	ImageButton btnFrance;
	ImageButton btnLearn;
	ImageButton btnQR;
	ImageButton btnEnd;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_stageselectbypass);

		btnIntro = (ImageButton) findViewById(R.id.stage_select_bypass_intro);
		btnIndia = (ImageButton) findViewById(R.id.stage_select_bypass_india);
		btnGreece = (ImageButton) findViewById(R.id.stage_select_bypass_greece);
		btnFrance = (ImageButton) findViewById(R.id.stage_select_bypass_france);
		btnLearn = (ImageButton) findViewById(R.id.stage_select_bypass_learn);
		btnQR = (ImageButton) findViewById(R.id.stage_select_bypass_qrquestion);
		btnEnd = (ImageButton) findViewById(R.id.stage_select_bypass_end);

		btnIntro.setOnClickListener(this);
		btnIndia.setOnClickListener(this);
		btnGreece.setOnClickListener(this);
		btnFrance.setOnClickListener(this);
		btnLearn.setOnClickListener(this);
		btnQR.setOnClickListener(this);
		btnEnd.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		SingletonBGMPlayer.shared(StageSelectByPassActivity.this, R.raw.start2).getGetMediaPlayer().stop();
		SingletonBGMPlayer.shared(StageSelectByPassActivity.this, R.raw.start2).release();
		
		if (arg0.getId() == btnIntro.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneIntroStoryActivityTypeOne.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();
		} else if (arg0.getId() == btnIndia.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneIndiaActivityTypeOne.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();

		} else if (arg0.getId() == btnGreece.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneGreeceActivityOne.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();

		} else if (arg0.getId() == btnFrance.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneFranceActivityOne.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();
			
		} else if (arg0.getId() == btnLearn.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneLearnActivityOne.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();
			
		} else if (arg0.getId() == btnQR.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					QRQuestionActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();
			
		} else if (arg0.getId() == btnEnd.getId()) {
			Intent mainIntent = new Intent(StageSelectByPassActivity.this,
					StageOneEndActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			StageSelectByPassActivity.this.startActivity(mainIntent);
			StageSelectByPassActivity.this.finish();
		}

	}

}
