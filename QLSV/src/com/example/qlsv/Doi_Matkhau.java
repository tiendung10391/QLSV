package com.example.qlsv;

import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qlsv.fragment.Fragment_thongtinsv;

import android.app.ActionBar;
import android.app.Activity;
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

public class Doi_Matkhau extends Activity{
	public static String masvien,host;
	private ActionBar actionBar;
	String prefname="my_data";
	EditText mk1,mk2,mk3;
	Button b1,b2;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.doi_mkhau);
			mk1 = (EditText)findViewById(R.id.matkhau1);
			mk2 = (EditText)findViewById(R.id.matkhau2);
			mk3 = (EditText)findViewById(R.id.matkhau3);
			b1 = (Button)findViewById(R.id.doi);
			b2 = (Button)findViewById(R.id.lai);
			actionBar = getActionBar();
			actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2253a2")));
			masvien = Fragment_Activity.masv;
			host = MainActivity.ip.toString();
			
			nhapdulieu();
		}
		private void nhapdulieu() {
			// TODO Auto-generated method stub
			b1.setOnClickListener(new OnClickListener() {
//				String mk22 = mk2.getText().toString();
//				String mk32 = mk3.getText().toString();
				@Override
				public void onClick(View v) {
					String mk22 = mk2.getText().toString();
					if(mk3.getText().toString().equals("") || mk2.getText().toString().equals("") || mk1.getText().toString().equals("")){
						Toast.makeText(getApplicationContext(), "Xin nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
					}else if(!mk3.getText().toString().equals(mk22)){
						mk3.setText("");
						Toast.makeText(getApplicationContext(), "Mật khẩu không trùng nhau !", Toast.LENGTH_LONG).show();
					}else{
						Tramatkhau();
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
		private void Tramatkhau() {
			// TODO Auto-generated method stub
			String mk = mk1.getText().toString();
			RequestParams params = new RequestParams();
				// Put Http parameter username with value of Email Edit View control
			params.put("TaiKhoan", masvien);
			params.put("MatKhau", mk);
			invokeWS(params);
		}
		private void invokeWS(RequestParams params) {
			// TODO Auto-generated method stub
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(
					host +"/WebServiesQLSV/rest/SwSinhVien/checkLogin",
					params, new AsyncHttpResponseHandler() {
						// When the response returned by REST has Http response code
						// '200'
						@Override
						public void onSuccess(String response) {
							// Hide Progress Dialog
							try {
								// JSON Object
								JSONObject obj = new JSONObject(response);
								// When the JSON response has status boolean value
								// assigned with true
								if (obj.getBoolean("status")) {
									doimatkhau();
														
								}
								// Else display error message
								else {
									mk1.setText("");
									Toast.makeText(getApplicationContext(),"Báº¡n nháº­p máº­t kháº©u hiá»‡n táº¡i cá»§a báº¡n khÃ´ng Ä‘Ãºng!",
											Toast.LENGTH_LONG).show();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								Toast.makeText(getApplicationContext(),
										"Lá»—i!", Toast.LENGTH_LONG).show();
								e.printStackTrace();

							}
						}
					});
		}
		private void doimatkhau() {
			String mk = mk2.getText().toString();
			RequestParams params = new RequestParams();
				// Put Http parameter username with value of Email Edit View control
				params.put("MaSV", masvien);
				// Put Http parameter password with value of Password Edit Value
				// control
				params.put("MatKhau", mk);
				// Invoke RESTful Web Service with Http parameters
				invokeWS1(params);
		}
		private void invokeWS1(RequestParams params) {
			// TODO Auto-generated method stub
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(
					host +"/WebServiesQLSV/rest/doiMatKhau",
					params, new AsyncHttpResponseHandler());
			Toast.makeText(getApplicationContext(),
					"Ä�á»•i máº­t kháº©u thÃ nh cÃ´ng!", Toast.LENGTH_LONG).show();
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
			switch(id){
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
			//táº¡o Ä‘á»‘i tÆ°á»£ng getSharedPreferences
			  SharedPreferences pre=getSharedPreferences
			  (prefname, MODE_PRIVATE);
			  //táº¡o Ä‘á»‘i tÆ°á»£ng Editor Ä‘á»ƒ lÆ°u thay Ä‘á»•i
			  SharedPreferences.Editor editor=pre.edit();
			  editor.clear();
			//cháº¥p nháº­n lÆ°u xuá»‘ng file
			  editor.commit();
		}
}