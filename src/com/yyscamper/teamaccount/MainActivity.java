package com.yyscamper.teamaccount;


//import com.baidu.frontia.Frontia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class MainActivity extends Activity implements OnClickListener {
    
	private static final int RequestCodeSelectAttendPersons = 1;
	
	//private String _apiKey = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PayManager.LoadPersons();
		
		//PayManager.LoadPayEntries();
		
		setContentView(R.layout.activity_main);
		//Frontia.init(this.getApplicationContext(), _apiKey);

        setupViews();
	}

	private void setupViews() {
		
		String[] allPersonNames = PayManager.GetAllPersonNames();
		Spinner spinSelectPayer = (Spinner)findViewById(R.id.spinnerSleectPayer);
		ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, allPersonNames);
		spinSelectPayer.setAdapter(spinAdapter);
		
		Button btnSelectAttPersons = (Button)findViewById(R.id.btnSelectAttendPersons);
		btnSelectAttPersons.setOnClickListener(this);
		
		((Button)findViewById(R.id.btnSavePayEntry)).setOnClickListener(this);
		
		/*Button btnSelectPayer = (Button)this.findViewById(R.id.btnSelectAttendPersons);
		btnSelectPayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(null, SelectPersonActivity.class);
				Bundle bundle = new Bundle();
				bundle.putBoolean("multi_select", false);
				it.putExtras(bundle);
				startActivity(it);
			}
			
		});
		*/
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSelectAttendPersons:
			Intent intent = new Intent(MainActivity.this, SelectPersonActivity.class);
			startActivityForResult(intent, RequestCodeSelectAttendPersons);
			break;
		case R.id.btnSavePayEntry:
			
			break;
		default:
		}
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RequestCodeSelectAttendPersons == requestCode) {
			if (resultCode != Activity.RESULT_OK)
				return;
			String[] selPersons = data.getStringArrayExtra("select_persons");
			StringBuffer sb = new StringBuffer();
			for (String str : selPersons) {
				sb.append(str);
				sb.append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			
			EditText selText = (EditText)findViewById(R.id.editTextAttendPersons);
			selText.setText(sb.toString());
		}
	}

}
