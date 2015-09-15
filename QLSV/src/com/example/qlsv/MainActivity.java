package com.example.qlsv;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class MainActivity extends Activity {
	// Progress Dialog Object
		ProgressDialog prgDialog;
		// cac bien du lieu
		String tk;
		TextView taiKhoan, matKhau, loiDN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nhaplai = (Button) findViewById(R.id.nhaplai);
        taiKhoan = (TextView) findViewById(R.id.taikhoan);
		matKhau = (TextView) findViewById(R.id.matkhau);
		loiDN = (TextView) findViewById(R.id.loidangnhap);

		ActionBar action = getActionBar();
		action.hide();
		// action.setBackgroundDrawable(new
		// ColorDrawable(Color.parseColor("#009688")));
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
		prgDialog.setMessage("Đang đăng nhập...");
		// Set Cancelable as False
		prgDialog.setCancelable(false);
		nhaplai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				taiKhoan.setText("");
				taiKhoan.requestFocus();
				matKhau.setText("");
			}
		});
    }
    public void loginUser(View view) {
		// lay du lieu nhap
		tk = taiKhoan.getText().toString();
		String mk = matKhau.getText().toString();
		RequestParams params = new RequestParams();
		// When Email Edit View and Password Edit View have values other than
		// Null
		if (isNotNull(tk) && isNotNull(mk)) {

			// Put Http parameter username with value of Email Edit View control
			params.put("TaiKhoan", tk);
			// Put Http parameter password with value of Password Edit Value
			// control
			params.put("MatKhau", mk);
			// Invoke RESTful Web Service with Http parameters
			invokeWS(params);

		}
		// When any of the Edit View control left blank
		else {
			Toast.makeText(getApplicationContext(),
					"Xin nhập thông tin, đừng để trống .", Toast.LENGTH_LONG)
					.show();
		}
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
				"http://192.168.0.100:8080/WebServiesQLSV/rest/SwSinhVien/checkLogin",
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
							if (obj.getBoolean("status")) {
								Toast.makeText(getApplicationContext(),
										"Đăng nhập thành công !",
										Toast.LENGTH_LONG).show();
								// Navigate to Home screen
								navigatetoHomeActivity();
							}
							// Else display error message
							else {
								loiDN.setText(obj.getString("error_msg"));
								Toast.makeText(getApplicationContext(),
										obj.getString("Lỗi khí đăng nhập ."),
										Toast.LENGTH_LONG).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(
									getApplicationContext(),
									"Lỗi đăng nhập!",
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
									"Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
    
    
    /**
	 * Method which navigates from Login Activity to Home Activity
	 */
	public void navigatetoHomeActivity() {
		Intent homeIntent = new Intent(getApplicationContext(),
				Fragment_Activity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		homeIntent.putExtra("MaSV", tk);
		startActivity(homeIntent);
		finish();
	}
    public static boolean isNotNull(String txt){
		return txt!=null && txt.trim().length()>0 ? true: false;
	}

}
