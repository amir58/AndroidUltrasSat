package com.amirmohammed.androidultrassat.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.amirmohammed.androidultrassat.R;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseSplashActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_splash);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if(firebaseAuth.getCurrentUser() == null){
                        Intent intent = new Intent(FirebaseSplashActivity.this, FirebaseLoginActivity.class);
                        startActivity(intent);
                    }
                    else {
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