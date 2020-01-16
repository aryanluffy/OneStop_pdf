package com.swc.onestop.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.swc.onestop.Internet_settings.BrowSet;
import com.swc.onestop.Internet_settings.DCSet;
import com.swc.onestop.Internet_settings.IPSettings;
import com.swc.onestop.Internet_settings.LanSet;
import com.swc.onestop.Internet_settings.SkypeSet;
import com.swc.onestop.R;

public class Internet_settings extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listview;
    String[] classes = { "lansettings", "browsettings", "skypesettings",
            "dcsettings", "ipsettings" };
    Intent j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(Internet_settings.this,Main2Activity.class));
                finish();
            }
        });

        listview = (ListView) findViewById(R.id.lvINTERNET);
        String[] items = { "LAN settings for PC","DC++ Settings", "Hostel IP calculator" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        switch (arg2){
            case 0:
                j = new Intent(Internet_settings.this,LanSet.class);
                break;
            case 1:
                j = new Intent(Internet_settings.this,DCSet.class);
                break;
            case 2:
                j = new Intent(Internet_settings.this,IPSettings.class);
                break;

        }

        startActivity(j);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Internet_settings.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }

}
