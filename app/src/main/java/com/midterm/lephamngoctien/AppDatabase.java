package com.midterm.lephamngoctien;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Item.class, version =  1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "items").build();
        }

        return instance;
    }
}
