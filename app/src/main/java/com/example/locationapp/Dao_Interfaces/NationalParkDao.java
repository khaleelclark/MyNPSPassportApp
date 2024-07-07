package com.example.locationapp.Dao_Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.locationapp.NationalPark;

import java.util.List;

@Dao
public interface NationalParkDao {
    @Query("SELECT * FROM national_park")
    List<NationalPark> getAllParks();

    @Query("SELECT * FROM national_park WHERE uid = :parkId")
    NationalPark getParkById(int parkId);

    @Query("SELECT * FROM national_park WHERE park_name LIKE :parkName")
    List<NationalPark> findParksByName(String parkName);

    @Insert
    void insertAll(NationalPark... parks);

    @Update
    void updatePark(NationalPark park);

    @Delete
    void deletePark(NationalPark park);
}
