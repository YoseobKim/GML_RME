package com.ys.gmlrme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class QRRightActivity extends Activity implements OnClickListener{

	ImageButton qrRightButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		setContentView(R.layout.activity_qrright);
		
		qrRightButton = (ImageButton) findViewById(R.id.stage1_qr_right_confirm_btn);
		qrRightButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		/*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		intent.putExtra("SAVE_HISTORY", false);
		startActivityForResult(intent, 0);*/
		QRRightActivity.this.finish();
	}

}
