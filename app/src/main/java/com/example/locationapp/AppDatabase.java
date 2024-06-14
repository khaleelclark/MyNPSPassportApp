package com.example.locationapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.locationapp.Dao_Interfaces.NationalParkDao;
import com.example.locationapp.Dao_Interfaces.NationalParkInstanceDao;
import com.example.locationapp.Dao_Interfaces.UserDao;

@Database(entities = {User.class, NationalPark.class, nationalParkInstance.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao usersDao();
    public abstract NationalParkDao nationalParkDao();
    public abstract NationalParkInstanceDao nationalParkInstanceDao();
}
