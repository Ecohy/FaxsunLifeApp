package com.faxsun.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BaseActivity extends Activity{
	
	 /** 
     * �ر�Activity�Ĺ㲥�������Զ���Ļ����У���������Activity�̳����Activity���� 
     */  
    protected BroadcastReceiver finishAppReceiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            finish();  
        }  
    };  
  
    @Override  
    public void onResume() {  
        super.onResume();  
        // �ڵ�ǰ��activity��ע��㲥  
        IntentFilter filter = new IntentFilter();  
        filter.addAction("net.loonggg.exitapp");  
        this.registerReceiver(this.finishAppReceiver, filter);  
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        this.unregisterReceiver(this.finishAppReceiver);  
    }

}
