package com.faxsun.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.payment_2.R;
import com.faxsun.moudle.Category;
import com.faxsun.moudle.RaidersCategoryItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryRadListAdapter extends BaseAdapter{
	
	private List<RaidersCategoryItem> list = new ArrayList<RaidersCategoryItem>();
	private Context context;
	
	public CategoryRadListAdapter(Context c,List<RaidersCategoryItem> l){
		context = c;
		list = l;
	}
	/*
	 * ռλ
	 */
	class ViewHolder{
		TextView Id;
		ImageView Image;
		TextView Name;
		TextView Price;
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
		ViewHolder viewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.raiders_item, null);
			viewHolder = new ViewHolder();
			viewHolder.Id=(TextView)convertView.findViewById(R.id.id);
			viewHolder.Image=(ImageView)convertView.findViewById(R.id.image);
			viewHolder.Name=(TextView)convertView.findViewById(R.id.name);
			viewHolder.Price=(TextView)convertView.findViewById(R.id.retailprice);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
//		viewHolder.Id.setText(""+(list.get(position).product_id));
//		viewHolder.Image.setBackgroundResource(list.get(position).img_id);
		viewHolder.Name.setText(list.get(position).name);
//		viewHolder.Price.setText(list.get(position).price);
		return convertView;
	}
}
