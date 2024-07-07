package com.example.locationapp.Dao_Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.locationapp.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE user_name LIKE :userName LIMIT 1")
    User findByName(String userName);

    @Query("SELECT COUNT (*) FROM users WHERE user_name = :userName")
    int countByUsername(String userName);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    //how can i check if a user exists - take username, return boolean
}
