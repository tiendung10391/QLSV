package com.qlsv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qlsv.fragment.Fragment_chuongtrinh;
import com.qlsv.fragment.Fragment_diemsv;
import com.qlsv.fragment.Fragment_lichhoc;
import com.qlsv.fragment.Fragment_thongtinsv;
import com.qlsv.fragment.Fragment_trangchu;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new Fragment_trangchu();
		case 1:
			// Games fragment activity
			return new Fragment_chuongtrinh();
		case 2:
			// Movies fragment activity
			return new Fragment_lichhoc();
		case 3:
			// Movies fragment activity
			return new Fragment_diemsv();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

}
