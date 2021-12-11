package com.amirmohammed.androidultrassat.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.androidultrassat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                if (item.getItemId() == R.id.item_active_tasks) {
                    getActiveTasks();

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

    @Override
    protected void onResume() {
        super.onResume();
        getActiveTasks();
    }

    private void getActiveTasks(){
        TasksDatabase.db.tasksDAO().getActiveTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Task>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Task> tasks) {
                        showTasks(tasks);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Toast.makeText(DatabaseActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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