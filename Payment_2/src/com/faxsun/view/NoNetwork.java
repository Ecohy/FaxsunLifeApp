package com.faxsun.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;

public class NoNetwork extends Activity {
	
	ActivityController ac = new ActivityController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//��ʾ����APP���ͼƬ
		setContentView(R.layout.no_network);
	}

}
