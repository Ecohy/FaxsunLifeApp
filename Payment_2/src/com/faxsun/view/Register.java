package com.faxsun.view;

import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{
	
	//ע��View
	private EditText register_username;
	private EditText register_passwd;
	private EditText reregister_passwd;
	private Button register_submit;	
	private Button bn_login;
	private Button bn_forget_pass;
	String TAG = "Register";
	
//	private static String registerUrl1="http://192.168.1.57:8080/faxsun/api/v1/fsResource/customer";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//��ʾע��ҳ��
		setContentView(R.layout.register);
		
		register_username = (EditText)findViewById(R.id.register_email);
		register_passwd = (EditText)findViewById(R.id.register_password);
		reregister_passwd = (EditText)findViewById(R.id.register_password_confirmation);
		register_submit = (Button)findViewById(R.id.bn_register);
		bn_login = (Button)findViewById(R.id.bn_login);
		bn_forget_pass = (Button)findViewById(R.id.bn_forgetpass);
		
		// ��ʧȥ����ʱ��ʱ�ж�
		register_username.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				// �����ж����䣨�û������Ƿ���Ϲ淶
				if(!hasFocus){
					String username = register_username.getText().toString().trim();
					if(!(isEmailorPhone(username))){
						showToast("�����ʽ����ȷ����������д��");
					}
				}
			}
			
		});
		register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO �����Ƿ��и��Ӷ�Ҫ��
				if(!hasFocus){
//					String pass = register_passwd.getText().toString();
					if(register_passwd.getText().toString().trim().length()<6){
						showToast("���벻��С��6���ַ�");
					}
				}
			}
			
		});
		reregister_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// ȷ���������������һ�£�
				if(!hasFocus){
					if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
						showToast("�����������벻һ��");
					}
				}
			}
			
		});
		
		// ע�ᰴť����
		register_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//���ȼ���û������Ƿ���Ϲ淶����������ֱ�ӷ���
				if(!checkEdit()){
					return;
				}
				//�������״��
				//TODO �Ƿ���Ҫ�Ե�ǰ�������Ϊ������������жϣ�
				if(!isNetworkAvailable(Register.this)){
					return;
				}
				
				String username = register_username.getText().toString().trim();
				String password = register_passwd.getText().toString().trim();
				
				if(ActivityController.SUCCESS == ActivityController.register(username,password)){
					Intent intent = new Intent(Register.this,Login.class);  
					Register.this.startActivity(intent); 
 //                   finish();
					showToast("ע��ɹ������¼");
				}
				else{
					//�û��Ѿ�ע�᣿
					showToast("ע��ʧ��");
				}
			}					
		});	
		
		//������¼��ť
        bn_login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this,Login.class);
				startActivity(intent);
				
			}
        	
        });
        //�����������밴ť
        bn_forget_pass.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this,ResetPass.class);
				startActivity(intent);				
			}      	
        });
	}		
	
	/*
	 * toast
	 */
	private void showToast(String s) {
		Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
	}
	
	/********
	 * ���ܣ��ж��û����Ƿ�Ϊ��������ֻ�����
	 * ������ username �û���
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
		
	/**
	 * ���ܣ���������Ƿ���Ϲ淶
	 * 1���Ƿ���ǿ�
	 * 2���û����Ƿ�Ϊ����
	 * 3��������ȷ�������Ƿ�һ��
	 * 
	 * */
	private boolean checkEdit(){
		String username = register_username.getText().toString().trim();
		if(username.equals("")){
			showToast("�û�������Ϊ��");
		}else if(!(isEmailorPhone(username))){
			showToast("������д��ʽ����ȷ����������д");
		}else if(register_passwd.getText().toString().trim().equals("")){
			showToast("���벻��Ϊ��");
		}else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
			showToast("�����������벻һ��");
		}else{
			return true;
		}
		return false;
	}
	
	/**
	 * ���ܣ���鵱ǰ�Ƿ�������
	 * 
	 * */
	public static boolean isNetworkAvailable(Context context) { 
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (connectivity == null) { 
			Log.i("NetWorkState", "Unavailabel"); 
//			Toast.makeText(this, "��ǰ���粻���ã�", Toast.LENGTH_SHORT).show();
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
