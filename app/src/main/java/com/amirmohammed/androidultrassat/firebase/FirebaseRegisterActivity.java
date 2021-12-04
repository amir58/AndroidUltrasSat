package com.amirmohammed.androidultrassat.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amirmohammed.androidultrassat.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class FirebaseRegisterActivity extends AppCompatActivity {
    ImageView imageView;
    EditText editTextName, editTextPhone, editTextEmail, editTextPassword;

    Uri imageUri = null;
    String name = "";
    String phone = "";
    String email = "";
    String password = "";

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_register);

        imageView = findViewById(R.id.iv_profile);
        editTextName = findViewById(R.id.firebase_et_name);
        editTextPhone = findViewById(R.id.firebase_et_phone);
        editTextEmail = findViewById(R.id.firebase_et_email);
        editTextPassword = findViewById(R.id.firebase_et_password);


    }

    public void register(View view) {
        System.out.println("Register");

        if (imageUri == null) {
            Toast.makeText(this, "Image required", Toast.LENGTH_SHORT).show();
            return;
        }

        name = editTextName.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show();
            return;
        }


        phone = editTextPhone.getText().toString();

        if (phone.isEmpty()) {
            Toast.makeText(this, "Phonne required", Toast.LENGTH_SHORT).show();
            return;
        }


        email = editTextEmail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(this, "Email required", Toast.LENGTH_SHORT).show();
            return;
        }


        password = editTextPassword.getText().toString();

        if (password.isEmpty()) {
            Toast.makeText(this, "Password required", Toast.LENGTH_SHORT).show();
            return;
        }

        createUser();
    }

    private void createUser() {
        System.out.println("Create user");

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult)
                    {
                        System.out.println("UserCreated");

                        uploadProfileImageToStorage();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void uploadProfileImageToStorage() {
        System.out.println("Upload image");
        storageReference
                .child("julyProfileImages") // folder
                .child("test") // folder
                .child(firebaseAuth.getUid()) // ده بيكون اسم الفايل عشان قبل putFile
                .putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        System.out.println("Image uploaded");
                        getProfileImageUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void getProfileImageUrl() {
        System.out.println("get image url");
        storageReference
                .child("julyProfileImages") // folder
                .child("test") // folder
                .child(firebaseAuth.getUid()) // ده بيكون اسم الفايل عشان قبل putFile
                .getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        System.out.println("IMAGE URL => " + imageUrl);

                        uploadUserDataToFirestore(imageUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void uploadUserDataToFirestore(String imageUrl) {
        System.out.println("Upload user data");

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);
        map.put("email", email);
        map.put("imageUrl", imageUrl);

        firestore.collection("roomOneUsers").document(firebaseAuth.getUid())
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        System.out.println("user data uploaded");

                        finish();
                        Intent intent = new Intent(FirebaseRegisterActivity.this, FirebaseDatabaseActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void pickImage(View view) {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            imageUri = data.getData();

            // Use Uri object instead of File to avoid storage permissions
            imageView.setImageURI(imageUri);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}