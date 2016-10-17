package com.ys.gmlrme;

import android.util.Log;
import android.view.*;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class ImageViewTouchDragEventListener implements OnTouchListener,
		OnDragListener {

	View originalImage;
	boolean dropResult = false;

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