package com.amirmohammed.androidultrassat.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TasksDAO {

    @Query("SELECT * FROM tasks WHERE status = 'active'")
    Single<List<Task>> getActiveTasks();

    @Query("SELECT * FROM tasks WHERE status = 'done'")
    List<Task> getDoneTasks();

    @Query("SELECT * FROM tasks WHERE status = 'archive'")
    List<Task> getArchiveTasks();

    @Insert
    Completable insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);
}
