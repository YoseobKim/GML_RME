package com.ys.gmlrme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StageOneLearnPopUpActivityFour extends Activity implements
		OnTouchListener, OnDragListener, OnClickListener {

	ImageButton nextBtn;

	LinearLayout answer_frame1;

	ImageView image_piece1;
	ImageView image_piece2;
	ImageView image_piece3;
	ImageView image_piece4;
	ImageView image_piece5;
	ImageView image_piece6;

	ImageView image_mirror;

	ImageViewTouchDragEventListener mDragListener = new ImageViewTouchDragEventListener();
	
	View originalImage;
	boolean dropResult = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_stageonelearnpopupfour);

		nextBtn = (ImageButton) findViewById(R.id.stage1_learn_popup4_next_btn);
		nextBtn.setOnClickListener(this);

		answer_frame1 = (LinearLayout) findViewById(R.id.stage1_learn_popup4_answer_layout1);

		image_piece1 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material1);
		image_piece2 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material2);
		image_piece3 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material3);
		image_piece4 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material4);
		image_piece5 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material5);
		image_piece6 = (ImageView) findViewById(R.id.stage1_learn_popup4_image_material6);

		image_mirror = (ImageView) findViewById(R.id.stage1_learn_popup4_mirror_material);

		image_piece1.setOnTouchListener(this);
		image_piece2.setOnTouchListener(this);
		image_piece3.setOnTouchListener(this);
		image_piece4.setOnTouchListener(this);
		image_piece5.setOnTouchListener(this);
		image_piece6.setOnTouchListener(this);

		answer_frame1.setOnDragListener(this);

		findViewById(R.id.stage1_learn_popup4_image_layout1).setOnDragListener(
				this);
		findViewById(R.id.stage1_learn_popup4_image_layout2).setOnDragListener(
				this);
		findViewById(R.id.stage1_learn_popup4_image_layout3).setOnDragListener(
				this);
		findViewById(R.id.stage1_learn_popup4_image_layout4).setOnDragListener(
				this);
		findViewById(R.id.stage1_learn_popup4_image_layout5).setOnDragListener(
				this);
		findViewById(R.id.stage1_learn_popup4_image_layout6).setOnDragListener(
				this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(null, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			originalImage = view;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == nextBtn.getId()) {
			StageOneLearnPopUpActivityFour.this.finish();
		}

	}

	@Override
	public boolean onDrag(View layoutView, DragEvent dragEvent) {
		int action = dragEvent.getAction();

		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			Log.d("GMLRoomEscape-ImageViewTouchDragEventLisener",
					"Drag event started");
			dropResult = false;
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			Log.d("GMLRoomEscape-ImageViewTouchDragEventLisener",
					"Drag event entered into " + layoutView.toString());
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			Log.d("GMLRoomEscape-ImageViewTouchDragEventLisener",
					"Drag event exited from " + layoutView.toString());
			break;
		case DragEvent.ACTION_DROP:
			Log.d("GMLRoomEscape-ImageViewTouchDragEventLisener", "Dropped");

			if (((LinearLayout) layoutView).getChildCount() == 0) {
				View view = (View) dragEvent.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) layoutView;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
				dropResult = true;
			}

			break;
		case DragEvent.ACTION_DRAG_ENDED:
			Log.d("GMLRoomEscape-ImageViewTouchDragEventLisener", "Drag ended");
			
			if(answer_frame1.getChildCount() != 0) {
				if(answer_frame1.getChildAt(0).getId() == image_piece1.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_2_m);
				}
				if(answer_frame1.getChildAt(0).getId() == image_piece2.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_3_m);
				}
				if(answer_frame1.getChildAt(0).getId() == image_piece3.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_4_m);
				}
				if(answer_frame1.getChildAt(0).getId() == image_piece4.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_5_m);
				}
				if(answer_frame1.getChildAt(0).getId() == image_piece5.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_6_m);
				}
				if(answer_frame1.getChildAt(0).getId() == image_piece6.getId()) {
					image_mirror.setImageResource(R.drawable.learn3_1_m);
				}
			} else if(answer_frame1.getChildCount() == 0) {
				image_mirror.setImageResource(R.drawable.blankimage);
			}
			
			if (dropResult == false) {
				originalImage.setVisibility(View.VISIBLE);
			}

			break;
		default:
			break;
		}

		return true;

	}
	
}
