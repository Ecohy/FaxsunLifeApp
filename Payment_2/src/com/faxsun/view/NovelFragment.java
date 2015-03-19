package com.faxsun.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.payment_2.R;
import com.faxsun.adapter.CategoryListAdapter;
import com.faxsun.controller.ActivityController;
import com.faxsun.moudle.CategoryItem;
import com.faxsun.moudle.Category.catogory_id_type;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class NovelFragment extends Fragment{
	
	catogory_id_type c = catogory_id_type.NOVEL;
	
	// JSON �ڵ�  
	public static final String KEY_CATEGORY = "category"; // parent node  
	public static final String KEY_PRODUCTS = "products"; // parent node
	public static final String KEY_PRODUCT = "product"; // parent node
    public static final String KEY_ID = "id";  //��Ʒid
    public static final String KEY_NAME = "name";  	//��Ʒ����
    public static final String KEY_RETAIL_PRICE = "retailPrice"; 	//��Ʒԭ��
    public static final String KEY_SALE_PRICE = "salePrice"; 		//�ۺ�۸�
    public static final String KEY_IMG = "primaryMedia"; 
    public static final String KEY_IMG_URL = "url";
	
	private ListView lv;
	private CategoryListAdapter adapter;
	private List<CategoryItem> l = new ArrayList<CategoryItem>();
	private ProgressDialog pDialog;
	ActivityController aController = new ActivityController();
	
	public static String TAG = "NovelFragment";
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState)  
    {		
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.novel_list, (ViewGroup)getActivity().findViewById(R.id.vPager), false); 		
		
		lv = (ListView)view.findViewById(R.id.list);
		adapter = new CategoryListAdapter(getActivity(),l);
		lv.setAdapter(adapter);
		
        
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
				Log.i(TAG , "onItemClick");
				
				TextView item_id;
				item_id = (TextView)view.findViewById(R.id.id);
				String str_item_id = (String)item_id.getText();
				Intent intent = new Intent();
				intent.setClass(getActivity(), ProductInfo.class);
				intent.putExtra("product_id", str_item_id);
				startActivity(intent);
			}				
		});
    }

	private void listPage(catogory_id_type c) throws JSONException {
		// TODO Auto-generated method stub
		Log.i(TAG, "listPage");
		String first_page = aController.getCategoryList(c);
		JSONphase(first_page);
	}	
	
	/*
	 * ��������json
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
	        // HashList���ӵ������б�  
	        l.add(item); 
	        //��ʾ���ﳵ�б�
		}
		adapter.notifyDataSetChanged();
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
}