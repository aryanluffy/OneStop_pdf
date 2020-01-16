package com.swc.onestop.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.onesignal.OneSignal;
import com.swc.onestop.Activities.Complaints.ComplaintActivity;
import com.swc.onestop.ForceUpdateChecker;
import com.swc.onestop.Main3Activity;
import com.swc.onestop.MainActivity;
import com.swc.onestop.MainActivity_Models.CustomAdapter;
import com.swc.onestop.MainActivity_Models.CustomAdapter_fav;
import com.swc.onestop.MainActivity_Models.Data_model;
import com.swc.onestop.R;
import com.swc.onestop.SignupActivity;
import com.swc.onestop.SplashActivity;
import com.swc.onestop.ui.ContactActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ForceUpdateChecker.OnUpdateNeededListener {



    Handler handler;
    public static RecyclerView recyclerView;
    public static ArrayList<Data_model> data1;
    public static RecyclerView recyclerView2;
    public static ArrayList<Data_model> data2;
    //    static View.OnClickListener myOnClickListener;
//    private static ArrayList<Integer> removedItems;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String TAG = Main2Activity.class.getSimpleName();
    static final int NUM_ITEMS = 2;
    private ViewPager mViewPager;
    int LIMIT = 2;
    String Onesignal_ID = null;

    static FirebaseFirestore db;
    SessionManager sessionManager ;

    static ArrayList<Map<String, Object>> feedList;
    static ArrayList<Map<String, Object>> favList;
    private static final int PERMISSION_REQUEST_CODE = 200;

    SharedPreferences prefs;
    DocumentSnapshot start;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_map:
                    startActivity(new Intent(Main2Activity.this, MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();

                    return true;
                case R.id.navigation_complaints:
                    if(new SessionManager(getApplicationContext()).isLoggedIn()) {
                        startActivity(new Intent(Main2Activity.this, ComplaintActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                    else{
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main2Activity.this);
                        alertDialogBuilder.setMessage("Login to view Complaints");
                                alertDialogBuilder.setPositiveButton("Login",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                                            }
                                        });

                        alertDialogBuilder.setNegativeButton("Dismiss",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BottomNavigationView bottomNavigationView;
                                bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
                                bottomNavigationView.setSelectedItemId(0);

                            }
                        });


                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }

                    return true;
                case R.id.navigation_timing:
                    startActivity(new Intent(Main2Activity.this, Timings_activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                    return true;
            }
            return false;
        }
    };
    static String capitailizeWord(String str) {
        StringBuffer s = new StringBuffer();

        // Declare a character of space
        // To identify that the next character is the starting
        // of a new word
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {

            // If previous character is space and current
            // character is not space then it shows that
            // current letter is the starting of the word
            if (ch == ' ' && str.charAt(i) != ' ')
                s.append(Character.toUpperCase(str.charAt(i)));
            else
                s.append(str.charAt(i));
            ch = str.charAt(i);
        }

        // Return the string with trimming
        return s.toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Fabric.with(this, new Crashlytics());
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        OneSignal.setSubscription(true);


      //  final OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState() ;
       // Onesignal_ID= status.getSubscriptionStatus().getUserId();

        prefs =  PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();

        Permission();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sessionManager = new SessionManager(getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);


        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setViewPager(mViewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View navHeaderView = navigationView.getHeaderView(0);
        TextView tvHeaderName= (TextView) navHeaderView.findViewById(R.id.name);

        String name =sessionManager.getUserDetails().get("name");
        if(name!=null) {
            tvHeaderName.setText("Hi " + capitailizeWord(sessionManager.getUserDetails().get("name").toLowerCase()) + " !");
            Menu menu = navigationView.getMenu();
            MenuItem logout = menu.findItem(R.id.logout);
            logout.setTitle("Log Out");
        }
        else {
            tvHeaderName.setText("Guest");
            Menu menu = navigationView.getMenu();
            MenuItem logout = menu.findItem(R.id.logout);
            logout.setTitle("Log In");
        }
        TextView tvHeaderHostel = (TextView) navHeaderView.findViewById(R.id.hostel);
        tvHeaderHostel.setText(sessionManager.getUserDetails().get("hostel"));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!isOnline())
            showDialog(Main2Activity.this);







        boolean previouslyStarted = prefs.getBoolean("Previously opened", false);
        Log.i("Previously opened",String.valueOf(previouslyStarted));
        if(!previouslyStarted) {

            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                @Override
                public void idsAvailable(String userId, String registrationId) {
                    Onesignal_ID=userId;
                    Log.i("Onesignal ID",Onesignal_ID);
                    Toast.makeText(Main2Activity.this, ""+userId, Toast.LENGTH_SHORT).show();
                    Add_OnesignalID_toFirebase();
                }
            });




        }




    }

    private void Add_OnesignalID_toFirebase() {
       // Toast.makeText(this, "Subscribing All", Toast.LENGTH_SHORT).show();
        Log.i("Previously opened","Subscribing All");
        final SharedPreferences.Editor edit = prefs.edit();



        if(Onesignal_ID!=null) {
            SubscribeAll(Onesignal_ID);
        }
        else {
            edit.putBoolean("Previously opened", Boolean.FALSE);
            edit.commit();
            Log.i("Previously opened","ID NULL");
            Add_OnesignalID_toFirebase();


        }
    }

    private void SubscribeAll(String Onesignal_ID) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");
        documentReference.update("Swc", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Vp", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Sail", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Sab", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Welfare", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Cultural", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Technical", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Sports", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Senate", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Hab", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Alcher", FieldValue.arrayUnion(Onesignal_ID));
        documentReference.update("Techniche", FieldValue.arrayUnion(Onesignal_ID));
        Log.i("Previously opened","Subscribed");
        final SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("Previously opened", Boolean.TRUE);
        edit.commit();
        Toast.makeText(this, "For Notification modifications check Subscription page", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Exit OneStop? ");
            alertDialogBuilder
                    .setMessage("Just confirming to make sure\n"+"this isn't a pocket dial! \uD83D\uDE09 \n\n"+"Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.freshers){
            startActivity(new Intent(Main2Activity.this,FreshersActivity.class));
        }

        if (id == R.id.outlook) {
            String packageName = "com.microsoft.office.outlook";
            Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent == null) {
                // Bring user to the market or let them choose an app?
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + packageName));
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
            // Handle the camera action

        } else if (id == R.id.timetable) {

            startActivity(new Intent(this, Timetable.class));
            finish();

        } else if (id == R.id.mess_menu) {

            startActivity(new Intent(this, Mess_Menu.class));
            finish();

        } else if (id == R.id.internet_set) {

            startActivity(new Intent(this, Internet_settings.class));
            finish();
        } else if (id == R.id.about) {

            startActivity(new Intent(this, AboutUs.class));
            finish();
        } else if (id == R.id.contacts) {
            startActivity(new Intent(this, ContactActivity.class));
            finish();

        } else if (id == R.id.logout) {


            clearApplicationData();


        } else if (id == R.id.subscription) {


            startActivity(new Intent(this, Subscription.class));


    }
        else if(id==R.id.sawaal){
            NavigationView navigationView=findViewById(R.id.nav_view);
            Menu menu = navigationView.getMenu();
            MenuItem logout = menu.findItem(R.id.logout);
//            String s=logout.getTitle().toString();
//            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            if(logout.getTitle().toString().equals("Log In")){
                Toast.makeText(getApplicationContext(),"Log In before Accessing Prev Papers",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent=new Intent(Main2Activity.this, SplashActivity.class);
                startActivity(intent);
            }
            //finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void loadFragment(Fragment fragment) {
        // load fragment

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FEED";
                case 1:
                    return "FAVOURITES";

            }
            return null;
        }
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        RecyclerView.Adapter adapter;
        ImageView no_saved;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        SwipeRefreshLayout swipeRefreshLayout_fav;
        SwipeRefreshLayout swipeRefreshLayout_feed;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View xyz = null;
            View z=null;
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                xyz = inflater.inflate(R.layout.feed, container, false);

                RecyclerView.LayoutManager layoutManager;
                recyclerView = (RecyclerView) xyz.findViewById(R.id.my_recycler_view_feed);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                data1 = new ArrayList<Data_model>();
                db = FirebaseFirestore.getInstance();

                Log.d("feed", "ref" + db);

                feedList = new ArrayList<>();

