package com.faxsun.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.payment_2.R;
import com.faxsun.adapter.OrderListAdapter;
import com.faxsun.controller.ActivityController;
import com.faxsun.controller.AppController;
import com.faxsun.moudle.OrderItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
/*
 * 购物车列表：显示加入购物车的商品
 */
public class Cart extends Activity{
	
	//url
	public static String base_url = "http://www.faxsun.com";
	//http返回节点
	public static String HTTP_UNKNOWN_ERROR = "unknownError";
	public static String HTTP_CART_NOT_FOUND = "cartNotFound";
	// JSON 节点  
	public static final String KEY_ITEMS = "orderItems"; // parent node  
    public static final String KEY_ID = "id";  //商品id
    public static final String KEY_NAME = "name";  	//商品名称
    public static final String KEY_SALE_PRICE = "salePrice"; 		//折后价格
    public static final String KEY_QUANTITY= "quantity";  			//购买数量
    public static final String KEY_IMG_URL = "urlOfSkuImage";  			//图片url
    public static final String TAG = "Cart"; 
    
    private ProgressDialog pDialog;
    private List<OrderItem> orderList = new ArrayList<OrderItem>();
    private ListView listView;    
    private OrderListAdapter adapter;
	
	ActivityController aController;
	private int customer_id = aController.current_customer_id;
	private String customer_username = aController.current_customer_username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cart);
		Log.e(TAG, "onCreate");
		
		listView = (ListView) findViewById(R.id.cart_list);
        adapter = new OrderListAdapter(this, orderList);
        listView.setAdapter(adapter);
        
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        
        try {
			getCartInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
//        AppController.getInstance().addToRequestQueue(movieReq);
        
		
	}
	
	@Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
	
	/*
	 * 获取我的购物车信息
	 */
	private void getCartInfo() throws JSONException{
		Log.e(TAG, "getCartInfo");
		String cart = aController.getCartInfo();
		
		hidePDialog();
		JSONphase(cart);
	}
	/*
	 * 解析返回json
	 */
	private void JSONphase(String response) throws JSONException{
		Log.e(TAG, "JSONphase");
		JSONObject json = new JSONObject(response);
		if(json.has("id")){
			//购物车存在,显示购物车列表
			listCartInfo(json);
		}else{
			JSONArray jsonArray = new JSONArray();
			jsonArray = json.getJSONArray("messages");
			JSONObject jsonObject = (JSONObject)jsonArray.get(0);
			String info = (String) jsonObject.get("messageKey");
			
			if(info.equals(HTTP_CART_NOT_FOUND)){
				//购物车不存在，显示无购物车fragment
				listNoCartFragment();
				
			}else if(info.equals(HTTP_UNKNOWN_ERROR)){
				//服务器错误
				showToast(HTTP_UNKNOWN_ERROR);
			}
		}
	}

		/*
		 * 购物车列表显示
		 */
		private void listCartInfo(JSONObject json) throws JSONException{

			
			Log.e(TAG, "listCartInfo");
//			ArrayList<HashMap<String, String>> itemsList = new ArrayList<HashMap<String, String>>(); 
			JSONArray jsonArray = new JSONArray();
			jsonArray = json.getJSONArray(KEY_ITEMS);
			
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject orderitem = (JSONObject)jsonArray.get(i);
				OrderItem item = new OrderItem();
				item.setId(orderitem.getInt(KEY_ID));
//				item.setImg_url(orderitem.getString(KEY_IMG_URL));
				item.setName(orderitem.getString(KEY_NAME));
//				item.setRetail_price(orderitem.getJSONObject(KEY_RETAIL_PRICE).getDouble("amount"));
				item.setSale_price(orderitem.getJSONObject(KEY_SALE_PRICE).getDouble("amount"));
				item.setItem_quantity(orderitem.getInt(KEY_QUANTITY));
		        
		        //组装url
		        String img_u = orderitem.getString(KEY_IMG_URL);
		        Log.e(TAG, "img_u = "+img_u);
		        String[] img_ur = img_u.split("[?]");
		        Log.e(TAG, "img_ur = "+img_ur[0].toString());
//		        String img_url = base_url+img_ur[0].toString()+"?thumbnail";
		        String img_url = base_url+img_ur[0].toString()+"?mobile";
		        Log.e(TAG, img_url);
		        item.setImg_url(img_url);
		        // HashList添加到数组列表  
		        orderList.add(item); 
		        //显示购物车列表
			}
			adapter.notifyDataSetChanged();
		}
		
		/*
		 * 没有购物车，切换fragment
		 */
		private void listNoCartFragment(){
			
//			Log.e(TAG, "listNoCartFragment");
//			//购物车不存在,移除fragment_cart替换为没有购物列表的textview
//			FragmentManager fragmentManager = getFragmentManager();
//			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//			// Create new fragment and transaction
//			FragmentNoCart newFragment = new FragmentNoCart();
//			android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//			// 替换fragment
//			transaction.replace(R.id.fragment_cart, newFragment);
////			transaction.addToBackStack(null);
//
//			// Commit 
//			transaction.commit();
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
//		case R.id.menu_cart:
//			intent.putExtra("customer_id", customer_id);
//			intent= new Intent(this,Cart.class);
//			startActivity(intent);
//			break;
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
	
	protected void showToast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
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
	
	private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
