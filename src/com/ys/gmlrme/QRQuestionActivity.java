package com.ys.gmlrme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QRQuestionActivity extends Activity implements OnClickListener {

	int count = 0;
	TextView description;
	boolean q1 = false;
	boolean q2 = false;
	boolean q3 = false;
	boolean q4 = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qrquestion);

		RelativeLayout thisLayout = (RelativeLayout) findViewById(R.id.qrquestionLayout);
		thisLayout.setOnClickListener(this);

		description = (TextView) findViewById(R.id.description_text);
	}

	@Override
	public void onClick(View arg0) {
		/*if(count == 3) {
			Intent mainIntent = new Intent(QRQuestionActivity.this,
					StageOneEndActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			QRQuestionActivity.this.startActivity(mainIntent);
			QRQuestionActivity.this.finish();
		}*/
		
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		intent.putExtra("SAVE_HISTORY", false);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = data.getStringExtra("SCAN_RESULT");
				Log.e("ZxingTest", contents);
				if (contents.equals("stage1")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (!(count >= 4)) {
						Intent mainIntent = new Intent(QRQuestionActivity.this,
								QRRightActivity.class); // TODO 고치기
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						QRQuestionActivity.this.startActivity(mainIntent);
						// QRQuestionActivity.this.finish();
						count++;
						description.setText("잘했어요. 이제" + (4 - count)
								+ "개 더 찾아보세요.");
						if(count == 4) {
							description.setText("잘했어요. 모두 찾았네요. 수학을 정말 잘하시네요~아직 문제를 못 푼 친구들도 있을지 모르니 조금만 기다려주세요.");
							Intent endIntent = new Intent(QRQuestionActivity.this,
									StageOneEndActivity.class);
							endIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							QRQuestionActivity.this.startActivity(endIntent);
							QRQuestionActivity.this.finish();
						}
						Log.i("GMLRME-QRQuetion", contents);
					} else {
						QRQuestionActivity.this.finish();
					}

				}

				if (contents.equals("stage2")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!(count >= 4)) {
						Intent mainIntent = new Intent(QRQuestionActivity.this,
								QRRightActivity.class); // TODO 고치기
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						QRQuestionActivity.this.startActivity(mainIntent);
						// QRQuestionActivity.this.finish();
						count++;
						description.setText("잘했어요. 이제" + (4 - count)
								+ "개 더 찾아보세요.");
						if(count == 4) {
							Intent endIntent = new Intent(QRQuestionActivity.this,
									StageOneEndActivity.class);
							endIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							QRQuestionActivity.this.startActivity(endIntent);
							QRQuestionActivity.this.finish();
							description.setText("잘했어요. 모두 찾았네요. 수학을 정말 잘하시네요~");
						}
						Log.i("GMLRME-QRQuetion", contents);
					} else {
						QRQuestionActivity.this.finish();
					}
				}

				if (contents.equals("stage3")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!(count >= 4)) {
						Intent mainIntent = new Intent(QRQuestionActivity.this,
								QRRightActivity.class); // TODO 고치기
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						QRQuestionActivity.this.startActivity(mainIntent);
						// QRQuestionActivity.this.finish();
						count++;
						description.setText("잘했어요. 이제" + (4 - count)
								+ "개 더 찾아보세요.");
						if(count == 4) {
							Intent endIntent = new Intent(QRQuestionActivity.this,
									StageOneEndActivity.class);
							endIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							QRQuestionActivity.this.startActivity(endIntent);
							QRQuestionActivity.this.finish();
							description.setText("잘했어요. 모두 찾았네요. 수학을 정말 잘하시네요~");
						}
						Log.i("GMLRME-QRQuetion", contents);
					} else {
						QRQuestionActivity.this.finish();
					}
				}

				if (contents.equals("stage4")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!(count >= 4)) {
						Intent mainIntent = new Intent(QRQuestionActivity.this,
								QRRightActivity.class); // TODO 고치기
						mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						QRQuestionActivity.this.startActivity(mainIntent);
						// QRQuestionActivity.this.finish();
						count++;
						description.setText("잘했어요. 이제" + (4 - count)
								+ "개 더 찾아보세요.");
						if(count == 4) {
							Intent endIntent = new Intent(QRQuestionActivity.this,
									StageOneEndActivity.class);
							endIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							QRQuestionActivity.this.startActivity(endIntent);
							QRQuestionActivity.this.finish();
							description.setText("잘했어요. 모두 찾았네요. 수학을 정말 잘하시네요~");
						}
						Log.i("GMLRME-QRQuetion", contents);
					} else {
						QRQuestionActivity.this.finish();
					}
				}

				if (contents.equals("stage5")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent mainIntent = new Intent(QRQuestionActivity.this,
							QRWrongActivity.class); // TODO 고치기
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					QRQuestionActivity.this.startActivity(mainIntent);
					// QRQuestionActivity.this.finish();
					// count++;
					description.setText("틀렸네요. 다시 한번 더 시도해 보세요. 이제"
							+ (4 - count) + "개 더 찾아보세요.");
					Log.i("GMLRME-QRQuetion", contents);
				}

				if (contents.equals("stage6")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent mainIntent = new Intent(QRQuestionActivity.this,
							QRWrongActivity.class); // TODO 고치기
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					QRQuestionActivity.this.startActivity(mainIntent);
					// QRQuestionActivity.this.finish();
					// count++;
					description.setText("틀렸네요. 다시 한번 더 시도해 보세요. 이제"
							+ (4 - count) + "개 더 찾아보세요.");
					Log.i("GMLRME-QRQuetion", contents);
				}

				if (contents.equals("stage7")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent mainIntent = new Intent(QRQuestionActivity.this,
							QRWrongActivity.class); // TODO 고치기
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					QRQuestionActivity.this.startActivity(mainIntent);
					// QRQuestionActivity.this.finish();
					// count++;
					description.setText("틀렸네요. 다시 한번 더 시도해 보세요. 이제"
							+ (4 - count) + "개 더 찾아보세요.");
					Log.i("GMLRME-QRQuetion", contents);
				}

				if (contents.equals("stage8")) {
					try {

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent mainIntent = new Intent(QRQuestionActivity.this,
							QRWrongActivity.class); // TODO 고치기
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					QRQuestionActivity.this.startActivity(mainIntent);
					// QRQuestionActivity.this.finish();
					// count++;
					description.setText("틀렸네요. 다시 한번 더 시도해 보세요. 이제"
							+ (4 - count) + "개 더 찾아보세요.");
					Log.i("GMLRME-QRQuetion", contents);
				}
			}
		} else if (resultCode == RESULT_CANCELED) {
			// Handle cancel
		}
	}

	@Override
	public void onBackPressed() {
		return;
	}

}
