package com.faxsun.view;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Mycollection extends Activity{
	private int customer_id = 0;
	private String customer_username = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//自定义标题显示
		setContentView(R.layout.mycollection);
		
//		setActionBarLayout(R.layout.actionbar_layout);
		
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		customer_id = ActivityController.current_customer_id;
		customer_username = ActivityController.current_customer_username;
		if(customer_id > 0){
			
			MenuItem login_item = menu.findItem(R.id.menu_login);
			login_item.setTitle(customer_username);
		}
		else if(customer_id == 0){
			MenuItem login_item = menu.findItem(R.id.menu_login);
			login_item.setTitle("登录");
		}
		
		//显示logo
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);				
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent intent = null;
		customer_id = ActivityController.current_customer_id;
		customer_username = ActivityController.current_customer_username;
		Log.i("=====customer_username",customer_username );
		Log.i("=======customer_id",""+customer_id );
		switch(item.getItemId()){
		case R.id.menu_cart:
//			intent.putExtra("customer_id", customer_id);
			intent= new Intent(this,Cart.class);
			startActivity(intent);
			break;
		case R.id.menu_login:
			intent= new Intent(this,CustomerInfo.class);
			startActivity(intent);
			break;
		case R.id.menu_myorder:
//			intent.putExtra("customer_id", customer_id);
			intent= new Intent(this,Myorder.class);
			startActivity(intent);
			break;
		case R.id.menu_mycollection:
//			intent.putExtra("customer_id", customer_id);
			intent= new Intent(this,Mycollection.class);
			startActivity(intent);
			break;
		case R.id.menu_register:
			intent = new Intent(this,Register.class);
			startActivity(intent);
			break;
		case android.R.id.home:
			intent = new Intent(this,FaxsunHome.class);
			startActivity(intent);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}								
		return true;
	}
	
	
	/**
	 * 实现setActionBarLayout方法，设置Actionbar布局 
	 * 
	 * */
	public void setActionBarLayout(int actionbarLayout) {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar( );
		if( null != actionBar ){
			actionBar.setDisplayShowHomeEnabled( false );
			actionBar.setDisplayShowCustomEnabled(true);
//			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);//导航模式必须设为NAVIGATION_MODE_LIST

			LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(actionbarLayout, null);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			actionBar.setCustomView(v,layout);
		}
	}
	
	/**
	 * 实现onClick方法，在这里面监听actionbar中按钮的点击事件 
	 * 
	 * */
	public void onClick( View v ){
		switch( v.getId( ) ){
			case R.id.actionbar_back:{
				this.finish();
				showToast( this, "actionbar_back" );
			}
			break;
			case R.id.actionbar_user:{
				//弹出下拉列表
//				SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.customer, android.R.layout.simple_spinner_dropdown_item);
				
				showToast( this, "actionbar_user" );
			}
			break;
			case R.id.actionbar_cart:{
				//进入购物车页面
				Intent intent_cart = new Intent(this,Cart.class);
				startActivity(intent_cart);
//				showToast( this, "actionbar_cart" );
			}
			break;
			case R.id.actionbar_search:{
				showToast( this, "actionbar_search" );
			}
			break;
			default:{
				
			}
			break;
		}
	}

	public void showToast(Context context, String string) {
		// TODO Auto-generated method stub
		if( null == context || TextUtils.isEmpty(string) ){
			return;
		}
		
		Toast.makeText(context, string, Toast.LENGTH_LONG ).show( );
		
	}

}
