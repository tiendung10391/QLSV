package com.qlsv.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsv.MainActivity;
import com.example.qlsv.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by kunph_000 on 08/09/2015.
 */
public class Fragment_thongtinsv extends Activity {
	private ActionBar actionBar;
	// Progress Dialog Object
	ProgressDialog prgDialog;
	String masvien,ip;
	TextView masv, gioitinh, lop, diachi, quequan, sdt, email,name,date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thongtin_sv);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#2253a2")));
		Intent in = getIntent();
		masvien = in.getStringExtra("MaSV");
		//ip
		ip = MainActivity.ip.toString();
		// id textview
		masv = (TextView) findViewById(R.id.Masv);
		masv.setText(masvien);
		gioitinh = (TextView) findViewById(R.id.Gioitinh);
		lop = (TextView) findViewById(R.id.Lop);
		diachi = (TextView) findViewById(R.id.Diachi);
		quequan = (TextView) findViewById(R.id.Quequan);
		sdt = (TextView) findViewById(R.id.Sdt);
		email = (TextView) findViewById(R.id.Email);
		name =(TextView) findViewById(R.id.name);
		date = (TextView) findViewById(R.id.date);
		//
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
		prgDialog.setMessage("Đang tải dữ liệu...");
		// Set Cancelable as False
		prgDialog.setCancelable(false);
		thongtinSV();
	}
	public void thongtinSV() {
		// lay du lieu nhap
		
		RequestParams params = new RequestParams();		
			// Put Http parameter MaSV
			params.put("MaSV", masvien);
			invokeWS(params);
	}
	/**
	 * Method that performs RESTful webservice invocations
	 * 
	 * @param params
	 */
	public void invokeWS(RequestParams params) {
		// Show Progress Dialog
		prgDialog.show();
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(
				ip+"/WebServiesQLSV/rest/SwSinhVien/checkSinhvien",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http response code
					// '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						prgDialog.hide();
						try {
							// JSON Object
							JSONObject obj = new JSONObject(response);
							// When the JSON response has status boolean value
							// assigned with true
							name.setText(obj.getString("TenSV"));
							date.setText(obj.getString("NgaySinh"));
							masv.setText(obj.getString("MaSV"));
							Boolean gt = obj.getBoolean("GioiTinh");
							if (gt) {
								gioitinh.setText("Nữ");
							}else {
								gioitinh.setText("Nam");
							}
							lop.setText(obj.getString("MaLop"));
							diachi.setText(obj.getString("DiaChi"));
							quequan.setText(obj.getString("QueQuan"));
							sdt.setText(obj.getString("Sdt"));
							email.setText(obj.getString("Email"));						
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(
									getApplicationContext(),
									"Lỗi tải dữ liệu!",
									Toast.LENGTH_LONG).show();
							e.printStackTrace();

						}
					}

					// When the response returned by REST has Http response code
					// other than '200'
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						// Hide Progress Dialog
						prgDialog.hide();
						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Vui lòng kiểm tra server.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
