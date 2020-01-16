package com.swc.onestop.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.swc.onestop.MyDownloadManager;
import com.swc.onestop.R;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Mess_Menu extends AppCompatActivity implements OnLoadCompleteListener,OnPageChangeListener {


    String dir;
    private  String menuMonth;
    PDFView pdfview;
    SharedPreferences sharedPreferences,BARAK, BRAHMAPUTRA, DHANSIRI, DIBANG, DIHING,KAMENG,KAPILI,LOHIT, MANAS, SIANG, SUBHANSIRI,UMIAM;
    String BARAK_MONTH, BRAHMAPUTRA_MONTH, DHANSIRI_MONTH, DIBANG_MONTH, DIHING_MONTH,KAMENG_MONTH,KAPILI_MONTH,LOHIT_MONTH, MANAS_MONTH, SIANG_MONTH, SUBHANSIRI_MONTH,UMIAM_MONTH;
    SharedPreferences.Editor editor;
    ProgressDialog progressdialog;
    String URL;
    private SessionManager sessionManager;
    private HashMap<String, String> userDetails;
    private String hostel,defaulthostel;
    private DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mess__menu);
        pdfview = (PDFView) findViewById(R.id.pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(Mess_Menu.this,Main2Activity.class));
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        checkmonth();
        check_hostel_month();


//        editor.putString("shivam","kumar");
//        editor.commit();
        progressdialog = new ProgressDialog(Mess_Menu.this);
        progressdialog.setMessage("Menu Downloading....");

//        sharedPreferences.getString("shivam","extra string if not shivam");
        dir =Environment.getExternalStorageDirectory().toString()+"/OneStop/";
