package com.faxsun.view;

import com.example.payment_2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNoCart extends Fragment {
	
	
	public static FragmentNoCart newInstance() {
		FragmentNoCart f = new FragmentNoCart();
        return f;
    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {		
		View view = inflater.inflate(R.layout.fragment_nocart, container, false); 		
		return view;		
    }

}
