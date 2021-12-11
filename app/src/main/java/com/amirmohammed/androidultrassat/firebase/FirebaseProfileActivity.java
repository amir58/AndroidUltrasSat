package com.amirmohammed.androidultrassat.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.databinding.ActivityFirebaseProfileBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseProfileActivity extends AppCompatActivity {
    // layoutName + Binding => GeneratedClassNameBinding
    ActivityFirebaseProfileBinding binding;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_firebase_profile);
        binding = ActivityFirebaseProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getProfileData();
    }

    private void getProfileData() {
        firestore.collection("roomOneUsers")
                .document(firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        System.out.println(user.toString());

                        binding.firebaseEtName.setText(user.getName());
                        binding.firebaseEtPhone.setText(user.getPhone());
                        binding.firebaseEtEmail.setText(user.getEmail());

                        Glide.with(FirebaseProfileActivity.this)
                                .load(user.getImageUrl())
                                .into(binding.ivProfile);
                    }
                });
    }

    public void update(View view) {

    }

    public void pickImage(View view) {

    }


}