//        FirebaseFirestore.getInstance().collection("messmenu").document("updatemenu").addSnapshotListener(new EventListener<QuerySnapshot>() {
//        final String defaulthostel ;
        try {
            sessionManager = new SessionManager(getApplicationContext());
            userDetails = sessionManager.getUserDetails();
            hostel=userDetails.get("hostel");
            defaulthostel=hostel.toUpperCase();
        }
        catch (Exception e){
            defaulthostel="BARAK";
        }

        Log.i("shivam",defaulthostel);
        if(isOnline())
        getUrlandLoadPDF(defaulthostel);
        else
        showDialog(Mess_Menu.this);

        final NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("--Select--","BARAK", "BRAHMAPUTRA", "DHANSIRI", "DIBANG", "DIHING","KAMENG","KAPILI","LOHIT", "MANAS", "SIANG", "SUBHANSIRI","UMIAM"));
        niceSpinner.attachDataSource(dataset);

        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        getUrlandLoadPDF(defaulthostel);
                        break;
                    case 1:
                        getUrlandLoadPDF("BARAK");
                        break;
                    case 2:
                       getUrlandLoadPDF("BRAHMAPUTRA");
                        

                        break;
                    case 3:
                        getUrlandLoadPDF("DHANSIRI");
                        

                        break;
                    case 4:
                        getUrlandLoadPDF("DIBANG");

                        break;
                    case 5:
                        getUrlandLoadPDF("DIHING");

                        break;
                    case 6:
                        getUrlandLoadPDF("KAMENG");

                        break;
                    case 7:
                        getUrlandLoadPDF("KAPILI");

                        break;
                    case 8:
                        getUrlandLoadPDF("LOHIT");

                        break;
                    case 9:
                        getUrlandLoadPDF("MANAS");

                        break;
                    case 10:
                        getUrlandLoadPDF("SIANG");

                        break;
                    case 11:
                        getUrlandLoadPDF("SUBHANSIRI");

                        break;
                    case 12:
                        getUrlandLoadPDF("UMIAM");

                        break;
                        
                    default:
                      //  url="no match";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



                






    }



    public void MessUpdated(){

        File del = new File(dir);
        if (del.isDirectory())
        {
            String[] children = del.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(del, children[i]).delete();
            }
        }
    }
    public void updateSharedPreference(){

        sharedPreferences = getApplicationContext().getSharedPreferences("Shivam", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("month",menuMonth);

        editor.commit();
        MessUpdated();


    }
    public void updateHostelMonth(String hostel) {

        BARAK = getApplicationContext().getSharedPreferences("BARAK", 0);
        BRAHMAPUTRA = getApplicationContext().getSharedPreferences("BRAHMAPUTRA", 0);
        DHANSIRI = getApplicationContext().getSharedPreferences("DHANSIRI", 0);
        DIBANG = getApplicationContext().getSharedPreferences("DIBANG", 0);
        DIHING = getApplicationContext().getSharedPreferences("DIHING", 0);
        KAMENG = getApplicationContext().getSharedPreferences("KAMENG", 0);
        KAPILI = getApplicationContext().getSharedPreferences("KAPILI", 0);
        LOHIT = getApplicationContext().getSharedPreferences("LOHIT", 0);
        MANAS = getApplicationContext().getSharedPreferences("MANAS", 0);
        SIANG = getApplicationContext().getSharedPreferences("SIANG", 0);
        SUBHANSIRI = getApplicationContext().getSharedPreferences("SUBHANSIRI", 0);
        UMIAM = getApplicationContext().getSharedPreferences("UMIAM", 0);
        if(hostel.equals("BARAK")){
            SharedPreferences.Editor editor = BARAK.edit();
            editor.putString("month",BARAK_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("BRAHMAPUTRA")){
            SharedPreferences.Editor editor = BRAHMAPUTRA.edit();
            editor.putString("month",BRAHMAPUTRA_MONTH);

            editor.commit();
            DeleteFile(hostel);
            
        }else if(hostel.equals("DHANSIRI")){
            SharedPreferences.Editor editor = DHANSIRI.edit();
            editor.putString("month",DHANSIRI_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("DIBANG")){
            SharedPreferences.Editor editor = DIBANG.edit();
            editor.putString("month",DIBANG_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("DIHING")){
            SharedPreferences.Editor editor = DIHING.edit();
            editor.putString("month",DIHING_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("KAMENG")){
            SharedPreferences.Editor editor = KAMENG.edit();
            editor.putString("month",KAMENG_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("KAPILI")){
            SharedPreferences.Editor editor = KAPILI.edit();
            editor.putString("month",KAPILI_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("LOHIT")){
            SharedPreferences.Editor editor = LOHIT.edit();
            editor.putString("month",LOHIT_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("MANAS")){
            SharedPreferences.Editor editor = MANAS.edit();
            editor.putString("month",MANAS_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("SIANG")){
            SharedPreferences.Editor editor = SIANG.edit();
            editor.putString("month",SIANG_MONTH);

            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("SUBHANSIRI")){
            SharedPreferences.Editor editor = SUBHANSIRI.edit();
            editor.putString("month",SUBHANSIRI_MONTH);
            editor.commit();
            DeleteFile(hostel);
        }else if(hostel.equals("UMIAM")){
            SharedPreferences.Editor editor = UMIAM.edit();
            editor.putString("month",UMIAM_MONTH);
            editor.commit();
            DeleteFile(hostel);
        }

    }

    private void DeleteFile(String hostel) {
        File del = new File(dir);
        if (del.isDirectory())
        {
          //  Toast.makeText(this, "About to Delete "+hostel, Toast.LENGTH_SHORT).show();


            File file = new File(del, hostel+".pdf");
            boolean deleted = file.delete();
            if (deleted) {
                String myhostel = new SessionManager(Mess_Menu.this).getUserDetails().get("hostel");
                myhostel.toUpperCase();
                if (hostel.equals(myhostel))
                Toast.makeText(this, hostel.toLowerCase() + " Menu Updated", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void checkmonth(){
        sharedPreferences = getApplicationContext().getSharedPreferences("Shivam", 0);
        final String sp = sharedPreferences.getString("month","Not Available");


        FirebaseFirestore.getInstance().collection("messmenu").document("updatemenu").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i("shivam", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                //    Log.i("shivam", "Current data: " + snapshot.getData().get("month").toString());
                    menuMonth = snapshot.getData().get("month").toString();
                    if (menuMonth.equals(sp)){
                 //       Log.i("shivam","its up to date");
                    }
                    else{
                   //     Log.i("shivam","its time to delete");
                        updateSharedPreference();
                    }
                } else {
                  //  Log.i("shivam", "Current data: null");
                }
            }
        });

    }

    private void check_hostel_month() {
        BARAK = getApplicationContext().getSharedPreferences("BARAK", 0);
        BRAHMAPUTRA = getApplicationContext().getSharedPreferences("BRAHMAPUTRA", 0);
        DHANSIRI = getApplicationContext().getSharedPreferences("DHANSIRI", 0);
        DIBANG = getApplicationContext().getSharedPreferences("DIBANG", 0);
        DIHING = getApplicationContext().getSharedPreferences("DIHING", 0);
        KAMENG = getApplicationContext().getSharedPreferences("KAMENG", 0);
        KAPILI = getApplicationContext().getSharedPreferences("KAPILI", 0);
        LOHIT = getApplicationContext().getSharedPreferences("LOHIT", 0);
        MANAS = getApplicationContext().getSharedPreferences("MANAS", 0);
        SIANG = getApplicationContext().getSharedPreferences("SIANG", 0);
        SUBHANSIRI = getApplicationContext().getSharedPreferences("SUBHANSIRI", 0);
        UMIAM = getApplicationContext().getSharedPreferences("UMIAM", 0);


        final String barakString = BARAK.getString("month","Not Available");
        final String brahmaputraString = BRAHMAPUTRA.getString("month","Not Available");
        final String dhansiriString = DHANSIRI.getString("month","Not Available");
        final String dibangString = DIBANG.getString("month","Not Available");
        final String dihingString = DIHING.getString("month","Not Available");
        final String kamengString = KAMENG.getString("month","Not Available");
        final String kapiliString = KAPILI.getString("month","Not Available");
        final String lohitString = LOHIT.getString("month","Not Available");
        final String manasString = MANAS.getString("month","Not Available");
        final String siangString = SIANG.getString("month","Not Available");
        final String subhansiriString = SUBHANSIRI.getString("month","Not Available");
        final String umiamString = UMIAM.getString("month","Not Available");


        FirebaseFirestore.getInstance().collection("MessMenuURLs").document("Months").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i("shivam", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    BARAK_MONTH = snapshot.getData().get("BARAK").toString();
                    if (BARAK_MONTH.equals(barakString)){
                               Log.i("shivam","its up to date");
                    }
                    else{
                             Log.i("shivam","its time to delete");
                        updateHostelMonth("BARAK");
                    }
                    BRAHMAPUTRA_MONTH = snapshot.getData().get("BRAHMAPUTRA").toString();
                    if (BRAHMAPUTRA_MONTH.equals(brahmaputraString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("BRAHMAPUTRA");
                    }

                    DHANSIRI_MONTH = snapshot.getData().get("DHANSIRI").toString();
                    if (DHANSIRI_MONTH.equals(dhansiriString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("DHANSIRI");
                    }

                    DIBANG_MONTH = snapshot.getData().get("DIBANG").toString();
                    if (DIBANG_MONTH.equals(dibangString)){
                              Log.i("DIBANG","DIBANG its up to date");
                    }
                    else{
                             Log.i("DIBANG","DIBANG its time to delete");
                        updateHostelMonth("DIBANG");
                    }

                    DIHING_MONTH = snapshot.getData().get("DIHING").toString();
                    if (DIHING_MONTH.equals(dihingString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("DIHING");
                    }

                    KAMENG_MONTH = snapshot.getData().get("KAMENG").toString();
                    if (KAMENG_MONTH.equals(kamengString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("KAMENG");
                    }

                    KAPILI_MONTH = snapshot.getData().get("KAPILI").toString();
                    if (KAPILI_MONTH.equals(kapiliString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("KAPILI");
                    }

                    LOHIT_MONTH = snapshot.getData().get("LOHIT").toString();
                    if (LOHIT_MONTH.equals(lohitString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("LOHIT");
                    }
                    MANAS_MONTH = snapshot.getData().get("MANAS").toString();
                    if (MANAS_MONTH.equals(manasString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("MANAS");
                    }
                    SIANG_MONTH = snapshot.getData().get("SIANG").toString();
                    if (SIANG_MONTH.equals(siangString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("SIANG");
                    }
                    SUBHANSIRI_MONTH = snapshot.getData().get("SUBHANSIRI").toString();
                    if (SUBHANSIRI_MONTH.equals(subhansiriString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("SUBHANSIRI");
                    }
                    UMIAM_MONTH = snapshot.getData().get("UMIAM").toString();
                    if (UMIAM_MONTH.equals(umiamString)){
                        //       Log.i("shivam","its up to date");
                    }
                    else{
                        //     Log.i("shivam","its time to delete");
                        updateHostelMonth("UMIAM");
                    }
                } else {
                    //  Log.i("shivam", "Current data: null");
                }
            }


        });
    }

    public void getUrlandLoadPDF(final String Hostel){

        FirebaseFirestore.getInstance().collection("MessMenuURLs").document("URLs").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i("nikhil", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.i("nikhil", "Get URL of "+Hostel + snapshot.getData().get(Hostel).toString());
                     URL = snapshot.getData().get(Hostel).toString();
                    LoadPDF(Hostel,URL);

                } else {

                    Snackbar.make((LinearLayout)findViewById(R.id.remote_pdf_root), "Mess Menu Not Uploaded", Snackbar.LENGTH_INDEFINITE).show();
                    Log.i("nikhil", "Current data: null");

                }
            }
        });



    }
    public void LoadPDF(String Hostel,String url){
        if(isExternalStorageReadable() && isExternalStorageWritable()){

            File file = new File(dir, "/"+Hostel+".pdf");

           // Log.i("shivam",dir+"/"+ Hostel + ".pdf");
           // Log.i("Testing",getFilesDir().toString());
            if (!file.exists()){
                Log.i("shivam","File Does not exists");

                if (isOnline()) {
                    beginDownload(Hostel, url);
                    progressdialog.show();
                }

                else{
                    //showSnackbar();
                    showDialog(Mess_Menu.this);
                }


            }
            else {
                SetPDF(Hostel);
                Log.i("shivam","File exists");

            }
        }
    }
    public void showSnackbar(){
        final Snackbar snackbar = Snackbar
                .make((LinearLayout)findViewById(R.id.remote_pdf_root), "You are Offline", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            if (isOnline()){

                            }
                            else
                                showDialog(Mess_Menu.this);
                                //showSnackbar();
                    }
                });
        snackbar.show();



    }
    public void showDialog(Activity activity) {
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
                if (isOnline()){
                    getUrlandLoadPDF("BARAK");
                }
                else {
                    dialog.show();
                }
            }
        });
        Button ok = dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();
    }

//    .onSnapshot(function(snapshot) {
//        snapshot.docChanges().forEach(function(change) {
//            if (change.type === "added") {
//                console.log("New city: ", change.doc.data());
//            }
//            if (change.type === "modified") {
//                console.log("Modified city: ", change.doc.data());
//            }
//            if (change.type === "removed") {
//                console.log("Removed city: ", change.doc.data());
//            }
//        });
//    });

    private void beginDownload(final String Hostel, String URL) {


        BroadcastReceiver listener = new BroadcastReceiver() {
            @Override
            public void onReceive( Context context, Intent intent ) {
                //String data = intent.getStringExtra("Download complete");
                    Log.i("shivam",Hostel+" File Downloading at"+dir);
                    progressdialog.hide();
                  SetPDF(Hostel);
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(listener,
                new IntentFilter("Download complete"));

        MyDownloadManager downloadManager = new MyDownloadManager();

        downloadManager.Download(getApplicationContext(),Hostel, URL);





    }
    public void SetPDF(String Hostel){
        Log.e("test","PDF Loaded");
        File file = new File(dir+"/"+Hostel + ".pdf");
        pdfview.fromFile(file)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onLoad(Mess_Menu.this)
                .onPageChange(Mess_Menu.this)
                .load();

    }



    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    @Override
    public void loadComplete(int nbPages) {
        //Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Mess_Menu.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }

}




