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
	//http���ؽڵ�
	public static String HTTP_UNKNOWN_ERROR = "unknownError";
	public static String HTTP_CART_NOT_FOUND = "cartNotFound";
	// JSON �ڵ�  
	public static final String KEY_ITEMS = "orderItems"; // parent node  
    public static final String KEY_ID = "id";  //��Ʒid
    public static final String KEY_NAME = "name";  	//��Ʒ����
    public static final String KEY_SALE_PRICE = "salePrice"; 		//�ۺ�۸�
    public static final String KEY_QUANTITY= "quantity";  			//��������
    public static final String KEY_IMG_URL = "urlOfSkuImage";  			//ͼƬurl 
    
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
	 * ��ȡ�ҵĹ��ﳵ��Ϣ
	 */
	private void getCartInfo() throws JSONException{
		Log.e(TAG, "getCartInfo");
		String cart = aController.getCartInfo();

		JSONphase(cart);
	}
	/*
	 * ��������json
	 */
	private void JSONphase(String response) throws JSONException{
		Log.e(TAG, "JSONphase");
		JSONObject json = new JSONObject(response);
		if(json.has("id")){
			//���ﳵ����,��ʾ���ﳵ�б�
			listCartInfo(json);
		}else{
			JSONArray jsonArray = new JSONArray();
			jsonArray = json.getJSONArray("messages");
			JSONObject jsonObject = (JSONObject)jsonArray.get(0);
			String info = (String) jsonObject.get("messageKey");
			
			if(info.equals(HTTP_CART_NOT_FOUND)){
				//���ﳵ�����ڣ���ʾ�޹��ﳵfragment
				listNoCartFragment();
				
			}else if(info.equals(HTTP_UNKNOWN_ERROR)){
				//����������
//				showToast(HTTP_UNKNOWN_ERROR);
			}
		}
	}

		/*
		 * ���ﳵ�б���ʾ
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
		        
		        //��װurl
		        String img_u = orderitem.getString(KEY_IMG_URL);
		        Log.e(TAG, "img_u = "+img_u);
		        String[] img_ur = img_u.split("[?]");
		        Log.e(TAG, "img_ur = "+img_ur[0].toString());
//		        String img_url = base_url+img_ur[0].toString()+"?thumbnail";
		        String img_url = base_url+img_ur[0].toString()+"?mobile";
		        Log.e(TAG, img_url);
		        item.setImg_url(img_url);
		        // HashList��ӵ������б�  
		        orderList.add(item); 
		        //��ʾ���ﳵ�б�
			}
			adapter.notifyDataSetChanged();
		}
		
		/*
		 * û�й��ﳵ���л�fragment
		 */
		private void listNoCartFragment(){
			
			Log.e(TAG, "listNoCartFragment");
			//���ﳵ������,�Ƴ�fragment_cart�滻Ϊû�й����б��textview
			TextView text = (TextView)view.findViewById(R.id.textview);
			text.setText("���Ĺ��ﳵ�ﻹû���κ���ƷŶ~~");
			
			
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
//			// �滻fragment
//			transaction.replace(R.id.fragment_cart, newFragment);
////			transaction.addToBackStack(null);
//
//			// Commit 
//			transaction.commit();
		}

}
