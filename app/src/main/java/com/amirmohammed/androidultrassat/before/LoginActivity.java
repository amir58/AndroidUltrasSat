package com.amirmohammed.androidultrassat.before;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
    }

    public void login(View view) {
        String email = editTextEmail.getText().toString();

        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            return;
        }
        String password = editTextPassword.getText().toString();

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            return;
        }

        if (email.equals("amir@gmail.com") && password.equals("123")) {
            // navigate to main screen
            Intent intent = new Intent(LoginActivity.this
                    , MainActivity.class);

            ProductModel productModel = new ProductModel(
                    1,
                    "laptop",
                    "HP Laptop 16GB 256 SSD",
                    "El Bostan",
                    1,
                    15000,
                    10
            );

            intent.putExtra("product", productModel);

            intent.putExtra("email", email);
            intent.putExtra("password",password );
            startActivity(intent);
        } else {
            Toast.makeText(this, "Email or password wrong",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}