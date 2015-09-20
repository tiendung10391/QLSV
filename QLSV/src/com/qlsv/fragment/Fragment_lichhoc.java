package com.qlsv.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by kunph_000 on 08/09/2015.
 */
public class Fragment_lichhoc extends Fragment {
	TableLayout table_lich;
	String malop,ip;
	String prefname="my_data";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lich_hoc, container, false);
        malop = Fragment_Activity.malop.toString();
      //ip
      	ip = MainActivity.ip.toString();
        table_lich = (TableLayout) view.findViewById(R.id.tableLich);
//		BuildTable(4, 6);
		cacNgayHoc();
        return view;
    }
    public void cacNgayHoc() {
		// lay du lieu nhap

		RequestParams params = new RequestParams();
		// Put Http parameter MaSV
		params.put("MaLop", malop);
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
				ip+"/WebServiesQLSV/rest/SwAdLichHoc/getAllLichHoc_Ad",
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
								String ngay = obj.getString("ngay");
								String gioHoc = obj.getString("gioHoc");
								String monHoc = obj.getString("monHoc");
								String phong = obj.getString("phong");
								// do len table	
								TextView tv1 = new TextView(getActivity());
								TextView tv2 = new TextView(getActivity());
								TextView tv3 = new TextView(getActivity());
								TextView tv4 = new TextView(getActivity());
//								1
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
								
								tv1.setText(ngay);
								tv2.setText(gioHoc);
								tv3.setText(monHoc);
								tv4.setText(phong);

								row.addView(tv1);
								row.addView(tv2);
								row.addView(tv3);
								row.addView(tv4);

								
								//
								table_lich.addView(row);
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
									"Vui lòng kiểm tra kết nối mạng.!",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
