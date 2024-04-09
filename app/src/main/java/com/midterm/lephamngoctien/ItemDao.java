package com.midterm.lephamngoctien;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insert(Item... items);
}
