package com.swc.onestop.Timings_activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.swc.onestop.Activities.Timings_activity;
import com.swc.onestop.R;


import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Ferry extends AppCompatActivity {
    TextView title;
    String day;
    private ExpandingList mExpandingList;
    ArrayList<String> NGtoG_Sunday = new ArrayList<String>();
    ArrayList<String> GtoGN_Sunday = new ArrayList<String>();
    ArrayList<String> NGtoG_Workingdays = new ArrayList<String>();
    ArrayList<String> GtoNG_Workingdays = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferry);
        mExpandingList = findViewById(R.id.expanding_list_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.back_btn_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(Ferry.this,Timings_activity.class));
            }
        });
        title = (TextView) findViewById(R.id.ferry_tv);
        final NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday","Saturday"));
        niceSpinner.attachDataSource(dataset);

        if (!isOnline())
            showDialogBOX(Ferry.this);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("FerryTimings").document("Timings");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 if (documentSnapshot.exists()) {

                     NGtoG_Sunday = (ArrayList) documentSnapshot.get("NGtoG_sunday");
                     GtoNG_Workingdays = (ArrayList) documentSnapshot.get("GtoNg_Workingdays");
                     GtoGN_Sunday = (ArrayList) documentSnapshot.get("GtoNG_Sunday");
                     NGtoG_Workingdays = (ArrayList) documentSnapshot.get("NGtoG_workingdays");
                     Log.i("Ferry",NGtoG_Sunday.toString());
                     Log.e("Ferry",NGtoG_Workingdays.get(0));
                     Log.e("Ferry",GtoGN_Sunday.get(0));
                     Log.e("Ferry",GtoNG_Workingdays.get(0));

                     Calendar calendar = Calendar.getInstance();
                     int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
                     switch(currentDay) {
                         case Calendar.MONDAY:
                             niceSpinner.setSelectedIndex(1);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Monday");
                             break;
                         case Calendar.TUESDAY:
                             niceSpinner.setSelectedIndex(2);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Tuesday");
                             break;
                         case Calendar.WEDNESDAY:
                             niceSpinner.setSelectedIndex(3);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Wednesday");
                             break;
                         case Calendar.THURSDAY:
                             niceSpinner.setSelectedIndex(4);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Thursday");
                             break;
                         case Calendar.FRIDAY:
                             niceSpinner.setSelectedIndex(5);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Friday");
                             break;
                         case Calendar.SATURDAY:
                             niceSpinner.setSelectedIndex(6);
                             createItemsforweekdays();
                             title.setText("Ferry Timings : Saturday");
                             break;
                         case Calendar.SUNDAY:
                             niceSpinner.setSelectedIndex(0);
                             createItemsforSunday();
                             title.setText("Ferry Timings : Sunday");
                             break;
                     }



                 }
             }
         });
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        day="Sunday";break;
                    case 1:
                        day="Monday";break;
                    case 2:
                        day="Tuesday";break;
                    case 3:
                        day="Wednesday";break;
                    case 4:
                        day="Thursday";break;
                    case 5:
                        day="Friday";break;
                    case 6:
                        day="Saturday";break;


                }
                title.setText("Ferry Timings : "+day);
                if(position==0){

                    mExpandingList.removeAllViews();
                    createItemsforSunday();
                }
                else {
                    mExpandingList.removeAllViews();
                    createItemsforweekdays();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            Log.i("Internet" ,"Online");
            return true;
        }
        Log.i("Internet" ,"Offline");
        return false;
    }
    private void showDialogBOX(Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.newcustom_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button retry = dialog.findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (!isOnline()){

                    dialog.show();
                }
            }
        });
        Button ok = dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Ferry.this, "Connect to the Internet .", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


        dialog.show();
    }

    private void createItemsforweekdays() {
        addItem("Guwahati to North Guwahati",GtoNG_Workingdays.toArray(new String[0]), R.color.yellow, R.drawable.ic_home);
        addItem("North Guwahati to Guwahati",NGtoG_Workingdays.toArray(new String[0]), R.color.yellow, R.drawable.ic_location_city);

    }
    private void createItemsforSunday() {
        addItem("Guwahati to North Guwahati", GtoGN_Sunday.toArray(new String[0]), R.color.yellow, R.drawable.ic_home);
        addItem("North Guwahati to Guwahati",NGtoG_Sunday.toArray(new String[0]), R.color.yellow, R.drawable.ic_location_city);

    }

    private void addItem(String title, String[] subItems, int colorRes, int iconRes) {
        //Let's create an item with R.layout.expanding_layout

        final ExpandingItem item = mExpandingList.createNewItem(R.layout.ferry_expanding_layout);

        //If item creation is successful, let's configure it
        if (item != null) {
            item.setIndicatorColorRes(colorRes);
            item.setIndicatorIconRes(iconRes);
            //It is possible to get any view inside the inflated layout. Let's set the text in the item
            ((TextView) item.findViewById(R.id.title)).setText(title);

            //We can create items in batch.
            item.createSubItems(subItems.length);
            for (int i = 0; i < item.getSubItemsCount(); i++) {
                //Let's get the created sub item by its index
                final View view = item.getSubItemView(i);

                //Let's set some values in
                configureSubItem(item, view, subItems[i]);
            }
        }
    }
    private void configureSubItem(final ExpandingItem item, final View view, String subTitle) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subTitle);
        view.findViewById(R.id.remove_sub_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.removeSubItem(view);
            }
        });
    }
    private void showInsertDialog(final Internal_bus.OnItemCreated positive) {
        final EditText text = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(text);
        builder.setTitle(R.string.enter_title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positive.itemCreated(text.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }
    interface OnItemCreated {
        void itemCreated(String title);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Ferry.this, Timings_activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }
}