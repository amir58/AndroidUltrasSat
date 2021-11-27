package com.amirmohammed.androidultrassat.before;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amirmohammed.androidultrassat.R;

public class RegisterActivity extends AppCompatActivity {
    private final int ADDRESS_CODE = 1;
    private final int IMAGE_CODE = 2;

    TextView textViewAddress;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewAddress = findViewById(R.id.tv_address);

        imageView = findViewById(R.id.iv_profile);
    }

    public void selectAddress(View view) {
        Intent intent = new Intent(RegisterActivity.this, AddressActivity.class);
        startActivityForResult(intent, ADDRESS_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADDRESS_CODE && resultCode == RESULT_OK){
            String address = data.getStringExtra("address");
            textViewAddress.setText(address);
        }
        else if(requestCode == ADDRESS_CODE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Address not selected", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null){
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

    }

    public void selectProfileImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, IMAGE_CODE);
    }
}