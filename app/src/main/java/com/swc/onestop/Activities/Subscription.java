package com.swc.onestop.Activities;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.swc.onestop.R;

public class Subscription extends AppCompatActivity {
    String Onesignal_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(Subscription.this,Main2Activity.class));
                finish();
            }
        });

        final Switch Swc, Vp, Sail, Sab, Welfare, Cultural, Technical, Sports, Senate, Hab, Alcher ,Techniche;
        Swc = findViewById(R.id.Swc);
        Vp = findViewById(R.id.Vp);
        Sail = findViewById(R.id.Sail);
        Sab = findViewById(R.id.sab);
        Welfare = findViewById(R.id.Welfare);
        Cultural = findViewById(R.id.Cultural);
        Technical = findViewById(R.id.techincal);
        Sports = findViewById(R.id.Sports);
        Senate = findViewById(R.id.Senate);
        Hab = findViewById(R.id.Hab);
        Alcher = findViewById(R.id.Alcher);
        Techniche = findViewById(R.id.Techniche);

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.onestop", MODE_PRIVATE);
        Swc.setChecked(sharedPrefs.getBoolean("Swc", true));
        Vp.setChecked(sharedPrefs.getBoolean("Vp", true));
        Sail.setChecked(sharedPrefs.getBoolean("Sail", true));
        Sab.setChecked(sharedPrefs.getBoolean("Sab", true));
        Welfare.setChecked(sharedPrefs.getBoolean("Welfare", true));
        Cultural.setChecked(sharedPrefs.getBoolean("Cultural", true));
        Technical.setChecked(sharedPrefs.getBoolean("Technical", true));
        Sports.setChecked(sharedPrefs.getBoolean("Sports", true));
        Senate.setChecked(sharedPrefs.getBoolean("Senate", true));
        Hab.setChecked(sharedPrefs.getBoolean("Hab", true));
        Alcher.setChecked(sharedPrefs.getBoolean("Alcher", true));
        Techniche.setChecked(sharedPrefs.getBoolean("Techniche", true));



        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        final OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        Onesignal_ID= status.getSubscriptionStatus().getUserId();
        //   documentReference.update("Technical", FieldValue.arrayRemove(Onesignal_ID));

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Onesignal_ID = userId;
            }
        });

        Swc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Swc.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Swc", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Swc", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Swc", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Swc", false);
                    editor.commit();

                }
            }
        });

        Vp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Vp.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Vp", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Vp", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Vp", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Vp", false);
                    editor.commit();

                }
            }
        });

        Sail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Sail.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sail", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sail", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sail", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sail", false);
                    editor.commit();

                }
            }
        });

        Sab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Sab.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sab", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sab", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sab", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sab", false);
                    editor.commit();


                }
            }
        });

        Welfare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Welfare.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Welfare", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Welfare", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Welfare", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Welfare", false);
                    editor.commit();

                }
            }
        });

        Cultural.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Cultural.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Cultural", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Cultural", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Cultural", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Cultural", false);
                    editor.commit();

                }
            }
        });
        Technical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Technical.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Technical", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Technical", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Technical", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Technical", false);
                    editor.commit();

                }
            }
        });

        Sports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Sports.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sports", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sports", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Sports", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Sports", false);
                    editor.commit();

                }
            }
        });
        Senate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Senate.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Senate", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Senate", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Senate", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Senate", false);
                    editor.commit();

                }
            }
        });

        Hab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Hab.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Hab", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Hab", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Hab", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Hab", false);
                    editor.commit();

                }
            }
        });
        Alcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Alcher.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Alcher", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Alcher", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Alcher", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Alcher", false);
                    editor.commit();

                }
            }
        });

        Techniche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Subscription").document("Onesignal_IDs");

                if(Techniche.isChecked()){
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Techniche", FieldValue.arrayUnion(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Techniche", true);
                    editor.commit();

                }
                else {
                    Onesignal_ID= status.getSubscriptionStatus().getUserId();
                    documentReference.update("Techniche", FieldValue.arrayRemove(Onesignal_ID));
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.onestop", MODE_PRIVATE).edit();
                    editor.putBoolean("Techniche", false);
                    editor.commit();

                }
            }
        });





    }
}
