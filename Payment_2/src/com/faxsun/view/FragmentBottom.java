package com.faxsun.view;

import com.example.payment_2.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentBottom extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {		
		View view = inflater.inflate(R.layout.fragment_bottom, container, false); 		
		return view;		
    }
}
