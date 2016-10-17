package com.ys.gmlrme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class StageOneLearnPopUpActivityThree extends Activity implements OnClickListener {

	ImageButton nextBtn;
	RelativeLayout wrongLayout;
	ImageButton confirmBtn;
	PointLinkerView plv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_stageonelearnpopupthree);
		
		nextBtn = (ImageButton) findViewById(R.id.stage1_learn_popup3_next_btn);
		nextBtn.setOnClickListener(this);

		wrongLayout = (RelativeLayout) findViewById(R.id.stage1_learn_popup3_wrong_layout);
		wrongLayout.setVisibility(View.INVISIBLE);
		confirmBtn = (ImageButton) findViewById(R.id.stage1_learn_popup3_wrong_confirm_btn);
		confirmBtn.setOnClickListener(this);
		
		plv = (PointLinkerView) findViewById(R.id.stage1_learn_popup3_pointLinkerView);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == nextBtn.getId()) {
			if(plv.getCount() == 3) {
				Log.e("Popup3", "correct");
				StageOneLearnPopUpActivityThree.this.finish();
			} else {
				wrongLayout.setVisibility(View.VISIBLE);
			}
			
		} else if (arg0.getId() == confirmBtn.getId()) {
			wrongLayout.setVisibility(View.INVISIBLE);
		}

	}
}
