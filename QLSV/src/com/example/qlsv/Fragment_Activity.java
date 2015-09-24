package com.example.qlsv;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.qlsv.adapter.TabsPagerAdapter;
import com.qlsv.fragment.Fragment_thongtinsv;

public class Fragment_Activity extends FragmentActivity implements
ActionBar.TabListener{
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	public static String masv,malop;
	String prefname="my_data";
	// Tab titles
	private String[] tabs = { "Trang chủ","Chương trình", "Lịch học", "Xem Điểm" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		Intent intent = getIntent();
		masv = intent.getStringExtra("MaSV");
		malop = intent.getStringExtra("MaLop");
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2253a2")));
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	
	
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
			case R.id.nguoi_dung:
				Intent i1 = new Intent(Fragment_Activity.this, Fragment_thongtinsv.class);
				i1.putExtra("MaSV", masv);
				startActivity(i1);
				break;
			case R.id.dangXuat:
				Intent i2 = new Intent(Fragment_Activity.this, MainActivity.class);
				cleanTK();
				startActivity(i2);
				finish();
				break;
			case R.id.doiMK:
				Intent i3 = new Intent(Fragment_Activity.this, Doi_Matkhau.class);
				startActivity(i3);			
				finish();
				break;
			case R.id.dangky_gcm:
				Intent i4 = new Intent(Fragment_Activity.this, RegisterActivity.class);
				startActivity(i4);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	public void cleanTK() {
		//tạo đối tượng getSharedPreferences
		  SharedPreferences pre=getSharedPreferences
		  (prefname, MODE_PRIVATE);
		  //tạo đối tượng Editor để lưu thay đổi
		  SharedPreferences.Editor editor=pre.edit();
		  editor.clear();
		//chấp nhận lưu xuống file
		  editor.commit();
	}
}
