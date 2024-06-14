package com.example.locationapp.Dao_Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.locationapp.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE user_name LIKE :userName LIMIT 1")
    User findByName(String userName);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}