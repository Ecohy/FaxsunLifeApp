package com.faxsun.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.payment_2.R;

public class FragmentBottomButton extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {		
		View view = inflater.inflate(R.layout.fragment_bottom_button, container, false); 		
		return view;		
    }

}
