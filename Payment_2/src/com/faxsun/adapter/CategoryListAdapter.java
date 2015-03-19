package com.faxsun.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.payment_2.R;
import com.faxsun.controller.AppController;
import com.faxsun.moudle.CategoryItem;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	private List<CategoryItem> list = new ArrayList<CategoryItem>();
	private Activity a;	
	public ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private static String TAG = "CategoryListAdapter";
	
	public CategoryListAdapter(Activity a,List<CategoryItem> l){
		this.a = a;
		list = l;
	}	

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}		

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "myClickListener.onClick");  
		if (mInflater == null)
			mInflater = (LayoutInflater) a
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vi=convertView;  
        if(convertView==null)  
            vi = mInflater.inflate(R.layout.category_item, null); 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        
        	NetworkImageView thumbNail = (NetworkImageView) vi
                .findViewById(R.id.image);
        	
			TextView id = (TextView)vi.findViewById(R.id.id);
			TextView name=(TextView)vi.findViewById(R.id.name);
			TextView retai_price=(TextView)vi.findViewById(R.id.retailprice);
//			TextView sale_price=(TextView)convertView.findViewById(R.id.saleprice);
			
			CategoryItem item = list.get(position);
			// 设置ListView的相关值  
			thumbNail.setImageUrl(item.getImg_url(), imageLoader);
	        id.setText(String.valueOf(item.getId()));  
	        name.setText(item.getName());  
	        retai_price.setText(String.valueOf(item.getRetail_price()));  
//	        sale_price.setText(String.valueOf(item.getSale_price()));  
	         
	        return vi;
		
	}

}
