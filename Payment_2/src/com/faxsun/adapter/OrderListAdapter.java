package com.faxsun.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.payment_2.R;
import com.faxsun.controller.ActivityController;
import com.faxsun.controller.AppController;
import com.faxsun.moudle.OrderItem;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter{
	
	private ActivityController ac = new ActivityController();
	private Activity activity;  
	private List<OrderItem> orderItems;  
    private static LayoutInflater inflater;
    public ImageLoader imageLoader = AppController.getInstance().getImageLoader(); //用来下载图片的类，后面有介绍  
//	private OnClick listener;
    private static String TAG = "OrderListAdapter";
	
	public OrderListAdapter(Activity a,List<OrderItem> l){
		activity = a;  
		orderItems=l;    
	}		

	@Override
	public int getCount() {
		return orderItems.size();
	}

	@Override
	public Object getItem(int position) {
		return orderItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}		

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.e(TAG, "getView");
		if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vi=convertView;  
        if(convertView==null)  
            vi = inflater.inflate(R.layout.order_item, null); 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        
        	NetworkImageView thumbNail = (NetworkImageView) vi
                .findViewById(R.id.image);
        	TextView id = (TextView)vi.findViewById(R.id.id);
//			ImageView image=(ImageView)vi.findViewById(R.id.thumbnail);
			TextView name = (TextView)vi.findViewById(R.id.name);
//			TextView retailprice=(TextView)vi.findViewById(R.id.retailprice);
			TextView saleprice=(TextView)vi.findViewById(R.id.saleprice);
			TextView quantity=(TextView)vi.findViewById(R.id.quantity);
			Button delete = (Button)vi.findViewById(R.id.delete);
			
			final OrderItem item = orderItems.get(position);
			 
			//监听删除按钮
			delete.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ac.deleteCartItem(item.getId());
			//		inflater.invalidate();
					Log.e(TAG, "delete");
				}
				
			});			
			
	        
	     // 设置ListView的相关值  
			thumbNail.setImageUrl(item.getImg_url(), imageLoader);
			String str_id = String.valueOf(item.getId());
	        id.setText(str_id);  
	        name.setText(item.getName());  
//	        retailprice.setText(String.valueOf(item.getRetail_price()));  
	        saleprice.setText(String.valueOf(item.getSale_price())); 
	        quantity.setText(String.valueOf(item.getItem_quantity()));  
	         
	        return vi;
	}
}
