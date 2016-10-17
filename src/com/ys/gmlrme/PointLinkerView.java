package com.ys.gmlrme;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class PointLinkerView extends View {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;
	private static final int TOUCH_TOLERANCE_DP = 50;
	private static final int BACKGROUND = 0x07000000;
	private List<Point> mPoints = new ArrayList<Point>();
	private int mLastPointIndex = 0;
	private int mTouchTolerance;
	private boolean isPathStarted = false;

	private int count = 0;

	public int getCount() {
		return count;
	}

	public PointLinkerView(Context context) {
		super(context);
		mCanvas = new Canvas();
		mPath = new Path();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(8);
		mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);

		Point point1 = new Point(5, 50);
		Point point2 = new Point(310, 5);
		Point point3 = new Point(395, 445);

		Point point1_1 = new Point(310, 5);
		Point point2_1 = new Point(395, 445);
		Point point3_1 = new Point(5, 445);

		mPoints.add(point1);
		mPoints.add(point1_1);
		mPoints.add(point2);
		mPoints.add(point2_1);
		mPoints.add(point3);
		mPoints.add(point3_1);
	}

	public PointLinkerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCanvas = new Canvas();
		mPath = new Path();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(8);
		mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);

		Point point1 = new Point(5, 50);
		Point point2 = new Point(310, 5);
		Point point3 = new Point(395, 445);

		Point point1_1 = new Point(310, 5);
		Point point2_1 = new Point(395, 445);
		Point point3_1 = new Point(5, 445);

		mPoints.add(point1);
		mPoints.add(point1_1);
		mPoints.add(point2);
		mPoints.add(point2_1);
		mPoints.add(point3);
		mPoints.add(point3_1);
	}

	public PointLinkerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mCanvas = new Canvas();
		mPath = new Path();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(8);
		mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldWidth,
			int oldHeight) {
		super.onSizeChanged(width, height, oldWidth, oldHeight);
		clear();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(BACKGROUND);
		canvas.drawBitmap(mBitmap, 0, 0, null);
		canvas.drawPath(mPath, mPaint);
		int loopCount = 0;
		// TODO remove if you dont want points to be drawn
		for (Point point : mPoints) {
			if (count == 0 && loopCount == 0)
				mPaint.setColor(Color.RED);
			else if (count == 1 && loopCount == 2)
				mPaint.setColor(Color.RED);
			else if (count == 2 && loopCount == 4)
				mPaint.setColor(Color.RED);
			else if (count == 3 && loopCount == 6)
				mPaint.setColor(Color.RED);
			else if (count == 4 && loopCount == 8)
				mPaint.setColor(Color.RED);
			canvas.drawPoint(point.x, point.y, mPaint);
			mPaint.setColor(Color.BLACK);
			loopCount++;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up(x, y);
			invalidate();
			break;
		}
		return true;
	}

	private void touch_start(float x, float y) {

		if (checkPoint(x, y, mLastPointIndex)) {
			mPath.reset();
			// user starts from given point so path can beis started
			isPathStarted = true;
		} else {
			// user starts move from point which doen's belongs to mPinst list
			isPathStarted = false;
		}

	}

	// ADDED WITH LAST EDIT
	private void touch_move(float x, float y) {
		// draw line with finger move
		if (isPathStarted) {
			mPath.reset();
			Point p = mPoints.get(mLastPointIndex);
			mPath.moveTo(p.x, p.y);
			mPath.lineTo(x, y);
		}
	}

	/**
	 * Draws line.
	 */
	private void touch_up(float x, float y) {
		mPath.reset();
		if (checkPoint(x, y, mLastPointIndex + 1) && isPathStarted) {
			// move finished at valid point so draw whole line

			// start point
			Point p = mPoints.get(mLastPointIndex);
			mPath.moveTo(p.x, p.y);
			// end point
			p = mPoints.get(mLastPointIndex + 1);
			mPath.lineTo(p.x, p.y);
			mCanvas.drawPath(mPath, mPaint);
			mPath.reset();
			// increment point index
			mLastPointIndex += 2;
			count++;
			isPathStarted = false;
			Log.e("PointLinker","count: " + count);
		}

	}

	/**
	 * Sets paint
	 * 
	 * @param paint
	 */
	public void setPaint(Paint paint) {
		this.mPaint = paint;
	}

	/**
	 * Returns image as bitmap
	 * 
	 * @return
	 */
	public Bitmap getBitmap() {
		return mBitmap;
	}

	/**
	 * Clears canvas
	 */
	public void clear() {
		mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Bitmap.Config.ARGB_8888);
		mBitmap.eraseColor(BACKGROUND);
		mCanvas.setBitmap(mBitmap);
		invalidate();
	}

	/**
	 * Checks if user touch point with some tolerance
	 */
	private boolean checkPoint(float x, float y, int pointIndex) {
		if (pointIndex >= mPoints.size()) {
			// out of bounds
			return false;
		}
		Point point = mPoints.get(pointIndex);
		// EDIT changed point.y to poin.x in the first if statement
		if (x > (point.x - mTouchTolerance) && x < (point.x + mTouchTolerance)) {
			if (y > (point.y - mTouchTolerance)
					&& y < (point.y + mTouchTolerance)) {
				return true;
			}
		}
		return false;
	}

	public List<Point> getPoints() {
		return mPoints;
	}

	public void setPoints(List<Point> points) {
		this.mPoints = points;
	}

	private int dp2px(int dp) {
		Resources r = getContext().getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				r.getDisplayMetrics());
		return (int) px;
	}
}