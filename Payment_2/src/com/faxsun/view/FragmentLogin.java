package com.faxsun.view;

import com.example.payment_2.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLogin extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {		
		View view = inflater.inflate(R.layout.fragment_login, container, false); 		
		return view;		
    }

}