//                more = (Button) findViewById(R.id.more);
                swipeRefreshLayout_feed = xyz.findViewById(R.id.swiperefresh_feed);
                setFeedRecyclerview();



                swipeRefreshLayout_feed.setOnRefreshListener(
                        new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                setFeedRecyclerview();
                                Toast.makeText(getContext(), "Refreshed", Toast.LENGTH_SHORT).show();

                            }
                        }
                );


            }

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                xyz = inflater.inflate(R.layout.fav, container, false);




                no_saved = xyz.findViewById(R.id.no_saved);
                RecyclerView.LayoutManager layoutManager;
                recyclerView2 = (RecyclerView) xyz.findViewById(R.id.my_recycler_view_fav);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView2.setItemAnimator(new DefaultItemAnimator());


                data2 = new ArrayList<Data_model>();
                db = FirebaseFirestore.getInstance();

                Log.d("fab", "ref" + db);

                favList = new ArrayList<>();

//                more = (Button) findViewById(R.id.more);
               // TextView t =(TextView) z.findViewById(R.id.text);
               // t.setText("Test");

                swipeRefreshLayout_fav = xyz.findViewById(R.id.swiperefresh_fav);
               if( new SessionManager(getContext()).isLoggedIn()) {
                   setSavedRecyclerview();


               }
               else {


               }




                swipeRefreshLayout_fav.setOnRefreshListener(
                        new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                //Toast.makeText(getContext(), "Refreshed", Toast.LENGTH_SHORT).show();
                                if( new SessionManager(getContext()).isLoggedIn())
                                setSavedRecyclerview();
                            }
                        }
                );


            }


