package com.ys.gmlrme;

import java.io.IOException;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class StageOneIntroStoryActivityTypeRullet extends Activity implements
		OnClickListener {
	ImageView go_Btn;
	ImageView rulletPad;
	ImageButton nextBtn;
	RelativeLayout winlayout;
	Animation animationRotate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_stageoneintrostortyyperullet);

		go_Btn = (ImageView) findViewById(R.id.stage1_1_event_popup_rullet_button);
		go_Btn.setOnClickListener(this);

		animationRotate = AnimationUtils.loadAnimation(this, R.animator.rotate);
		rulletPad = (ImageView) findViewById(R.id.stage1_1_event_popup_rullet_pad);

		winlayout = (RelativeLayout) findViewById(R.id.stage1_1_event_popup_rullet_win_layout);

		nextBtn = (ImageButton) findViewById(R.id.stage1_1_event_popup_rullet_win_next_btn);
		nextBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == go_Btn.getId()) {
			// TODO Auto-generated method stub
			animationRotate.setFillAfter(true);
			rulletPad.setImageResource(R.drawable.stage1_rullet_pad);
			rulletPad.startAnimation(animationRotate);
			
			final MediaPlayer mp = MediaPlayer.create(this, R.raw.rullet);
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

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					winlayout.setVisibility(View.VISIBLE);
				}
			}, 3500);
		} else if (arg0.getId() == nextBtn.getId()) {
			this.finish();
		}

	}
	
	public void onBackPressed() {
		return;
	}

}
