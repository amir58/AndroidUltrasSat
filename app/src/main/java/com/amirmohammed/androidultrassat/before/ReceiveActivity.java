package com.amirmohammed.androidultrassat.before;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.amirmohammed.androidultrassat.R;

public class ReceiveActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        imageView = findViewById(R.id.iv_received);

        Intent intent = getIntent();

        if (intent.getAction().equals(Intent.ACTION_SEND) && intent.getType().equals("text/plain")){
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);

            System.out.println(text);
        }

        System.out.println(intent.getType());

        if(intent.getAction().equals(Intent.ACTION_SEND) && intent.getType().startsWith("image/")){
            System.out.println("HELLO");

            Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);

            imageView.setImageURI(imageUri);
        }

    }

}