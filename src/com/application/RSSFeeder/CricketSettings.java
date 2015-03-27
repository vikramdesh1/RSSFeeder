package com.application.RSSFeeder;

import android.app.Activity;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class CricketSettings extends Activity {
	
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	EditText ed;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		 prefs = getSharedPreferences("rssprefs", 0);
	      
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		ed=(EditText)findViewById(R.id.editText1);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(listener);
		ed.setText(prefs.getString("cricketurl",""));
	}
	private OnClickListener listener = new OnClickListener() {
	    public void onClick(View v) {
	    	editor = prefs.edit();
	    	editor.putString("cricketurl",ed.getText().toString());
	    	editor.commit();
	    Toast.makeText(getApplicationContext(), "Feed address saved", Toast.LENGTH_SHORT).show();
	    finish();
	    }
	    
	};
}
 