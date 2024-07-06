package com.example.locationapp;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;
import com.example.locationapp.Dao_Interfaces.NationalParkDao;
import com.example.locationapp.Dao_Interfaces.NationalParkInstanceDao;
import com.example.locationapp.Dao_Interfaces.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, NationalPark.class, NationalParkInstance.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract NationalParkDao nationalParkDao();
    public abstract NationalParkInstanceDao nationalParkInstanceDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "database-name")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                NationalParkDao dao = INSTANCE.nationalParkDao();
                NationalPark arches = new NationalPark("Arches", "Utah", "Delicate");
                NationalPark bryceCanyon = new NationalPark("Bryce Canyon", "Utah", "Hoodoo");
                NationalPark canyonlands = new NationalPark("Canyonlands", "Utah", "Canyons");
                NationalPark capitolReef = new NationalPark("Capitol Reef", "Utah", "Pie");
                NationalPark zion = new NationalPark("Zion", "Utah", "Angel's Landing");
                dao.insertAll(arches, bryceCanyon, canyonlands, capitolReef, zion);
            });
        }
    };
}