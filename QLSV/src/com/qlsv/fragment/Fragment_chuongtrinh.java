package com.qlsv.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsv.Fragment_Activity;
import com.example.qlsv.MainActivity;
import com.example.qlsv.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Fragment_chuongtrinh extends Fragment {
	TableLayout table_chuongtrinh;
	String mankhoahoc,malop,ip;
	String prefname="my_data";
	ArrayList<String> khoahoc = new ArrayList<String>();
	
	Spinner mySpinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.chuong_trinh, container, false);
		malop = Fragment_Activity.malop.toString();
		//ip
		ip = MainActivity.ip.toString();
		table_chuongtrinh = (TableLayout) view.findViewById(R.id.tableChuongtrinh);
//		BuildTable(4, 6);
		mySpinner = (Spinner) view.findViewById(R.id.spinChuongtrinh);		
		chuongtrinhspinner();
		return view;
    }
	private void chuongtrinhspinner() {
		// TODO Auto-generated method stub
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(ip + "/WebServiesQLSV/rest/swKhoaHoc/getAllKhoaHoc",
				new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http response code
					// '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						try {
							JSONArray jsArr = new JSONArray(response);
							khoahoc.clear();
							for (int i = 0; i < jsArr.length(); i++) {
								// JSON Object
								JSONObject obj = jsArr.getJSONObject(i);								
								khoahoc.add(obj.getString("maKhoaHoc"));
								// Locate the spinner in activity_main.xm
								// Spinner adapter
								mySpinner
										.setAdapter(new ArrayAdapter<String>(
												getActivity(),
												android.R.layout.simple_spinner_dropdown_item,
												khoahoc));
								// Spinner on item click listener
								mySpinner
										.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
											@Override
											public void onItemSelected(
													AdapterView<?> arg0,
													View arg1, int position,
													long arg3) {
												// TODO Auto-generated method
												mankhoahoc = arg0.getItemAtPosition(
														position).toString();
												cacMon();
												int count = table_chuongtrinh
														.getChildCount();
												for (int j = 1; j < count; j++) {
													TableRow row = (TableRow) table_chuongtrinh
															.getChildAt(j);
													table_chuongtrinh.removeView(row);
												}

											}

											@Override
											public void onNothingSelected(
													AdapterView<?> arg0) {
												// TODO Auto-generated method
												// stub
											}
										});
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getActivity(), "Lỗi tải dữ liệu!",
									Toast.LENGTH_LONG).show();
							e.printStackTrace();

						}
					}

					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						// Hide Progress Dialog

						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getActivity(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getActivity(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(getActivity(),
									"Kiểm tra server.!",
									Toast.LENGTH_LONG).show();
						}
					}

				});
		
	}	
	/**
	 * Method that performs RESTful webservice invocations
	 * 
	 * @param params
	 */
	public void cacMon() {
		RequestParams params = new RequestParams();
		// Put Http parameter MaSV
		params.put("MaKhoaHoc", mankhoahoc);		
		invokeWS(params);
	}
	
	public void invokeWS(RequestParams params) {
		// Show Progress Dialog
		
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(
				ip+"/WebServiesQLSV/rest/SwAdChuongTrinh/getAllMonHoc_Ad",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http response code
					// '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						
						try {
							JSONArray jsArr = new JSONArray(response);
							for (int i = 0; i <= jsArr.length(); i++) {
								TableRow row = new TableRow(getActivity());
								row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
										LayoutParams.WRAP_CONTENT));
								// lay du lieu tu json
								JSONObject obj = jsArr.getJSONObject(i);
								String mamonhoc = obj.getString("maMonHoc");
								String tenmonhoc = obj.getString("tenMonHoc");
								String sogio = obj.getString("soGio");
								String ghichu = obj.getString("ghiChu");
								// do len table	
								TextView tv = new TextView(getActivity());
								TextView tv1 = new TextView(getActivity());
								TextView tv2 = new TextView(getActivity());
								TextView tv3 = new TextView(getActivity());
								TextView tv4 = new TextView(getActivity());
//								1
								tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv.setBackgroundResource(R.drawable.cell_shape);
								tv.setPadding(5, 5, 5, 5);
								
								tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv1.setBackgroundResource(R.drawable.cell_shape);
								tv1.setPadding(5, 5, 5, 5);
								//2
								tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv2.setBackgroundResource(R.drawable.cell_shape);
								tv2.setPadding(5, 5, 5, 5);
								//3
								tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv3.setBackgroundResource(R.drawable.cell_shape);
								tv3.setPadding(5, 5, 5, 5);
								//4
								tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv4.setBackgroundResource(R.drawable.cell_shape);
								tv4.setPadding(5, 5, 5, 5);
								
								tv.setText("" +(i+1));
								tv1.setText(mamonhoc);
								tv2.setText(tenmonhoc);
								tv3.setText(sogio);
								tv4.setText(ghichu);

//								row.addView(tv);
								row.addView(tv1);
								row.addView(tv2);
								row.addView(tv3);
								row.addView(tv4);

								
								//
								table_chuongtrinh.addView(row);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
//							Toast.makeText(getActivity(), "",
//									Toast.LENGTH_LONG).show();
							e.printStackTrace();

						}
					}

					// When the response returned by REST has Http response code
					// other than '200'
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						// Hide Progress Dialog
						
						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getActivity(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getActivity(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(
									getActivity(),
									"Kiểm tra server.!",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
