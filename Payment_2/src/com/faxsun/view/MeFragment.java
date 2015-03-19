package com.faxsun.view;

import com.example.payment_2.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MeFragment extends Fragment {
	
	public static String TAG = "MeFragment";
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState)  
    {		
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.fragment_me, (ViewGroup)getActivity().findViewById(R.id.vPager), false); 
		
		initLayout();
				
    }
	
	private void initLayout() {
		// TODO Auto-generated method stub
		
		LinearLayout user = (LinearLayout)view.findViewById(R.id.user_info);
		LinearLayout collection = (LinearLayout)view.findViewById(R.id.collection);
		LinearLayout address = (LinearLayout)view.findViewById(R.id.address);
		LinearLayout order = (LinearLayout)view.findViewById(R.id.order);
		LinearLayout phone_number = (LinearLayout)view.findViewById(R.id.phone_number);
		LinearLayout set_up = (LinearLayout)view.findViewById(R.id.set_up);
		
		Intent intent = new Intent();
		
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
