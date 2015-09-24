package com.example.qlsv;

import static com.example.qlsv.CommonUtilities.SENDER_ID;
import static com.example.qlsv.CommonUtilities.SERVER_URL;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qlsv.R;

public class RegisterActivity extends Activity {
	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Internet detector
	ConnectionDetector cd;
	
	// UI elements
	EditText txtName;
	EditText txtMasv,txtMalop;
	
	// Register button
	Button btnRegister;
	String masv,malop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gcm);
		
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}
		
		txtName = (EditText) findViewById(R.id.txtName);
		txtMasv = (EditText) findViewById(R.id.txtMasv);
		txtMalop = (EditText) findViewById(R.id.txtMalop);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		masv = Fragment_Activity.masv.toString();
		malop = Fragment_Activity.malop.toString();
		txtMasv.setText(masv);
		txtMalop.setText(malop);
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String name = txtName.getText().toString();
				String masv = txtMasv.getText().toString();
				String malop = txtMalop.getText().toString();
				// Check if user filled the form
				if(name.trim().length() > 0 && masv.trim().length() > 0){
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), MainActivityGcm.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("name", name);
					i.putExtra("masv", masv);
					i.putExtra("malop", malop);
					startActivity(i);
					finish();
				}else{
					// user doen't filled that data
					// ask him to fill the form
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}

}
