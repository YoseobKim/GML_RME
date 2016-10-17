package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ys.gmlrme.R;

public class LogoScreenActivity extends Activity{
	private final int SPLASH_DISPLAY_LENGTH = 3000;
	Animation animationFadeInAndFadeOut;
	ImageView logoImage;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logoscreen);
		animationFadeInAndFadeOut = AnimationUtils.loadAnimation(this,
				R.animator.alpha);
		logoImage = (ImageView) findViewById(R.id.logoImage);
		logoImage.setImageResource(R.drawable.logoscreen);
		logoImage.startAnimation(animationFadeInAndFadeOut);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent mainIntent = new Intent(LogoScreenActivity.this,
						StartScreenActivity.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				LogoScreenActivity.this.startActivity(mainIntent);
				LogoScreenActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);

	}
}
