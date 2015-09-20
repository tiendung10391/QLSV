package com.qlsv.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsv.Fragment_Activity;
import com.example.qlsv.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by kunph_000 on 08/09/2015.
 */
public class Fragment_diemsv extends Fragment {
	TableLayout table_diem;
	
	String masvien;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.diem_sinhvien, container, false);
		masvien = Fragment_Activity.masv.toString();
		table_diem = (TableLayout) view.findViewById(R.id.tableDiem);
//		BuildTable(4, 6);
		diemSinhvien();
	
		return view;
	}

	public void diemSinhvien() {
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
		
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(
				"http://192.168.0.100:8080/WebServiesQLSV/rest/SwDiem/getAllDiem",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http response code
					// '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog
						
						try {
							JSONArray jsArr = new JSONArray(response);
							for (int i = 1; i <= jsArr.length(); i++) {
								TableRow row = new TableRow(getActivity());
								row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
										LayoutParams.WRAP_CONTENT));
								// lay du lieu tu json
								JSONObject obj = jsArr.getJSONObject(i);
								String mamonhoc = obj.getString("maMonHoc");
								String diemlan1 = obj.getString("diemLan1");
								String diemlan2 = obj.getString("diemlan2");
								String diemlan3 = obj.getString("diemLan3");
//								Boolean trangthai = obj.getBoolean("trangThai");
								// do len table	
								TextView tv = new TextView(getActivity());
								TextView tv2 = new TextView(getActivity());
								TextView tv3 = new TextView(getActivity());
								TextView tv4 = new TextView(getActivity());
								TextView tv5 = new TextView(getActivity());
//								1
								tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv.setBackgroundResource(R.drawable.cell_shape);
								tv.setPadding(5, 5, 5, 5);
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
								//5
								tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
										LayoutParams.MATCH_PARENT));
								tv5.setBackgroundResource(R.drawable.cell_shape);
								tv5.setPadding(5, 5, 5, 5);
								
								tv.setText(mamonhoc);
								tv2.setText(diemlan1);
								tv3.setText(diemlan2);
								tv4.setText(diemlan3);
//								if (trangthai) {
//									tv5.setText("Đạt");
//								} else {
//									tv5.setText("Chưa đạt");
//								}

								row.addView(tv);
								row.addView(tv2);
								row.addView(tv3);
								row.addView(tv4);
//								row.addView(tv5);

								
								Log.i("monhoc", mamonhoc);
								Log.i("diemlan1", diemlan1);
								//
								table_diem.addView(row);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Toast.makeText(getActivity(), "Hoàn tất tải dữ liệu!",
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
									"Vui lòng kiểm tra kết nối mạng.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
