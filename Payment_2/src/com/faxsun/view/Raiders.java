package com.faxsun.view;

import java.util.ArrayList;
import java.util.List;

import com.example.payment_2.R;
import com.faxsun.adapter.CategoryRadListAdapter;
import com.faxsun.controller.ActivityController;
import com.faxsun.moudle.RaidersCategoryItem;
import com.faxsun.moudle.Category.catogory_id_type;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Raiders extends ListActivity{
	
	int catogory_id = catogory_id_type.RAIDERS.ordinal();
	
	private ListView lv;
	private CategoryRadListAdapter adapter;
	private List<RaidersCategoryItem> l = new ArrayList<RaidersCategoryItem>();
	protected String TAG = "Raiders";
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
                
        setContentView(R.layout.category_list); 
        
 //       getData();
 //       getArrayListFromInternet(catogory_id);
        lv = getListView();
//        lv = (ListView)findViewById(R.id.list);
		adapter = new CategoryRadListAdapter(Raiders.this,getArrayListFromInternet(catogory_id));
		lv.setAdapter(adapter);
		
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
				intent.setClass(getApplication(), ProductInfo.class);
				intent.putExtra("product_id", str_item_id);
				startActivity(intent);
			}				
		});
    }

	private List<RaidersCategoryItem> getArrayListFromInternet(int catogory_id) {
		// TODO Auto-generated method stub
		List<RaidersCategoryItem> l = new ArrayList<RaidersCategoryItem>();
		l = ActivityController.getRaidersArrayListFromServer(catogory_id,l);
		return l;
	}

	private void getData() {
		// TODO Auto-generated method stub
		l.add(new RaidersCategoryItem(R.drawable.misfit15,0, "ÐÂÆæÏµÁÐ","100.00"));

		
	}

}