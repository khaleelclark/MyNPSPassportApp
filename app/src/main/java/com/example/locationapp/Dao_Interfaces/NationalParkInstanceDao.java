package com.example.locationapp.Dao_Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.locationapp.nationalParkInstance;

import java.util.List;

@Dao
public interface NationalParkInstanceDao {
    @Query("SELECT * FROM national_park_instance")
    List<nationalParkInstance> getAllInstances();

    @Query("SELECT * FROM national_park_instance WHERE userId = :userId")
    List<nationalParkInstance> findInstancesByUserId(int userId);

    @Query("SELECT * FROM national_park_instance WHERE parkId = :parkId")
    List<nationalParkInstance> findInstancesByParkId(int parkId);

    @Insert
    void insertAll(nationalParkInstance... instances);

    @Update
    void updateInstance(nationalParkInstance instance);

    @Delete
    void deleteInstance(nationalParkInstance instance);
}

