package com.example.qlsv;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qlsv.fragment.Fragment_thongtinsv;

public class Doi_Matkhau extends Activity {
	public static String masvien, host;
	private ActionBar actionBar;
	String prefname = "my_data";
	EditText mk1, mk2, mk3;
	Button b1, b2;
	// Progress Dialog Object
	ProgressDialog prgDialog;
	String malop,mk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doi_mkhau);
		mk1 = (EditText) findViewById(R.id.matkhau1);
		mk2 = (EditText) findViewById(R.id.matkhau2);
		mk3 = (EditText) findViewById(R.id.matkhau3);
		b1 = (Button) findViewById(R.id.doi);
		b2 = (Button) findViewById(R.id.lai);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#2253a2")));
		masvien = Fragment_Activity.masv.toString();
		host = MainActivity.ip.toString();
		malop = Fragment_Activity.malop.toString();
		
		nhapdulieu();
	}

	private void nhapdulieu() {
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
		prgDialog.setMessage("Đang đăng nhập...");
		// Set Cancelable as False
		prgDialog.setCancelable(false);
		// TODO Auto-generated method stub
		b1.setOnClickListener(new OnClickListener() {
			// String mk22 = mk2.getText().toString();
			// String mk32 = mk3.getText().toString();
			@Override
			public void onClick(View v) {
				String mk22 = mk2.getText().toString();
				if (mk3.getText().toString().equals("")
						|| mk2.getText().toString().equals("")
						|| mk1.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Xin nhập đầy đủ thông tin !", Toast.LENGTH_LONG)
							.show();
				} else if (!mk3.getText().toString().equals(mk22)) {
					mk3.setText("");
					Toast.makeText(getApplicationContext(),
							"Mật khẩu không trùng nhau !", Toast.LENGTH_LONG)
							.show();
				} else {
					doimatkhau();
				}

			}

		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mk1.setText("");
				mk2.setText("");
				mk3.setText("");
			}
		});
	}

	private void doimatkhau() {
		mk = mk2.getText().toString();
		RequestParams params = new RequestParams();
		params.put("MatKhau", mk);
		params.put("MaSV", masvien);
		// Invoke RESTful Web Service with Http parameters
		invokeWS1(params);
	}

	private void invokeWS1(RequestParams params) {
		prgDialog.show();
		// TODO Auto-generated method stub
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(host + "/WebServiesQLSV/rest/SwSinhVien/editSinhVien", params,
				new AsyncHttpResponseHandler(){
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
							if (obj.getBoolean("status")) {
								Toast.makeText(getApplicationContext(),
										"Đổi nật khẩu thành công !",
										Toast.LENGTH_LONG).show();	
								navigatetoHomeActivity();
							}
							// Else display error message
							else {								
								Toast.makeText(getApplicationContext(),
										obj.getString("Lỗi khí đăng nhập ."),
										Toast.LENGTH_LONG).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(),
									"Lỗi đăng nhập!", Toast.LENGTH_LONG).show();
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
							Toast.makeText(getApplicationContext(),
									"Đăng nhập không đúng,mời bạn nhập lại!",
									Toast.LENGTH_LONG).show();
						}
					}
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		menu.findItem(R.id.doiMK).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.nguoi_dung:
			Intent i1 = new Intent(Doi_Matkhau.this, Fragment_thongtinsv.class);
			i1.putExtra("MaSV", masvien);
			startActivity(i1);
			break;
		case R.id.dangXuat:
			Intent i2 = new Intent(Doi_Matkhau.this, MainActivity.class);
			cleanTK();
			startActivity(i2);
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void cleanTK() {
		// táº¡o Ä‘á»‘i tÆ°á»£ng getSharedPreferences
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		// táº¡o Ä‘á»‘i tÆ°á»£ng Editor Ä‘á»ƒ lÆ°u thay Ä‘á»•i
		SharedPreferences.Editor editor = pre.edit();
		editor.clear();
		// cháº¥p nháº­n lÆ°u xuá»‘ng file
		editor.commit();
	}
	public void navigatetoHomeActivity() {
		Intent homeIntent = new Intent(Doi_Matkhau.this,
				MainActivity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		homeIntent.putExtra("MaSV", masvien);
		startActivity(homeIntent);
		finish();
	}
}