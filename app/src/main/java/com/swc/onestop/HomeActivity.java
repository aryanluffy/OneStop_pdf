package com.swc.onestop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.OpenableColumns;
import androidx.annotation.NonNull;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar ;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.parse.ParseInstallation;
import com.squareup.picasso.Picasso;
import com.swc.onestop.Activities.Main2Activity;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.swc.onestop.Recycler_View_Adapter.PICK_PDF_CODE;
import static com.swc.onestop.Recycler_View_Adapter.recycler;


public class HomeActivity extends AppCompatActivity {

    public static Uri newpdfuri;
    public static String newpdfname;
    public String PREF_ACCESS_TOKEN = "access_token";
    public  String PREFS_NAME = "MyPrefsFile";
    SharedPreferences prefs;
    //    private TextView mTextMessage;
    public static boolean isathome = true;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    public static Uri uploadurl=null;
    public static String uploadname="";
    private FirebaseAuth auth;
    private Toolbar toolbar;
    private CircleImageView profileimg;
    private TextView profilename;
    private long lastClickTime = 0;
    public static Drawable d = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        nv = (NavigationView)findViewById(R.id.nv);
        t = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        setSupportActionBar(toolbar);
        dl.addDrawerListener(t);
        t.syncState();
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(HomeActivity.this, Main2Activity.class));
                finish();
            }
        });
        View header = nv.getHeaderView(0);
        profilename = header.findViewById(R.id.profilenamenv);
        final TextView profileemail = header.findViewById(R.id.profileemailnv);

        final Activity ac = this;

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
//                    return;
//                }
//                Toast.makeText(getApplicationContext(),"LOL",Toast.LENGTH_SHORT).show();
//                lastClickTime = SystemClock.elapsedRealtime();
//                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
////                startActivity(intent);
////                intent.putExtra("profileimage", profileimg.getImageMatrix().toString());
//                Pair<View, String> p1 = Pair.create((View)profileimg, "profileimg");
////                Pair<View, String> p3 = Pair.create((View)cv, "card");
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, p1);
//                ActivityCompat.startActivityForResult(ac, intent, 2, options.toBundle());
            }
        });
        Intent receivedIntent = getIntent();
        String receivedAction = receivedIntent.getAction();
        String receivedType = receivedIntent.getType();
        String fileName="";
        if(Intent.ACTION_SEND.equals(receivedAction)){
            Uri receivedUri = receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
            uploadurl = receivedUri;


            String result = null;
            if (receivedUri.getScheme().equals("content")) {
                Cursor cursor = getContentResolver().query(receivedUri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = receivedUri.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            uploadname = result;
            Log.e("TAG", "onCreate: "+result);
            if(savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new UploadFragment()).commit();
                nv.setCheckedItem(R.id.upload);
            }
        }
        else {
            if(savedInstanceState == null) {
                Log.e("TAG", "onCreate: usual");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FindFragment()).commit();
                nv.setCheckedItem(R.id.find);
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                dl.closeDrawer(GravityCompat.START);
//                switch(id)
//                {
//                    case R.id.find:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FindFragment()).commit();
//                        return true;
//                    case R.id.upload:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new UploadFragment()).commit();
//                        return true;
//                    case R.id.leader:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new LeaderFragment()).commit();
//                        return true;
////                    case R.id.about:
////                        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
////                        startActivity(intent);
////                        return true;
//                        default:
//                            return true;
//                    }
//
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file

        if(resultCode==2) {
            String newname = data.getStringExtra("newname");
            profilename.setText(newname.toUpperCase());
        }
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                Uri uri = data.getData();
                String uriString = uri.toString();
                File myFile = new File(uriString);
                String path = myFile.getAbsolutePath();
                String displayName = null;

                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = this.getContentResolver().query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    displayName = myFile.getName();
                }
                Toast.makeText(this, displayName, Toast.LENGTH_SHORT).show();
//                inputdisplaytext.setText(displayName);
//                file_uri = data.getData();
                Log.e("file",data.getData().toString());
                newpdfname=displayName;
                newpdfuri=data.getData();
                recycler.uploadFile(newpdfuri, newpdfname);

            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



