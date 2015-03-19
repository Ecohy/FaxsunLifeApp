package com.faxsun.view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;
import com.faxsun.controller.AppController;

public class ProductInfo extends Activity implements OnGestureListener{
	
	public static String TAG = "ProductInfo";
	public static String base_url = "http://www.faxsun.com/faxsun";
	ActivityController ac = new ActivityController();
	public ImageLoader imageLoader = AppController.getInstance().getImageLoader(); 
	
	//http返回节点
	public static String HTTP_UNKNOWN_ERROR = "unknownError";
	public static String HTTP_CART_NOT_FOUND = "cartNotFound";
	// JSON 节点  
//	public static final String KEY_ITEMS = "orderItems"; // parent node  
    public static final String KEY_ID = "id";  								//商品id
    public static final String KEY_CATEGORY_ID = "defaultCategoryId"; 
    public static final String KEY_NAME = "name";  							//商品名称
    public static final String KEY_RETAIL_PRICE = "retailPrice"; 			//价格
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_DESCRIPTION= "longDescription";  		//详细信息
    public static final String KEY_PRIMARYMEDIA= "primaryMedia";  			//图片url
    public static final String KEY_IMG_URL = "url";  						//图片url字段
    
    public static final String KEY_MEDIAITEMS= "mediaItems";  				//其余图片
    public static final String KEY_MEDIA= "media";
    
