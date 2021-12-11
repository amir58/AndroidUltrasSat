package com.amirmohammed.androidultrassat.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amirmohammed.androidultrassat.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseSplashActivity extends AppCompatActivity {
    private static final String TAG = "Splash";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_splash);

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "onSuccess: FCM TOKEN => " + s);
            }
        });

        Log.i(TAG, "onCreate: THREAD NAME => " + Thread.currentThread().getName());


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (firebaseAuth.getCurrentUser() == null) {
                        Intent intent = new Intent(FirebaseSplashActivity.this, FirebaseLoginActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(FirebaseSplashActivity.this, FirebaseDatabaseActivity.class);
                        startActivity(intent);
                    }


                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}