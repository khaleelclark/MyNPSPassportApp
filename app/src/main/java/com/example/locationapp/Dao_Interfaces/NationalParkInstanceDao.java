package com.example.locationapp.Dao_Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.locationapp.NationalParkInstance;

import java.util.List;

@Dao
public interface NationalParkInstanceDao {
    @Query("SELECT * FROM national_park_instance")
    LiveData<List<NationalParkInstance>> getAllInstances();

    @Query("SELECT * FROM national_park_instance WHERE userId = :userId")
    List<NationalParkInstance> findInstancesByUserId(int userId);

    @Query("SELECT * FROM national_park_instance WHERE parkId = :parkId")
    List<NationalParkInstance> findInstancesByParkId(int parkId);


    @Insert
    void insertAll(NationalParkInstance... instances);

    @Update
    void updateInstance(NationalParkInstance instance);

    @Delete
    void deleteInstance(NationalParkInstance instance);
}

