package com.swc.onestop.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.swc.onestop.R;

public class TTData extends Activity implements AdapterView.OnItemSelectedListener {

    CardView Tut, ML1, ML2, ML3 ,ML4, MLab, AL1, AL2, AL3, ALab;
    TextView tvTut, tvTutV, tvML1, tvML1V, tvML2, tvML2V, tvML3 ,tvML4, tvML3V, tvML4V, tvMLab, tvMLabV, tvAL1, tvAL1V, tvAL2, tvAL2V, tvAL3, tvAL3V, tvALab, tvALabV;
    Spinner spinner;
    int tutGroup, division, labGroup;
    int day=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttdata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(TTData.this,Main2Activity.class));
                finish();
            }
        });

        toolbar.setTitle("TimeTable");

        initialize();
        //add roll number to title bar
        int roll = getIntent().getIntExtra("roll", 0);
        tutGroup = getIntent().getIntExtra("Tut", 0);
        division = getIntent().getIntExtra("Division", 0);
        labGroup = getIntent().getIntExtra("Lab", 0);
        if (roll > 190101000)
            toolbar.setTitle(""+roll);
        else
           toolbar.setTitle("TimeTable");
        setData();
    }

    private void initialize() {
        Tut = (CardView) findViewById(R.id.TTtut);
        ML1 = (CardView) findViewById(R.id.TTML1);
        ML2 = (CardView) findViewById(R.id.TTML2);
        ML3 = (CardView) findViewById(R.id.TTML3);
        ML4 = (CardView) findViewById(R.id.TTML4);
        MLab = (CardView) findViewById(R.id.TTMLab);
        AL1 = (CardView) findViewById(R.id.TTAL1);
        AL2 = (CardView) findViewById(R.id.TTAL2);
        AL3 = (CardView) findViewById(R.id.TTAL3);
        ALab = (CardView) findViewById(R.id.TTALab);

        tvTut = (TextView) findViewById(R.id.tvTTtut);
        tvTutV = (TextView) findViewById(R.id.tvTTtutV);
        tvML1 = (TextView) findViewById(R.id.tvTTML1);
        tvML2 = (TextView) findViewById(R.id.tvTTML2);
        tvML3 = (TextView) findViewById(R.id.tvTTML3);
        tvML4 = (TextView) findViewById(R.id.tvTTML4);
        tvMLab = (TextView) findViewById(R.id.tvTTMLab);
        tvML1V = (TextView) findViewById(R.id.tvTTMLV1);
        tvML2V = (TextView) findViewById(R.id.tvTTMLV2);
        tvML3V = (TextView) findViewById(R.id.tvTTMLV3);
        tvML4V = (TextView) findViewById(R.id.tvTTMLV4);
        tvMLabV = (TextView) findViewById(R.id.tvTTMLabV);
        tvAL1 = (TextView) findViewById(R.id.tvTTAL1);
        tvAL2 = (TextView) findViewById(R.id.tvTTAL2);
        tvAL3 = (TextView) findViewById(R.id.tvTTAL3);
        tvALab = (TextView) findViewById(R.id.tvTTALab);
        tvAL1V = (TextView) findViewById(R.id.tvTTALV1);
        tvAL2V = (TextView) findViewById(R.id.tvTTALV2);
        tvAL3V = (TextView) findViewById(R.id.tvTTALV3);
        tvALabV = (TextView) findViewById(R.id.tvTTALabV);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        spinner = (Spinner) findViewById(R.id.spinTT);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void setData() {
        setTut();
        setLectures();
        setLab();

    }

    //Div I,II morning classes, Div III,IV afternoon classes
    private void setLectures() {
        //    String[] Lectures = {"Modern Biology (BT101)", "Maths (MA102)", "Engineering Mechanics (ME101)", "Introduction to Computing (CS101)", "Physics (PH102)"};
        //   String[] Venues = {"", "Venue: Lecture Hall 1", "Venue: Lecture Hall 2", "Venue: Lecture Hall 1", "Venue: Lecture Hall 2"};
        // String[] Lectures = {" Mathematics (MA101)", "  Electrical Science (EE101)", "Engineering Drawing (ME111)", "Physics (PH101) ", "Chemistry (CH101)"};

        String[] Lectures = {"Physics (PH101)", "Electrical Science (EE101)", "Mathematics (MA101)", "Engineering Drawing (CE 101)", "Chemistry (CH101)"};
        String[] Venues = {"", "Venue: Lecture Hall 2", "Venue: Lecture Hall 3", "Venue: Lecture Hall 2", "Venue: Lecture Hall 3"};
        if ((division == 3) | (division == 4)) {
            AL1.setVisibility(View.VISIBLE);
            AL2.setVisibility(View.VISIBLE);
            AL3.setVisibility(View.VISIBLE);
            tvAL1.setText(Lectures[(5 + day) % 5]);
            tvAL2.setText(Lectures[(6 + day) % 5]);
            tvAL3.setText(Lectures[(7 + day) % 5]);
            tvAL1V.setText(Venues[division]);
            tvAL2V.setText(Venues[division]);
            tvAL3V.setText(Venues[division]);
        } else if ((division == 1) | (division == 2)) {
            ML1.setVisibility(View.VISIBLE);
            ML2.setVisibility(View.VISIBLE);
            ML3.setVisibility(View.VISIBLE);
            tvML1.setText(Lectures[(7 + day) % 5]);
            tvML2.setText(Lectures[(6 + day) % 5]);
            tvML3.setText(Lectures[(5 + day) % 5]);
            tvML1V.setText(Venues[division]);
            tvML2V.setText(Venues[division]);
            tvML3V.setText(Venues[division]);
        }
    }

    private void setLab() {
        String[] Labs = {"Lab: Chemistry (CH110)", "Lab: Mechanical Workshop (ME110)", "Lab: Physics (PH110)", "Lab: Engineering Drawing (CE 101)"};
        String[] LabVenues = {"Department of Chemistry", "Workshop", "Department of Physics", "1203 and 1204, Core-1"};
        //    String[] Labs = {"Lab: Computing Laboratory (CS110)", "Lab: Mechanical Workshop (ME110)", "Lab: Basic Electronics Laboratory (EC102)", "Lab: Physics (PH110)"};
        //    String[] LabVenues = {"CC", "Workshop", "Department of Physics", "Department of EEE"};

        if ((division == 3) | (division == 4)) {
            //Morning Labs
            //Checking for (day and group) or etc. for the three labs
            if (((day == 0) & (labGroup == 6)) | ((day == 1) & (labGroup == 9)) | ((day == 2) & (labGroup == 7)) | ((day == 3) & (labGroup == 10)) | ((day == 4) & (labGroup == 8))) {
                MLab.setVisibility(View.VISIBLE);
                tvMLab.setText(Labs[0]);
                tvMLabV.setText(LabVenues[0]);
            } else if (((day == 0) & (labGroup == 7)) | ((day == 1) & (labGroup == 10)) | ((day == 2) & (labGroup == 8)) | ((day == 3) & (labGroup == 6)) | ((day == 4) & (labGroup == 9))) {
                MLab.setVisibility(View.VISIBLE);
                tvMLab.setText(Labs[2]);
                tvMLabV.setText(LabVenues[2]);
            } else if (((day == 0) & (labGroup == 8)) | ((day == 1) & (labGroup == 6)) | ((day == 2) & (labGroup == 9)) | ((day == 3) & (labGroup == 7)) | ((day == 4) & (labGroup == 10))) {
                MLab.setVisibility(View.VISIBLE);
                tvMLab.setText(Labs[3]);
                tvMLabV.setText(LabVenues[3]);
            } else {
                MLab.setVisibility(View.GONE);
            }

        } else if ((division == 1) | (division == 2)) {
            //Afternoon Labs
            if (((day == 0) & (labGroup == 1)) | ((day == 1) & (labGroup == 4)) | ((day == 2) & (labGroup == 2)) | ((day == 3) & (labGroup == 5)) | ((day == 4) & (labGroup == 3))) {
                ALab.setVisibility(View.VISIBLE);
                tvALab.setText(Labs[0]);
                tvALabV.setText(LabVenues[0]);
            } else if (((day == 0) & (labGroup == 2)) | ((day == 1) & (labGroup == 5)) | ((day == 2) & (labGroup == 3)) | ((day == 3) & (labGroup == 1)) | ((day == 4) & (labGroup == 4))) {
                ALab.setVisibility(View.VISIBLE);
                tvALab.setText(Labs[1]);
                tvALabV.setText(LabVenues[1]);
            } else if (((day == 0) & (labGroup == 3)) | ((day == 1) & (labGroup == 1)) | ((day == 2) & (labGroup == 4)) | ((day == 3) & (labGroup == 2)) | ((day == 4) & (labGroup == 5))) {
                ALab.setVisibility(View.VISIBLE);
                tvALab.setText(Labs[3]);
                tvALabV.setText(LabVenues[3]);
            } else {
                ALab.setVisibility(View.GONE);
            }
        }
    }

    private void setTut() {
        switch (day) {
            case 3:
                tvTut.setText("Tutorial: Electrical Science (EE101)");
                Tut.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvTut.setText("Tutorial: Physics (PH101)");
                Tut.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvTut.setText("Tutorial: Chemistry(CH101)");
                Tut.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvTut.setText("Tutorial: Maths (MA101)");
                Tut.setVisibility(View.VISIBLE);
                break;
            default:
                Tut.setVisibility(View.GONE);
                break;
        }

       // String[] TutVenues = {"", "Lecture Hall 1", "Lecture Hall 2", "Lecture Hall 3", "Lecture Hall 4", "1G1", "1G2", "1006", "1102", "1207", "2002", "2101" , "2102", "2203","4G3","4005","3202"};
       // tvTutV.setText("Venue: " + TutVenues[tutGroup]);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        day = i;
        setData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        day = 0;
    }

}
