package com.faxsun.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;

public class ResetPass extends Activity{
	
	private EditText et_user;
	private EditText et_pass;
	private EditText et_repass;
	private Button bn_commit;
	private static String TAG = "ResetPass";
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
                
        setContentView(R.layout.reset_pass); 
        
        et_user = (EditText)findViewById(R.id.et_user);
        et_pass = (EditText)findViewById(R.id.et_pass);
        et_repass = (EditText)findViewById(R.id.et_repass);
        bn_commit = (Button)findViewById(R.id.bn_commit);
        
     // 当失去焦点时及时判断
        et_user.setOnFocusChangeListener(new OnFocusChangeListener()
 		{
 			@Override
 			public void onFocusChange(View v, boolean hasFocus) {
 				
 				// 这里判断邮箱（用户名）是否符合规范
				if(!hasFocus){
					String username = et_user.getText().toString().trim();
					if(!(isEmailorPhone(username))){
						showToast("用户名必须是邮箱或者手机号码");
					}
				}
			}
			
		});
        et_pass.setOnFocusChangeListener(new OnFocusChangeListener()
 		{

 			@Override
 			public void onFocusChange(View v, boolean hasFocus) {
 				// TODO 密码是否有复杂度要求？
 				if(!hasFocus){
//     				String pass = register_passwd.getText().toString();
 					if(et_pass.getText().toString().trim().length()== 0){
 						showToast("密码不能为空");
 					}
 				}
 			} 			
 		});
        
        et_repass.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// 确认密码必须与密码一致！
				if(!hasFocus){
					if(!et_repass.getText().toString().trim().equals(et_pass.getText().toString().trim())){ 
						showToast("两次密码输入不一致");
					}
				}
			}			
		});   
        
        bn_commit.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			//首先监测用户输入是否符合规范，不符合则直接返回
			if(!checkEdit()){
				return;
			}
			//监测网络状况
			//TODO 是否需要对当前网络具体为何种网络进行判断？
			if(!isNetworkAvailable(ResetPass.this)){
				return;
			}			
			String username = et_user.getText().toString().trim();
			String password = et_pass.getText().toString().trim();
			String reset_pass = ActivityController.reset_password(username,password);
			if(ActivityController.SUCCESS == reset_pass){
				Intent intent = new Intent(ResetPass.this,Login.class);  
				startActivity(intent); 
                finish();
                showToast("密码已经重置，请登录");
			}
			else if(reset_pass.equalsIgnoreCase("usernameNotExisted")){
				et_user.setText("");
				et_pass.setText("");
				et_repass.setText("");
				showToast("用户名不存在，请重新输入");
			}else
				showToast("服务器正忙，请稍后重试");
				
		}       	
    });
	}
	
	/*
	 * 重置密码成功提示
	 */
	private void showToast(String s) {
		// TODO Auto-generated method stub
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}	

	/**
	 * 功能：检查输入是否符合规范
	 * 1、是否均非空
	 * 2、用户名是否为邮箱
	 * 3、密码与确认密码是否一致
	 * 
	 * */
	private boolean checkEdit(){
		String username = et_user.getText().toString().trim();
		if(username.equals("")){
			showToast("用户名不能为空");
		}else if(!(isEmailorPhone(username))){
			showToast("邮箱填写格式不正确，请重新填写");
		}else if(et_pass.getText().toString().trim().equals("")){
			showToast("密码不能为空");
		}else if(!et_pass.getText().toString().trim().equals(et_repass.getText().toString().trim())){
			showToast("两次密码输入不一致");
		}else{
			return true;
		}
		return false;
	}
	
	/**
	 * 功能：检查当前是否有网络
	 * 
	 * */
	public static boolean isNetworkAvailable(Context context) { 
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (connectivity == null) { 
			Log.i("NetWorkState", "Unavailabel"); 
//			Toast.makeText(this, "当前网络不可用！", Toast.LENGTH_SHORT).show();
			return false; 
			} else { 
				NetworkInfo[] info = connectivity.getAllNetworkInfo(); 
				if (info != null) { 
					for (int i = 0; i < info.length; i++) { 
						if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
							Log.i("NetWorkState", "Availabel");
			//				Toast.makeText(Register.this, "NetWorkState is availabel", Toast.LENGTH_SHORT).show();
							return true; 
						} 
					} 
				} 
			} 
			return false; 
		}
	
	/********
	 * 功能：判断用户名是否为邮箱或者手机号码
	 * 参数： username 用户名
	 * 		
	 * 
	 */
	protected static boolean isEmailorPhone(String username) {
		// TODO Auto-generated method stub
		String email_str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		String phone_str = "^1[3-9]{1}[0-9]{9}$";
//		String phone_str = "/^13[0-9]{1}[0-9]{8}$|15[0189]{1}[0-9]{8}$|189[0-9]{8}$/";
//		String phone_str = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		Pattern p_email = Pattern.compile(email_str);
		Pattern p_phone = Pattern.compile(phone_str);
		Matcher m_email = p_email.matcher(username);
		Matcher m_phone = p_phone.matcher(username);
//		Log.e(TAG, ""+m_phone.matches());
//		return m_phone.matches();
		return ((m_email.matches())|(m_phone.matches()));
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch(item.getItemId()){
		case R.id.menu_cart:
			Intent intent_cart = new Intent(this,Cart.class);
			startActivity(intent_cart);
			finish();
			break;
		case R.id.menu_login:
			Intent intent_login = new Intent(this,Login.class);
			startActivity(intent_login);
			finish();
			break;
		case R.id.menu_myorder:
			Intent intent_myorder = new Intent(this,Myorder.class);
			startActivity(intent_myorder);
			finish();
			break;
		case R.id.menu_mycollection:
			Intent intent_mycollection = new Intent(this,Mycollection.class);
			startActivity(intent_mycollection);
			finish();
			break;
		case R.id.menu_register:
			Intent intent_register = new Intent(this,Register.class);
			startActivity(intent_register);
			finish();
			break;
		case android.R.id.home:
			Intent intent_home = new Intent(this,FaxsunHome.class);
			startActivity(intent_home);
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}				
		return true;
	}

}
