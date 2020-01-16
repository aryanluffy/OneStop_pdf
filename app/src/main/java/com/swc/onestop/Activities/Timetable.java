package com.swc.onestop.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.swc.onestop.MyDownloadManager;
import com.swc.onestop.R;

import java.io.File;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Timetable extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener, View.OnTouchListener {

    int roll, division = 0, labGroup = 0, tutGroup = 0;
    EditText et;
    SharedPreferences sp;
    Spinner SLab, Sbranch, STut;
    String[] tut = {"Select tut group", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17","T18","T19"};
    String[] lab = {"Select lab group", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "L10"};
    String[] branch = {"Select branch", "ME", "CE", "ECE", "EEE", "BT", "CSE", "EPH", "M&C", "CL", "CST", "DD"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(Timetable.this,Main2Activity.class));
                finish();
            }
        });


        et = (EditText) findViewById(R.id.etRoll);
        et.setOnTouchListener(this);

        sp = getSharedPreferences("rollcode", 0);
        if (sp.getString("roll", null) != null)
            et.setText(sp.getString("roll", null), TextView.BufferType.EDITABLE);

        Sbranch = (Spinner) findViewById(R.id.spinTTbranch);
        ArrayAdapter<String> adapterDiv = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, branch);
        Sbranch.setAdapter(adapterDiv);
        Sbranch.setOnItemSelectedListener(this);

        SLab = (Spinner) findViewById(R.id.spinTTLab);
        ArrayAdapter<String> adapterLab = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lab);
        SLab.setAdapter(adapterLab);
        SLab.setOnItemSelectedListener(this);

        STut = (Spinner) findViewById(R.id.spinTTtut);
        ArrayAdapter<String> adapterTut = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tut);
        STut.setAdapter(adapterTut);
        STut.setOnItemSelectedListener(this);

        Button submit = (Button) findViewById(R.id.bTTSubmit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        String s = et.getText().toString();

        //Input handling
        if (!s.equals("")) {
            try {
                roll = Integer.parseInt(s);
                if ((roll > 199999999) | (roll < 190000000)) {
                    Toast t = Toast.makeText(this, "Enter a valid FIRST year roll number", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("roll", s);
                    editor.commit();

                    Intent i = new Intent(Timetable.this,TTData.class);
                    i.putExtra("Lab", LabGroup(roll));
                    i.putExtra("Tut", TutGroup(roll));
                    i.putExtra("Division", Division(roll));
                    i.putExtra("roll", roll);
                    startActivity(i);
                }
            } catch (NumberFormatException nfe) {
                Toast t = Toast.makeText(this, "Error", Toast.LENGTH_SHORT);
                t.show();
            }
        } else {
            if ((division != 0) & (labGroup != 0) & (tutGroup != 0)) {
                Intent intent = new Intent(Timetable.this,TTData.class);
                intent.putExtra("Tut", tutGroup);
                intent.putExtra("Division", division);
                intent.putExtra("Lab", labGroup);
                startActivity(intent);
            } else {
                Toast t = Toast.makeText(this, "Fill the form completely", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    private int Division(int roll) {
        if (((roll >= 190103001) & (roll <= 190103105)) | ((roll >= 190104001) & (roll <= 190104101)))
            return 1;
        else if (((roll >= 190106001) & (roll <= 190106075)) | ((roll >= 190108001) & (roll <= 190108057)) | ((roll >= 190102001) & (roll <= 190102092)))
            return 2;
        else if (((roll >= 190101001) & (roll <= 190101101)) | ((roll >= 190205001) & (roll <= 190205056)) | ((roll >= 190123001) & (roll <= 190123064)))
            return 3;
        else if (((roll >= 190107001) & (roll <= 190107084)) | ((roll >= 190121001) & (roll <= 190121060)) | ((roll >= 190122001) & (roll <= 190122057)))
            return 4;
        else
            return 0;
    }

    private int TutGroup(int roll) {
        if ((roll >= 190101001) & (roll <= 190101045))
            return 1;
        else if (((roll >= 190101046) & (roll <= 190101090)) )
            return 2;
        else if ((roll >= 190101091) & (roll <= 190101101)  | ((roll >= 190102001) & (roll <= 190102034)))
            return 3;
        else if (((roll >= 190102035) & (roll <= 190102079)) )
            return 4;
        else if (((roll >= 190103001) & (roll <= 190103032)) | ((roll >= 190102080) & (roll <= 190102092)))
            return 5;
        else if ((roll >= 190103033) & (roll <= 190103077))
            return 6;
        else if (((roll >= 190103078) & (roll <= 190104017))  )
            return 7;
        else if ((roll >= 190104018) & (roll <= 190104062))
            return 8;
        else if (((roll >= 190104063) & (roll <= 190104101)) | ((roll >= 190106001) & (roll <= 190106006)))
            return 9;
        else if (((roll >= 190106007) & (roll <= 190106051)) )
            return 10;
        else if (((roll >= 190106052) & (roll <= 190106075))  | ((roll >= 190107001) & (roll <= 190107021)) )
            return 11;
        else if ((roll >= 190107022) & (roll <= 190107066) )
            return 12;
        else if (((roll >= 190107067) & (roll<= 190107084)) |(roll== 190108001) | (roll == 190108027))
            return 13;
        else if ((roll== 190108028) | (roll== 190108057)| ((roll>=190121001) & (roll <= 190121015)))
            return 14;
        else if ((roll== 190121016) | (roll== 190121060))
            return 15;
        else if ((roll== 190122001) | (roll== 190122045))
            return 16;
        else if ((roll== 190122046) | (roll== 190122057) | ((roll>=190123001) & (roll <= 190123033)))
            return 17;
        else if ((roll== 190123034) | (roll== 190205014) )
            return 18;
        else if ((roll== 190205015) | (roll== 190205056) )
            return 19;
        else
            return 0;
    }

    private int LabGroup(int roll) {
        if ((roll >= 190103001) & (roll <= 190103086))
            return 1;
        else if (((roll >= 190103087) & (roll <= 190103105)) | ((roll >= 190104001) & (roll <= 190104067)))
            return 2;
        else if (((roll >= 190104068) & (roll <= 190104101)) | ((roll >= 190102001) & (roll <= 190102052)) )
            return 3;
        else if (((roll >= 190102053) & (roll <= 190102092))  | ((roll >= 190106001) & (roll <= 190106046)) )
            return 4;
        else if (((roll >= 190106047) & (roll <= 190106075)) | ((roll >= 190108001) & (roll <= 190108057)))
            return 5;
        else if (((roll >= 190101001) & (roll <= 190101085)))
            return 6;
        else if (((roll >= 190101086) & (roll <= 190101101)) | ((roll >= 190123001) & (roll <= 190123064)) | ((roll >= 190205001) & (roll <= 190205005)) )
            return 7;
        else if (((roll >= 190205006) & (roll <= 190205056)) | ((roll >= 190107001) & (roll <= 190107034)))
            return 8;
        else if (((roll >= 190107035) & (roll <= 190107084)) | ((roll >= 190121001) & (roll <= 190121035)))
            return 9;
        else if (((roll >= 190121036) & (roll <= 190121060)) | ((roll >= 190122001) & (roll <= 190122057)))
            return 10;
        else
            return 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        switch (spinner.getId()) {
            case R.id.spinTTbranch:
                if ((i == 1) | (i == 2))
                    division = 1;
                else if ((i >= 3) & (i <= 5))
                    division = 2;
                else if ((i >= 6) & (i <= 8))
                    division = 3;
                else if ((i >= 9) & (i <= 11))
                    division = 4;
                if (i != 0)
                    et.setText("", TextView.BufferType.EDITABLE);
                break;
            case R.id.spinTTLab:
                labGroup = i;
                if (i != 0)
                    et.setText("", TextView.BufferType.EDITABLE);
                break;
            case R.id.spinTTtut:
                tutGroup = i;
                if (i != 0)
                    et.setText("", TextView.BufferType.EDITABLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Sbranch.setSelection(0);
        SLab.setSelection(0);
        STut.setSelection(0);
        return false;
    }
}