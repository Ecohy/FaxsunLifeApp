package com.faxsun.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.payment_2.R;
import com.faxsun.adapter.OrderListAdapter;
import com.faxsun.controller.ActivityController;
import com.faxsun.moudle.OrderItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CartFragment extends Fragment {
	
	//
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
    
    private List<OrderItem> orderList = new ArrayList<OrderItem>();
    private ListView listView;    
    private OrderListAdapter adapter;
	
	ActivityController aController;
	private int customer_id = aController.current_customer_id;
	private String customer_username = aController.current_customer_username;
	
	public static String TAG = "CartFragment";
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState)  
    {		
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.fragment_cart, (ViewGroup)getActivity().findViewById(R.id.vPager), false); 
		
		listView = (ListView)view.findViewById(R.id.cart_list);
        adapter = new OrderListAdapter(getActivity(), orderList);
        listView.setAdapter(adapter);
        
        try {
			getCartInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {		
		Log.i(TAG, "onCreateView");
		ViewGroup p = (ViewGroup)view.getParent();
		if(p!=null){
			p.removeAllViewsInLayout();
			Log.i(TAG, "removeAllViewsInLayout");
		}
		return view;		
    }
	
	/*
	 * 获取我的购物车信息
	 */
	private void getCartInfo() throws JSONException{
		Log.e(TAG, "getCartInfo");
		String cart = aController.getCartInfo();

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
//				showToast(HTTP_UNKNOWN_ERROR);
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
			
			Log.e(TAG, "listNoCartFragment");
			//购物车不存在,移除fragment_cart替换为没有购物列表的textview
			TextView text = (TextView)view.findViewById(R.id.textview);
			text.setText("您的购物车里还没有任何商品哦~~");
			
			
//			 FragmentTransaction trans = getFragmentManager().beginTransaction();
//             trans.replace(R.id.fragment_cart, FragmentNoCart.newInstance());
//             trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//             trans.addToBackStack(null);
//             trans.commit();
			
			
			
//			FragmentManager fragmentManager = getFragmentManager();
//			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//			// Create new fragment and transaction
//			FragmentNoCart newFragment = new FragmentNoCart();
////			FragmentTransaction transaction = getFragmentManager().beginTransaction();
//			FragmentTransaction transaction = getParentFragment().getChildFragmentManager().beginTransaction();
//
//			// 替换fragment
//			transaction.replace(R.id.fragment_cart, newFragment);
////			transaction.addToBackStack(null);
//
//			// Commit 
//			transaction.commit();
		}

}
