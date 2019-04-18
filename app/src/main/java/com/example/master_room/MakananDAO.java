package com.example.master_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MakananDAO {
    @Insert
    Long insertData(Makanan makanan);

    @Query("Select * from makanan_db")
    List<Makanan> getData();

    @Update
    int updateData(Makanan item);

    @Delete
    void deleteData(Makanan item);
}