//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return xyz;
        }

        private void setFeedRecyclerview() {
            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            final CollectionReference feedRef = rootRef.collection("feed");
            Query firstQuery = feedRef.orderBy("time", Query.Direction.DESCENDING);
            firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        data1.clear();
                        for (DocumentSnapshot document : task.getResult()) {
                            Map singleData = document.getData();

                            String postLink="";                                   //****testing
                            try{                                                  //****testing
                                postLink=singleData.get("link").toString();       //****testing
                            }                                                     //****testing
                            catch (Exception e){ }                                //****testing
                            data1.add(new Data_model(
                                    singleData.get("title").toString(),
                                    singleData.get("subtitle").toString(),
                                    singleData.get("desc").toString(),
                                    singleData.get("dp").toString(),
                                    singleData.get("image").toString(),
                                    document.getId(),
                                    postLink                                    //*****testing
                            ));

                            Log.d("Data Size", String.valueOf(data1.size()));


//                                feedList.add(document.getData().);
                            //Toast.makeText(Main2Activity.this, document.getData().toString(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Log.d("test", "error");
                    }
                    adapter = new CustomAdapter(data1, getActivity());
                    recyclerView.removeAllViews();
                    recyclerView.setAdapter(adapter);


                }
            });
            swipeRefreshLayout_feed.setRefreshing(false);
        }

        private void setSavedRecyclerview() {
            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            final CollectionReference feedRef_saved = rootRef.collection("users").document(new SessionManager(getContext()).getUserDetails().get("id")).collection("fav");
            Query firstQuery_saved = feedRef_saved.orderBy("time", Query.Direction.DESCENDING);
            firstQuery_saved.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        data2.clear();
                        for (DocumentSnapshot document_fav : task.getResult()) {
                            Map singleData_fav = document_fav.getData();

                            String postLink="";                                   //****testing
                            try{                                                  //****testing
                                postLink=singleData_fav.get("link").toString();   //****testing
                            }                                                     //****testing
                            catch (Exception e){ }                                //****testing
                            data2.add(new Data_model(
                                    singleData_fav.get("title").toString(),
                                    singleData_fav.get("subtitle").toString(),
                                    singleData_fav.get("desc").toString(),
                                    singleData_fav.get("dp").toString(),
                                    singleData_fav.get("image").toString(),
                                    document_fav.getId(),
                                    postLink                                      //****testing
                            ));
                            Log.d("Data 2 fav checking", singleData_fav.get("title").toString());
                            Log.d("fav", singleData_fav.get("title").toString());
//                                feedList.add(document.getData().);


                        }
                    } else {
                        Log.d("test", "error");
                    }

                    RecyclerView.Adapter adapter2;

                    adapter2 = new CustomAdapter_fav(data2, getActivity());
                    adapter2.notifyDataSetChanged();
                    recyclerView2.removeAllViews();
                    recyclerView2.setAdapter(adapter2);
                    if(data2.size()!=0){
                       no_saved.setVisibility(View.INVISIBLE);
                    }
                    else
                        no_saved.setVisibility(View.VISIBLE);

                }
            });
            swipeRefreshLayout_fav.setRefreshing(false);
        }


    }

    public void Permission() {


        if (!checkPermission()) {

            requestPermission();


        }


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED &&result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean writeAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && readAccepted && writeAccepted){


                       // Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                    }

                    else {

                        //Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(Main2Activity.this)
                .setMessage(message)
                .setPositiveButton("Allow", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            Log.i("Delete","applicationDirectory.exists()"+applicationDirectory.toString());
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                    Log.i("Deleted",fileName);
                }
            }
            Intent i = new Intent(this,Outlook_API_Activity.class);
            i.putExtra("logout","true");
            startActivity(i);
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
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
                if (!isOnline()){

                    dialog.show();
                }
            }
        });
        Button ok = dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Connect to the Internet to load all the posts.", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


        dialog.show();
    }

    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue reposting.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).setNegativeButton("No, thanks",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create();
        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
