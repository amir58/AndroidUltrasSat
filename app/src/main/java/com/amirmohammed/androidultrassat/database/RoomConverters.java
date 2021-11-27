package com.amirmohammed.androidultrassat.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class RoomConverters {

    @TypeConverter
    public String userToJson(User user){
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @TypeConverter
    public User jsonToUser(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }



}
