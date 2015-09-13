package com.qlsv.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.qlsv.R;

/**
 * Created by kunph_000 on 08/09/2015.
 */
public class Fragment_thongtinsv extends Activity {
	private ActionBar actionBar;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thongtin_sv);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2253a2")));
	}

//	@Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.thongtin_sv, container, false);
//    }
}
