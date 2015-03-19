package com.faxsun.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.payment_2.R;

public class CustomerInfo extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//自定义标题显示
		setContentView(R.layout.customer_info);		
		
	}
}
