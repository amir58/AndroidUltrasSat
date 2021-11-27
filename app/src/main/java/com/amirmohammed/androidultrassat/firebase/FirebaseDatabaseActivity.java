package com.amirmohammed.androidultrassat.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.database.SharedPref;
import com.amirmohammed.androidultrassat.database.TasksDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseActivity extends AppCompatActivity {
    RecyclerView recyclerViewTasks;
    BottomNavigationView bottomNavigationView;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        SharedPref.init(getApplicationContext());
        TasksDatabase.init(getApplicationContext());

        recyclerViewTasks = findViewById(R.id.rv_tasks);
        bottomNavigationView = findViewById(R.id.database_bottom_navigation);

        getTasksFromFirestore("active");

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                if (item.getItemId() == R.id.item_active_tasks) {
                    getTasksFromFirestore("active");


                } else if (item.getItemId() == R.id.item_done_tasks) {
                    getTasksFromFirestore("done");


                } else if (item.getItemId() == R.id.item_archive_tasks) {
                    getTasksFromFirestore("archive");
                }

                return false;
            }
        });

    }

    private void getTasksFromFirestore(String status) {
        firestore.collection("roomOneUsers").document(firebaseAuth.getUid())
                .collection("myTasks")
                .whereEqualTo("status", status)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot q) {
                        List<Task> tasks = new ArrayList<>();

                        for (int i = 0; i < q.getDocuments().size(); i++) {
                            Task task = q.getDocuments().get(i).toObject(Task.class);
                            tasks.add(task);
                        }

                        FirebaseTasksAdapter tasksAdapter = new FirebaseTasksAdapter(tasks);

                        recyclerViewTasks.setAdapter(tasksAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseDatabaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showTasks(List<Task> tasks) {

    }


    public void openInsertTaskActivity(View view) {
        Intent intent = new Intent(FirebaseDatabaseActivity.this, FirebaseInsertTaskActivity.class);
        startActivity(intent);
    }
}