    //图片滑动显示
    private GestureDetector gestureDetector;
	private	ViewFlipper	flipper;
	private	LinearLayout layout;
	private	Context	context;
	private	int	now	= 0;				//默认显示第一张图片
	private	int	pictureCounts = 5;		//产品详细信息共显示五张图片
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.product_info);
		
		//显示标题
		super.setTitle("商品详情");
		
		//获取当前产品id，放在不显示的textview中
		Intent intent = getIntent();
		final String product_id = intent.getStringExtra("product_id");
		Log.i(TAG, "product_id = "+product_id);
		TextView tv = (TextView)findViewById(R.id.product_id);
		tv.setText(product_id);
		TextView cate = (TextView)findViewById(R.id.category_id);
		
		//获取产品信息
		getProductInfo(product_id);
		
		final String product = (String) tv.getText();
		final String category = (String) cate.getText();
		
		//显示多张图片切换
		initViewFlipper();
		
		//监听加入购物车按钮
		Button addToCart = (Button)findViewById(R.id.add);
		addToCart.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// 这里添加一个加入购物车的动画吧
				addToCartAnim();
				
				//调用加入购物车函数
				addToCart(product,category);
				
				//然后跳转到购物车界面
				Intent intent = new Intent();
				intent.setClass(getApplication(), Cart.class);
				
			}
			
		});
	}

	/*
	 * 加入购物车
	 */	
	protected void addToCart(String product_id,String category_id) {
		// 购买数量默认为1
		int quantity = 1;
		ac.addToCart(product_id,category_id,1,true);
		
	}


	/*
	 * 功能：显示多张图片切换
	 */
	private void initViewFlipper() {
		
		gestureDetector = new GestureDetector(this);
    	flipper	= (ViewFlipper)findViewById(R.id.viewFlipper);
    	flipper.setDisplayedChild(now);	//显示第一张图片
    	
    	//用于显示翻页的小圆点
    	layout = (LinearLayout) findViewById(R.id.linearLayout); 
    	
    	generatePageControl();		//手势判断
    	flipper.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				return;
			}
    		
    	});
		
	}
	
	/*
	 * 小圆点切换控制
	 */
	private void generatePageControl() {
    	layout.removeAllViews();

        for (int i = 0; i < pictureCounts; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setPadding(0, 0, 6, 0);
                if (now  == i) {
                        imageView.setImageResource(R.drawable.circle_black);//当前图片显示灰圆圈
                } else {
                        imageView.setImageResource(R.drawable.circle_white);
                }
                this.layout.addView(imageView);
        }
    }

	protected void addToCartAnim() {
		// TODO Auto-generated method stub
		
	}

	private void getProductInfo(String product_id)  {
		
		//获取商品详细信息
		String product_info = ac.getProductInfo(product_id);
		JSONPhase(product_info);
		//获取商品sku信息
		String sku_info = ac.getSkuInfo(product_id);
		
	}

	private void JSONPhase(String product_info){
		Log.i(TAG, "JSONPhase");
		TextView id = (TextView)findViewById(R.id.product_id);
		TextView cate = (TextView)findViewById(R.id.category_id);
		NetworkImageView image1 = (NetworkImageView)findViewById(R.id.image1);
		NetworkImageView image2 = (NetworkImageView)findViewById(R.id.image2);
		NetworkImageView image3 = (NetworkImageView)findViewById(R.id.image3);
		NetworkImageView image4 = (NetworkImageView)findViewById(R.id.image4);
		NetworkImageView image5 = (NetworkImageView)findViewById(R.id.image5);
		TextView name = (TextView)findViewById(R.id.name);
		final WebView info = (WebView)findViewById(R.id.info);
		TextView price = (TextView)findViewById(R.id.price);
		
		try {
			JSONObject json = new JSONObject(product_info);
			id.setText(json.getString(KEY_ID));
			cate.setText(json.getString(KEY_CATEGORY_ID));
			//获取第一张图显示
			image1.setImageUrl(json.getJSONObject(KEY_PRIMARYMEDIA).getString(KEY_IMG_URL), imageLoader);	
//			Log.i(TAG, "image1 = "+json.getJSONObject(KEY_PRIMARYMEDIA).getString(KEY_IMG_URL));
			name.setText(json.getString(KEY_NAME));
//			Log.i(TAG, "name = "+json.getString(KEY_NAME));
			price.setText(json.getJSONObject(KEY_RETAIL_PRICE).getString(KEY_AMOUNT));
//			Log.i(TAG, "price = "+json.getJSONObject(KEY_RETAIL_PRICE).getString(KEY_AMOUNT));
			final String info_html = json.getString(KEY_DESCRIPTION);
			WebSettings webSettings = info.getSettings();
			//　优先使用缓存
			webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

			//支持缩放
			//webSettings.setBuiltInZoomControls(true);
			//设置图片大小，将字也变小了。
//			webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			//将图片调整到适合webview大小，将字也变小了
			webSettings.setUseWideViewPort(true);
			//自适应屏幕大小
			//webSettings.setLoadWithOverviewMode(true);
			//设置支持js脚本
//			webSettings.setJavaScriptEnabled(true);
			//设置文字大小，好像无效
//			webSettings.setDefaultFontSize(14);
			final StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<meta content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0\" name=\"viewport\">");
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.faxsun.com/faxsun/css/new_mobile_style.css\">");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<div id=\"description\">");
			sb.append(info_html);
			sb.append("</div>");
			sb.append("</body>");
			sb.append("</html>");
			Log.i(TAG,String.valueOf(sb));

			info.loadDataWithBaseURL(base_url,String.valueOf(sb),"text/html", "UTF-8",null);

			info.setWebViewClient(new WebViewClient() {  
				public boolean shouldOverrideUrlLoading(WebView view, String url) {  
					info.loadDataWithBaseURL(base_url,String.valueOf(sb),"text/html", "UTF-8",null);                 
	                return true; // 表示已经处理了这次URL的请求   
	            }
				
				/*
				 * 加载出来之前显示的正在加载图片
				 */
				public boolean onPageStarted(WebView view, String url) {  
					                 
	                return true; // 表示已经处理了这次URL的请求   
	            }
				 
			}); 
			//获取其余四张图片
			JSONArray mediaItems = json.getJSONArray(KEY_MEDIAITEMS);
			Log.i(TAG,"mediaItems = "+mediaItems.toString());
			
			JSONObject image_2 = mediaItems.getJSONObject(0);
			
			image2.setImageUrl(image_2.getString(KEY_IMG_URL), imageLoader);
			Log.i(TAG, "image2 = "+image_2.getString(KEY_IMG_URL));
			
			JSONObject image_3 = mediaItems.getJSONObject(1);
			image3.setImageUrl(image_3.getString(KEY_IMG_URL), imageLoader);
			Log.i(TAG, "image3 = "+image_3.getString(KEY_IMG_URL));
			JSONObject image_4 = mediaItems.getJSONObject(2);
			image4.setImageUrl(image_4.getString(KEY_IMG_URL), imageLoader);
			Log.i(TAG, "image4 = "+image_4.getString(KEY_IMG_URL));
			JSONObject image_5 = mediaItems.getJSONObject(3);
			image5.setImageUrl(image_5.getString(KEY_IMG_URL), imageLoader);
			Log.i(TAG, "image5 = "+image_5.getString(KEY_IMG_URL));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.i(TAG, "onFling");
		// TODO Auto-generated method stub
		if (e2.getX() - e1.getX() > 120) {			 
			Animation rInAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_in); 
			Animation rOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_out); 

			flipper.setInAnimation(rInAnim);
			flipper.setOutAnimation(rOutAnim);
			flipper.showPrevious();
			now--;
			if(now	<	0){
				now	=	pictureCounts	-	1;
			}
			generatePageControl();
			return true;
		} else if (e2.getX() - e1.getX() < -120) {		
			Animation lInAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_in);		
			Animation lOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_out); 	

			flipper.setInAnimation(lInAnim);
			flipper.setOutAnimation(lOutAnim);
			flipper.showNext();
			now++;
			if(now	>	pictureCounts	-	1){
				now	=	0;
			}
			generatePageControl();
			return true;
		}
		return true;
	}	
}
