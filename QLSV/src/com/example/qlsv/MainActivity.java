package com.example.qlsv;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {
	// Progress Dialog Object
	ProgressDialog prgDialog;
	// cac bien du lieu
	String tk, malop;
	EditText taiKhoan, matKhau;
	TextView loiDN;
	CheckBox chksave;
	String prefname = "my_data";
	public static String ip = "http://192.168.100.25:8080";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button nhaplai = (Button) findViewById(R.id.nhaplai);
		Button dangnhap = (Button) findViewById(R.id.dangnhap);
		taiKhoan = (EditText) findViewById(R.id.taikhoan);
		matKhau = (EditText) findViewById(R.id.matkhau);
		loiDN = (TextView) findViewById(R.id.loidangnhap);
		chksave = (CheckBox) findViewById(R.id.checkDN);

		ActionBar action = getActionBar();
		action.hide();
		// action.setBackgroundDrawable(new
		// ColorDrawable(Color.parseColor("#009688")));
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
		prgDialog.setMessage("Đang đăng nhập...");
		// Set Cancelable as False
		prgDialog.setCancelable(false);
		// onclick
		dangnhap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginUser();
			}
		});
		nhaplai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				taiKhoan.setText("");
				taiKhoan.requestFocus();
				matKhau.setText("");
				loiDN.setText("");
			}
		});
	}

	public void loginUser() {
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
		client.get(ip+"/WebServiesQLSV/rest/SwSinhVien/checkLogin",
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
								getMalop();								
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// gọi hàm lưu trạng thái ở đây
		savingPreferences();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// gọi hàm đọc trạng thái ở đây
		restoringPreferences();
	}

	/**
	 * hàm lưu trạng thái
	 */
	public void savingPreferences() {
		// tạo đối tượng getSharedPreferences
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		// tạo đối tượng Editor để lưu thay đổi
		SharedPreferences.Editor editor = pre.edit();
		String user = taiKhoan.getText().toString();
		String pwd = matKhau.getText().toString();
		boolean bchk = chksave.isChecked();
		if (!bchk) {
			// xóa mọi lưu trữ trước đó
			editor.clear();
		} else {
			// lưu vào editor
			editor.putString("user", user);
			editor.putString("pwd", pwd);
			editor.putBoolean("checked", bchk);
		}
		// chấp nhận lưu xuống file
		editor.commit();
	}

	/**
	 * hàm đọc trạng thái đã lưu trước đó
	 */
	public void restoringPreferences() {
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		// lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
		boolean bchk = pre.getBoolean("checked", false);
		if (bchk) {
			// lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
			String user = pre.getString("user", "");
			String pwd = pre.getString("pwd", "");
			taiKhoan.setText(user);
			matKhau.setText(pwd);
		}
		chksave.setChecked(bchk);
		loginUser();
	}

	/**
	 * Method which navigates from Login Activity to Home Activity
	 */
	public void navigatetoHomeActivity() {
		Intent homeIntent = new Intent(getApplicationContext(),
				Fragment_Activity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		homeIntent.putExtra("MaSV", tk);
		homeIntent.putExtra("MaLop", malop);
		startActivity(homeIntent);
		finish();
	}

	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() > 0 ? true : false;
	}

	public void getMalop() {
		tk = taiKhoan.getText().toString();
		// lay du lieu nhap
		RequestParams params1 = new RequestParams();
		if (isNotNull(tk)) {
			params1.put("MaSV", tk);
			invokeWService(params1);
		}

	}

	public void invokeWService(RequestParams params) {
		// Show Progress Dialog
		prgDialog.show();
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client1 = new AsyncHttpClient();
		client1.get(
				ip+"/WebServiesQLSV/rest/SwSinhVien/checkSinhvien",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http response code
					// '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						try {
							// JSON Object
							JSONObject obj = new JSONObject(response);
							malop = obj.getString("MaLop");
							// tạo đối tượng getSharedPreferences
							navigatetoHomeActivity();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(),
									"Lỗi tải dữ liệu!", Toast.LENGTH_LONG)
									.show();
							e.printStackTrace();

						}
					}

					// When the response returned by REST has Http response code
					// other than '200'
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
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
									"Vui lòng kiểm tra kết nối mạng.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}

}
