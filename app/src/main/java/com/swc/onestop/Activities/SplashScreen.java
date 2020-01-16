package com.swc.onestop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.swc.onestop.R;


public class SplashScreen extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        setContentView(R.layout.activity_splash_screen);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        OneSignal.setSubscription(true);

        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        String userId = status.getSubscriptionStatus().getUserId();
        //Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SessionManager sessionManager;
                sessionManager = new SessionManager(getApplicationContext());
                if(sessionManager.isLoggedIn()){
                    startActivity(new Intent(SplashScreen.this,Main2Activity.class));
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        },500);
    }
}
