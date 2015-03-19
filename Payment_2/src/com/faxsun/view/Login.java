package com.faxsun.view;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener; 
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	
	//http返回节点
	public static String HTTP_USERNAME_NOT_EXISTED = "usernameNotExisted";
	public static String HTTP_PASSWORD_ERROR = "passwordError";
	public static String HTTP_UNKNOWN_ERROR = "unknownError";
	//当前用户信息
	private int customer_id = 0;
	private String customer_username = null;
	
	//登录View
	private EditText login_username;
	private EditText login_passwd;
	private Button login_submit;
	private CheckBox cb_remember_pass;
	private Button bn_register;
	private Button bn_forgetpass;
	private SharedPreferences sp;	
	String TAG = "Login";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.login);
		
		cb_remember_pass = (CheckBox)findViewById(R.id.cb_auto_login);
		login_username = (EditText)findViewById(R.id.et_user);
		login_passwd = (EditText)findViewById(R.id.et_pass);
		login_submit = (Button)findViewById(R.id.bn_login);
		bn_register = (Button)findViewById(R.id.bn_register);
		bn_forgetpass = (Button)findViewById(R.id.bn_forgetpass);
		
		sp = this.getSharedPreferences("faxsunlife_user", Activity.MODE_PRIVATE); 
		
		 //判断记住密码状态  
	      if(sp.getBoolean("AUTO_ISCHECK", false))  
          {  
                 //设置默认是记住登录状态  
	    	  cb_remember_pass.setChecked(true); 
	    	  	login_username.setText(sp.getString("USER_NAME", ""));  
	    	  	login_passwd.setText(sp.getString("PASSWORD", ""));                                 
          }  
	      
		// 当失去焦点时及时判断
		login_username.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				//这里判断用户名非空
				if(!hasFocus){
					String username = login_username.getText().toString().trim();
					if(username.length()==0){
						showToast("用户名不能为空！");
					}
				}
			}
			
		});
		login_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO 密码是否有复杂度要求？
				if(!hasFocus){
//							String pass = register_passwd.getText().toString();
					if(login_passwd.getText().toString().trim().length()== 0){
						showToast("密码不能为空！");
					}
				}
			}
			
		});

				
		// 登录按钮监听
		login_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//首先监测用户输入是否符合规范，不符合则直接返回
				String username = login_username.getText().toString().trim();
				String password = login_passwd.getText().toString().trim();
				String login_message = null;
				if(!checkEdit()){
					return;
				}
				//监测网络状况
				//TODO 是否需要对当前网络具体为何种网络进行判断？
				if(!isNetworkAvailable(Login.this)){
					return;
				}				
				else{
					login_message = ActivityController.login(username,password);
					if((ActivityController.SUCCESS == login_message)){
						
						//返回成功，记住用户密码
						//记住用户名、密码、  
	                    Editor editor = sp.edit();  
	                    editor.putString("USER_NAME", username);  
	                    editor.putString("PASSWORD",password);  
	                    editor.commit(); 
	                    //跳转界面  
	                    Intent intent = new Intent(Login.this,FaxsunHome.class);  
	                    Login.this.startActivity(intent); 
	                    finish();
					}
					else if(login_message.equals(HTTP_USERNAME_NOT_EXISTED)){
						//用户名不存在
						login_passwd.setText("");
						login_username.setText("");
						showToast("用户名不存在，请重新输入");
	                    
					}else if(login_message.equals(HTTP_PASSWORD_ERROR)){
						//返回失败，清空密码
						login_passwd.setText("");
					//	login_username.setText("");
						showToast("密码错误，请重新输入");
					}
					else if(login_message.equals(HTTP_UNKNOWN_ERROR)){
						showToast("服务器忙，请稍后重试");
					}
				}									
			}						
		});	
		
		//监听记住密码  
        cb_remember_pass.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
                if (cb_remember_pass.isChecked()) {  
                    System.out.println("记住密码已选中");  
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();  
  
                } else {  
                    System.out.println("记住密码没有选中");  
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();  
                }  
            }

        });
        
        //监听注册按钮
        bn_register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this,Register.class);
				startActivity(intent);
				finish();
				
			}
        	
        });
        //监听忘记密码按钮
        bn_forgetpass.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this,ResetPass.class);
				startActivity(intent);
				finish();
			}        	
        });				
	}
	
	protected void showToast(String string) {
		
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
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
		if(customer_id == 0){
			switch(item.getItemId()){
			case R.id.menu_cart:
				intent= new Intent(this,Login.class);
				showToast("您还没有登录，请先登录");
				startActivity(intent);
				break;
			case R.id.menu_login:
				intent= new Intent(this,Login.class);
				startActivity(intent);
				break;
			case R.id.menu_myorder:
				intent= new Intent(this,Login.class);
				showToast("您还没有登录，请先登录");
				startActivity(intent);
				break;
			case R.id.menu_mycollection:
				intent= new Intent(this,Login.class);
				showToast("您还没有登录，请先登录");
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
		}else{
			Log.i("else=====customer_username",customer_username );
			Log.i("else=======customer_id",""+customer_id );
			switch(item.getItemId()){
			case R.id.menu_cart:
//				intent.putExtra("customer_id", customer_id);
				intent= new Intent(this,Cart.class);
				startActivity(intent);
				break;
			case R.id.menu_login:
				intent= new Intent(this,CustomerInfo.class);
				startActivity(intent);
				break;
			case R.id.menu_myorder:
				intent.putExtra("customer_id", customer_id);
				intent= new Intent(this,Myorder.class);
				startActivity(intent);
				break;
			case R.id.menu_mycollection:
				intent.putExtra("customer_id", customer_id);
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
		}						
		return true;
	}
	
	/**
	 * 功能：检查输入是否符合规范
	 * 1、是否均非空
	 * 2、用户名是否为邮箱
	 * 3、密码与确认密码是否一致
	 * 
	 * */
	private boolean checkEdit(){
		String username = login_username.getText().toString().trim();
		if(username.equals("")){
			showToast("用户名不能为空");
		}else if(login_passwd.getText().toString().trim().equals("")){
			showToast("密码名不能为空");
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

}
