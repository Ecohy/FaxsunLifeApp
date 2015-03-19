package com.faxsun.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.payment_2.R;
import com.faxsun.adapter.CategoryListAdapter;
import com.faxsun.controller.ActivityController;
import com.faxsun.moudle.Category;
import com.faxsun.moudle.CategoryItem;
import com.faxsun.moudle.Category.catogory_id_type;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Health extends ListActivity{
	
	catogory_id_type c = catogory_id_type.HEALTH;
	
	// JSON 节点  
	public static final String KEY_CATEGORY = "category"; // parent node  
	public static final String KEY_PRODUCTS = "products"; // parent node
	public static final String KEY_PRODUCT = "product"; // parent node
    public static final String KEY_ID = "id";  //商品id
    public static final String KEY_NAME = "name";  	//商品名称
    public static final String KEY_RETAIL_PRICE = "retailPrice"; 	//商品原价
    public static final String KEY_SALE_PRICE = "salePrice"; 		//折后价格
    public static final String KEY_IMG = "primaryMedia"; 
    public static final String KEY_IMG_URL = "url";
	
	private ListView lv;
	private CategoryListAdapter adapter;
	private List<CategoryItem> l = new ArrayList<CategoryItem>();
	protected String TAG = "Health";
	private ProgressDialog pDialog;
	ActivityController aController = new ActivityController();
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
                
        setContentView(R.layout.category_list); 
        
        lv = getListView();
		adapter = new CategoryListAdapter(Health.this,l);
		lv.setAdapter(adapter);
		
		pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        
        try {
			listPage(c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				// TODO Auto-generated method stub
				Log.i("onItemClick===========", "here begin");
				
				TextView item_id;
				item_id = (TextView)view.findViewById(R.id.id);
				String str_item_id = (String)item_id.getText();
				Intent intent = new Intent();
				intent.setClass(getApplication(), ProductInfo.class);
				intent.putExtra("product_id", str_item_id);
				startActivity(intent);
			}				
		});				
	}

	private void listPage(catogory_id_type c) throws JSONException {
		// TODO Auto-generated method stub
		Log.i(TAG, "listPage");
		String first_page = aController.getCategoryList(c);
		hidePDialog();
		JSONphase(first_page);
	}	
	
	/*
	 * 解析返回json
	 */
	private void JSONphase(String response) throws JSONException{
		Log.e(TAG, "JSONphase");
		JSONObject json = new JSONObject(response);
		JSONArray jsonArray = new JSONArray();
		jsonArray = json.getJSONArray(KEY_PRODUCTS);
		
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject orderitem = (JSONObject)jsonArray.get(i);
			CategoryItem item = new CategoryItem();
			item.setId(orderitem.getInt(KEY_ID));
//			item.setImg_url(orderitem.getString(KEY_IMG_URL));
			item.setName(orderitem.getString(KEY_NAME));
			item.setRetail_price(orderitem.getJSONObject(KEY_RETAIL_PRICE).getDouble("amount"));
//			item.setSale_price(orderitem.getJSONObject(KEY_SALE_PRICE).getDouble("amount"));
	        
	        item.setImg_url(orderitem.getJSONObject(KEY_IMG).getString(KEY_IMG_URL));
	        // HashList添加到数组列表  
	        l.add(item); 
	        //显示购物车列表
		}
		adapter.notifyDataSetChanged();
	}	
	
	private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
