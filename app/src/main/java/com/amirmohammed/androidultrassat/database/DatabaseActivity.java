package com.amirmohammed.androidultrassat.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.amirmohammed.androidultrassat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {
    RecyclerView recyclerViewTasks;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        SharedPref.init(getApplicationContext());
        TasksDatabase.init(getApplicationContext());

        recyclerViewTasks = findViewById(R.id.rv_tasks);
        bottomNavigationView = findViewById(R.id.database_bottom_navigation);

        List<Task> tasks = TasksDatabase.db.tasksDAO().getActiveTasks();
        showTasks(tasks);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                if (item.getItemId() == R.id.item_active_tasks) {
                    List<Task> tasks = TasksDatabase.db.tasksDAO().getActiveTasks();
                    showTasks(tasks);

                } else if (item.getItemId() == R.id.item_done_tasks) {
                    List<Task> tasks = TasksDatabase.db.tasksDAO().getDoneTasks();
                    showTasks(tasks);

                } else if (item.getItemId() == R.id.item_archive_tasks) {
                    List<Task> tasks = TasksDatabase.db.tasksDAO().getArchiveTasks();
                    showTasks(tasks);
                }

                return false;
            }
        });

    }

    private void showTasks(List<Task> tasks) {

        TasksAdapter tasksAdapter = new TasksAdapter(tasks);

        recyclerViewTasks.setAdapter(tasksAdapter);
    }


    public void openInsertTaskActivity(View view) {
        Intent intent = new Intent(DatabaseActivity.this, InsertTaskActivity.class);
        startActivity(intent);
    }
}