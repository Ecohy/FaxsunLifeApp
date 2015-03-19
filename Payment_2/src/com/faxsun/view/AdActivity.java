package com.faxsun.view;

import java.util.Timer;
import java.util.TimerTask;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;
import com.faxsun.moudle.Category.catogory_id_type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AdActivity extends Activity {
	
	public static String TAG = "AdActivity";
	
	ActivityController ac = new ActivityController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate"); 
		
		//显示进入APP广告图片
		setContentView(R.layout.ad_activity);
		Log.i(TAG, "onCreate===1");
		//1秒后跳转
		final Intent intent = new Intent(AdActivity.this, FaxsunHome.class);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub	
				startActivity(intent);
				AdActivity.this.finish();
			}			
		};
		timer.schedule(timerTask,1000*1);
		
	}
		
}
