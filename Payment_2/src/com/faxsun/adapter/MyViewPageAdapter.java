package com.faxsun.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyViewPageAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> fragmentList;
	FragmentManager fm;
	ArrayList<String> titleList;
	public MyViewPageAdapter(FragmentManager fm,List<Fragment> list,ArrayList<String> titleList){
		super(fm);
		this.fm = fm;
		this.fragmentList = list;
		this.titleList = titleList;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
//		return null;
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titleList.get(position);
	}
	
	@Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
