package com.amirmohammed.androidultrassat.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(RoomConverters.class)
@Database(entities = {Task.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    public static TasksDatabase db;

    public abstract TasksDAO tasksDAO();

    public static void init(Context context) {
        if (db == null)
            db = Room
                    .databaseBuilder(context, TasksDatabase.class, "TasksDatabase")
//                    .allowMainThreadQueries()
                    .build();

    }

}
