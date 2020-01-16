package com.swc.onestop.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.swc.onestop.Activities.Main2Activity;
import com.swc.onestop.R;
import com.swc.onestop.TabFragments.SectionsPageAdapter;
import com.swc.onestop.TabFragments.Tab1Fragment;
import com.swc.onestop.TabFragments.Tab2Fragment;
import com.swc.onestop.TabFragments.Tab3Fragment;

public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "ContactActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(ContactActivity.this,Main2Activity.class));
            }
        });
        Log.d(TAG, "onCreate: Starting.");
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},1);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        NavigationTabStrip tabLayout = (NavigationTabStrip) findViewById(R.id.tabs);
        tabLayout.setViewPager(mViewPager);


        //gymkhana

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "TAB1");
        adapter.addFragment(new Tab2Fragment(), "TAB2");
        adapter.addFragment(new Tab3Fragment(), "TAB3");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ContactActivity.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }

}
