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
                NationalPark arches = new NationalPark("Arches", "Utah", "Known for its over 2,000 natural stone arches, including the famous Delicate Arch, this park offers stunning red rock landscapes and a variety of hiking trails.");
                NationalPark bryceCanyon = new NationalPark("Bryce Canyon", "Utah", "Famous for its unique geological formations called hoodoos, Bryce Canyon features a stunning amphitheater of red, orange, and white rock pillars.");
                NationalPark canyonlands = new NationalPark("Canyonlands", "Utah", "This park is divided into four districts by the Colorado and Green rivers, showcasing dramatic desert landscapes, vast canyons, and towering mesas.\n" +
                        "\n");
                NationalPark capitolReef = new NationalPark("Capitol Reef", "Utah", "Highlighted by the Waterpocket Fold, a unique geologic monocline, Capitol Reef features colorful cliffs, canyons, domes, and bridges in a less crowded setting.");
                NationalPark zion = new NationalPark("Zion", "Utah", "Known for its towering sandstone cliffs, narrow canyons, and the scenic Zion Canyon, this park offers popular hikes like Angel's Landing and The Narrows.");
                NationalPark teton = new NationalPark("Grand Teton", "Wyoming", "Renowned for its stunning mountain vistas, alpine terrain, and the iconic Grand Teton peak, this park provides opportunities for hiking, wildlife viewing, and scenic drives.");
                dao.insertAll(arches, bryceCanyon, canyonlands, capitolReef, zion, teton);
            });
        }
    };
}