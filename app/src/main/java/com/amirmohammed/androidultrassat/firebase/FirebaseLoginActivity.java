package com.amirmohammed.androidultrassat.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.androidultrassat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextInputEditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        editTextEmail = findViewById(R.id.firebase_et_email);
        editTextPassword = findViewById(R.id.firebase_et_password);
    }

    public void login(View view) {
        String email = editTextEmail.getText().toString();

        if (email.isEmpty()){
            editTextEmail.setError("Email required");
            return;
        }

        String password = editTextPassword.getText().toString();

        if (password.isEmpty()){
            editTextPassword.setError("password required");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(FirebaseLoginActivity.this, "Login success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(FirebaseLoginActivity.this, FirebaseDatabaseActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


    }

    public void register(View view) {
        Intent intent = new Intent(this, FirebaseRegisterActivity.class);
        startActivity(intent);

    }
}