package com.swc.onestop.Internet_settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.swc.onestop.Activities.Main2Activity;
import com.swc.onestop.R;

public class SkypeSet extends Activity {

	ImageView skype1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.skypeset);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// back button pressed
				startActivity(new Intent(SkypeSet.this,Main2Activity.class));
				finish();
			}
		});
		
	}

}
