package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StageOneLearnPopUpActivieyOne extends Activity implements
		OnClickListener {

	ImageButton nextBtn;
	RelativeLayout wrongLayout;
	ImageButton confirmBtn;

	LinearLayout answer_frame1;
	LinearLayout answer_frame2;
	LinearLayout answer_frame3;
	LinearLayout answer_frame4;
	LinearLayout answer_frame5;
	LinearLayout answer_frame6;

	ImageView answer_piece1;
	ImageView answer_piece2;
	ImageView answer_piece3;
	ImageView answer_piece4;
	ImageView answer_piece5;
	ImageView answer_piece6;

	ImageViewTouchDragEventListener mDragListener = new ImageViewTouchDragEventListener();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_stageonelearnpopupone);

		answer_frame1 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout1);
		answer_frame2 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout2);
		answer_frame3 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout3);
		answer_frame4 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout4);
		answer_frame5 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout5);
		answer_frame6 = (LinearLayout) findViewById(R.id.stage1_learn_popup1_blank_layout6);

		answer_piece1 = (ImageView) findViewById(R.id.stage1_learn_popup1_image1);
		answer_piece2 = (ImageView) findViewById(R.id.stage1_learn_popup1_image2);
		answer_piece3 = (ImageView) findViewById(R.id.stage1_learn_popup1_image3);
		answer_piece4 = (ImageView) findViewById(R.id.stage1_learn_popup1_image4);
		answer_piece5 = (ImageView) findViewById(R.id.stage1_learn_popup1_image5);
		answer_piece6 = (ImageView) findViewById(R.id.stage1_learn_popup1_image6);

		answer_frame1.setOnDragListener(mDragListener);
		answer_frame2.setOnDragListener(mDragListener);
		answer_frame3.setOnDragListener(mDragListener);
		answer_frame4.setOnDragListener(mDragListener);
		answer_frame5.setOnDragListener(mDragListener);
		answer_frame6.setOnDragListener(mDragListener);

		answer_piece1.setOnTouchListener(mDragListener);
		answer_piece2.setOnTouchListener(mDragListener);
		answer_piece3.setOnTouchListener(mDragListener);
		answer_piece4.setOnTouchListener(mDragListener);
		answer_piece5.setOnTouchListener(mDragListener);
		answer_piece6.setOnTouchListener(mDragListener);

		findViewById(R.id.stage1_learn_popup1_image_layout1).setOnDragListener(
				mDragListener);
		findViewById(R.id.stage1_learn_popup1_image_layout2).setOnDragListener(
				mDragListener);
		findViewById(R.id.stage1_learn_popup1_image_layout3).setOnDragListener(
				mDragListener);
		findViewById(R.id.stage1_learn_popup1_image_layout4).setOnDragListener(
				mDragListener);
		findViewById(R.id.stage1_learn_popup1_image_layout5).setOnDragListener(
				mDragListener);
		findViewById(R.id.stage1_learn_popup1_image_layout6).setOnDragListener(
				mDragListener);

		nextBtn = (ImageButton) findViewById(R.id.stage1_learn_popup1_next_btn);
		nextBtn.setOnClickListener(this);

		wrongLayout = (RelativeLayout) findViewById(R.id.stage1_learn_popup1_wrong_layout);
		wrongLayout.setVisibility(View.INVISIBLE);
		confirmBtn = (ImageButton) findViewById(R.id.stage1_learn_popup1_wrong_confirm_btn);
		confirmBtn.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == nextBtn.getId()) {
			if (answer_check()) {
				Log.i("GMLRME", "correct");
				Intent mainIntent = new Intent(
						StageOneLearnPopUpActivieyOne.this,
						StageOneLearnPopUpActivityTwo.class);
				mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				StageOneLearnPopUpActivieyOne.this.startActivity(mainIntent);
				StageOneLearnPopUpActivieyOne.this.finish();
			} else {
				wrongLayout.setVisibility(View.VISIBLE);
			}
		} else if (arg0.getId() == confirmBtn.getId()) {
			wrongLayout.setVisibility(View.INVISIBLE);
		}

	}

	private boolean answer_check() {
		int count = 0;
		boolean frame1 = false;
		boolean frame2 = false;
		boolean frame3 = false;
		boolean frame4 = false;
		boolean frame5 = false;
		boolean frame6 = false;

		int child1 = 0;
		int child2 = 0;
		int child3 = 0;
		int child4 = 0;
		int child5 = 0;
		int child6 = 0;

		int correctCount = 0;

		if (answer_frame1.getChildCount() != 0) {
			count++;
			frame1 = true;
			child1 = answer_frame1.getChildAt(0).getId();
		}
		if (answer_frame2.getChildCount() != 0) {
			count++;
			frame2 = true;
			child2 = answer_frame2.getChildAt(0).getId();
		}
		if (answer_frame3.getChildCount() != 0) {
			count++;
			frame3 = true;
			child3 = answer_frame3.getChildAt(0).getId();
		}
		if (answer_frame4.getChildCount() != 0) {
			count++;
			frame4 = true;
			child4 = answer_frame4.getChildAt(0).getId();
		}
		if (answer_frame5.getChildCount() != 0) {
			count++;
			frame5 = true;
			child5 = answer_frame5.getChildAt(0).getId();
		}
		if (answer_frame6.getChildCount() != 0) {
			count++;
			frame6 = true;
			child6 = answer_frame6.getChildAt(0).getId();
		}

		if (count == 3) {
			if (frame1) {
				if (child1 == answer_piece1.getId()) {
					correctCount++;
				} else if (child1 == answer_piece2.getId()) {
					correctCount++;
				} else if (child1 == answer_piece3.getId()) {
					correctCount++;
				}
			}
			if (frame2) {
				if (child2 == answer_piece1.getId()) {
					correctCount++;
				} else if (child2 == answer_piece2.getId()) {
					correctCount++;
				} else if (child2 == answer_piece3.getId()) {
					correctCount++;
				}
			}
			if (frame3) {
				if (child3 == answer_piece1.getId()) {
					correctCount++;
				} else if (child3 == answer_piece2.getId()) {
					correctCount++;
				} else if (child3 == answer_piece3.getId()) {
					correctCount++;
				}
			}
			if(frame4) {
				if(child4 == answer_piece1.getId()) {
					correctCount++;
				} else if(child4 == answer_piece2.getId()) {
					correctCount++;
				} else if(child4 == answer_piece3.getId()) {
					correctCount++;
				}
			}
			if(frame5) {
				if(child5 == answer_piece1.getId()) {
					correctCount++;
				} else if(child5 == answer_piece2.getId()) {
					correctCount++;
				} else if(child5 == answer_piece3.getId()) {
					correctCount++;
				}
			}
			if(frame6) {
				if(child6 == answer_piece1.getId()) {
					correctCount++;
				} else if(child6 == answer_piece2.getId()) {
					correctCount++;
				} else if(child6 == answer_piece3.getId()) {
					correctCount++;
				}
			}
		}
		if(correctCount == 3) {
			return true;
		}
		
		return false;
	}

}
