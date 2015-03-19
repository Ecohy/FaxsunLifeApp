package com.faxsun.view;

import java.util.ArrayList;
import java.util.List;

import com.example.payment_2.R;
import com.faxsun.adapter.MyViewPageAdapter;
import com.faxsun.controller.ActivityController;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class FaxsunHome extends FragmentActivity
{
	
	public static String TAG = "FaxsunHome";
	
	ActivityController ac = new ActivityController();
	
	private int currIndex;//��ǰҳ�����  
    private int bmpW;//����ͼƬ���  
    private int offset;//ͼƬ�ƶ���ƫ���� 
	//�ײ�������
	private TabHost mTabHost; 
	RadioGroup group;
	static int customer_id = 0;
	static String customer_username = null;
	
	private ViewPager viewPager;
	FirstPageFragment first = new FirstPageFragment();
	NovelFragment novel = new NovelFragment();
	CartFragment cart = new CartFragment();
	MeFragment me = new MeFragment();
	//fragment �б�
	List<Fragment> fragmentList;
	
	//�����б�
	ArrayList<String> titleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.home_bottom);
		
		InitTextView();
		InitViewPager(); 
	}
	
	/* 
     * ��ʼ����ǩ�� 
     */  
    public void InitTextView(){  
        TextView view1 = (TextView)findViewById(R.id.text1);  
        TextView view2 = (TextView)findViewById(R.id.text2);  
        TextView view3 = (TextView)findViewById(R.id.text3);  
        TextView view4 = (TextView)findViewById(R.id.text4);  
        
          
        view1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
			}
        	
        });  
        view2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
			}
        	
        });  
        view3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(2);
			}
        	
        });   
        view4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(4);
			}
        	
        });  
    }
    
    /* 
     * ��ʼ��ViewPager 
     */  
    public void InitViewPager(){ 
    	viewPager = (ViewPager)findViewById(R.id.vPager);
    	fragmentList = new ArrayList<Fragment>();
		fragmentList.add(first);
		fragmentList.add(novel);
		fragmentList.add(cart);
		fragmentList.add(me);
		
		titleList = new ArrayList<String>();
		titleList.add("��ҳ");
		titleList.add("�ᱦ");
		titleList.add("���ﳵ");
		titleList.add("��"); 
          
        //��ViewPager����������  
		
        viewPager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), fragmentList, titleList));  
        viewPager.setCurrentItem(0);//���õ�ǰ��ʾ��ǩҳΪ��һҳ  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());//ҳ��仯ʱ�ļ�����  
    }
    
    public class MyOnPageChangeListener implements OnPageChangeListener{  
        private int one = offset *2 +bmpW;//��������ҳ���ƫ����  
          
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
              
        }  
          
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
              
        }  
          
        @Override  
        public void onPageSelected(int arg0) {  
            // TODO Auto-generated method stub  
            Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//ƽ�ƶ���  
            currIndex = arg0;  
            animation.setFillAfter(true);//������ֹʱͣ�������һ֡����Ȼ��ص�û��ִ��ǰ��״̬  
            animation.setDuration(200);//��������ʱ��0.2��  
//            image.startAnimation(animation);//����ImageView����ʾ������  
            int i = currIndex + 1;  
            Log.i(TAG, "you clicl on text") ;
        }  
    }
		
		
		
		//�ײ�����
//		setContentView(R.layout.bottombar);	
//		
//		mTabHost = this.getTabHost();
//		mTabHost.addTab(mTabHost.newTabSpec("ONE").setIndicator("one").
//				setContent(new Intent(this,FirstPage.class)));
//		mTabHost.addTab(mTabHost.newTabSpec("TWO").setIndicator("two").
//				setContent(new Intent(this,MumAndBaby.class)));
//		mTabHost.addTab(mTabHost.newTabSpec("THREE").setIndicator("three").
//				setContent(new Intent(this,Novel.class)));
//		mTabHost.addTab(mTabHost.newTabSpec("FOUR").setIndicator("four").
//				setContent(new Intent(this,Health.class)));
//		mTabHost.addTab(mTabHost.newTabSpec("FIVE").setIndicator("five").
//				setContent(new Intent(this,Raiders.class)));
//		group = (RadioGroup)findViewById(R.id.main_radio);
//		group.setOnCheckedChangeListener(this);
					

	
	//�˳�����
	private long exitTime = 0;
	public boolean dispatchKeyEvent(KeyEvent event) {
	if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){ 
//		if(keyCode == KeyEvent.KEYCODE_HOME){ 
        if((System.currentTimeMillis()-exitTime) > 2000){ 
        	ac.showToast(this,"�ٰ�һ���˳�����");
//	        	Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
        	Log.i(TAG, ""+exitTime);
            exitTime = System.currentTimeMillis(); 
            Log.i(TAG, ""+exitTime);
        } else {
            this.finish();
        }
        return true;   
    }
		return super.dispatchKeyEvent(event);  
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
			login_item.setTitle("��¼");
		}
		
		//��ʾlogo
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
				ac.showToast(this,"����û�е�¼�����ȵ�¼");
				startActivity(intent);
				break;
			case R.id.menu_login:
				intent= new Intent(this,Login.class);
				startActivity(intent);
				break;
			case R.id.menu_myorder:
				intent= new Intent(this,Login.class);
				ac.showToast(this,"����û�е�¼�����ȵ�¼");
				startActivity(intent);
				break;
			case R.id.menu_mycollection:
				intent= new Intent(this,Login.class);
				ac.showToast(this,"����û�е�¼�����ȵ�¼");
				startActivity(intent);
				break;
			case R.id.menu_register:
				intent = new Intent(this,Register.class);
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
			default:
				return super.onOptionsItemSelected(item);
			}
		}						
		return true;
	}		

	
	public void onCheckedChanged(RadioGroup group, int checkedId) {   
            switch (checkedId) {  
            case R.id.radio_button0:  
                this.mTabHost.setCurrentTabByTag("ONE");  
                break;  
            case R.id.radio_button1:  
                this.mTabHost.setCurrentTabByTag("TWO");  
                break;  
            case R.id.radio_button2:  
                this.mTabHost.setCurrentTabByTag("THREE");  
                break;  
            case R.id.radio_button3:  
                this.mTabHost.setCurrentTabByTag("FOUR");  
                break;  
            case R.id.radio_button4:  
                this.mTabHost.setCurrentTabByTag("FIVE");  
                break;  
         }  
     }   
	
	
}
