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
	
	private int currIndex;//当前页卡编号  
    private int bmpW;//横线图片宽度  
    private int offset;//图片移动的偏移量 
	//底部导航栏
	private TabHost mTabHost; 
	RadioGroup group;
	static int customer_id = 0;
	static String customer_username = null;
	
	private ViewPager viewPager;
	FirstPageFragment first = new FirstPageFragment();
	NovelFragment novel = new NovelFragment();
	CartFragment cart = new CartFragment();
	MeFragment me = new MeFragment();
	//fragment 列表
	List<Fragment> fragmentList;
	
	//标题列表
	ArrayList<String> titleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.home_bottom);
		
		InitTextView();
		InitViewPager(); 
	}
	
	/* 
     * 初始化标签名 
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
     * 初始化ViewPager 
     */  
    public void InitViewPager(){ 
    	viewPager = (ViewPager)findViewById(R.id.vPager);
    	fragmentList = new ArrayList<Fragment>();
		fragmentList.add(first);
		fragmentList.add(novel);
		fragmentList.add(cart);
		fragmentList.add(me);
		
		titleList = new ArrayList<String>();
		titleList.add("首页");
		titleList.add("夺宝");
		titleList.add("购物车");
		titleList.add("我"); 
          
        //给ViewPager设置适配器  
		
        viewPager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), fragmentList, titleList));  
        viewPager.setCurrentItem(0);//设置当前显示标签页为第一页  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器  
    }
    
    public class MyOnPageChangeListener implements OnPageChangeListener{  
        private int one = offset *2 +bmpW;//两个相邻页面的偏移量  
          
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
            Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//平移动画  
            currIndex = arg0;  
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态  
            animation.setDuration(200);//动画持续时间0.2秒  
//            image.startAnimation(animation);//是用ImageView来显示动画的  
            int i = currIndex + 1;  
            Log.i(TAG, "you clicl on text") ;
        }  
    }
		
		
		
		//底部导航
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
					

	
	//退出代码
	private long exitTime = 0;
	public boolean dispatchKeyEvent(KeyEvent event) {
	if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){ 
//		if(keyCode == KeyEvent.KEYCODE_HOME){ 
        if((System.currentTimeMillis()-exitTime) > 2000){ 
        	ac.showToast(this,"再按一次退出程序");
//	        	Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
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
				ac.showToast(this,"您还没有登录，请先登录");
				startActivity(intent);
				break;
			case R.id.menu_login:
				intent= new Intent(this,Login.class);
				startActivity(intent);
				break;
			case R.id.menu_myorder:
				intent= new Intent(this,Login.class);
				ac.showToast(this,"您还没有登录，请先登录");
				startActivity(intent);
				break;
			case R.id.menu_mycollection:
				intent= new Intent(this,Login.class);
				ac.showToast(this,"您还没有登录，请先登录");
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
