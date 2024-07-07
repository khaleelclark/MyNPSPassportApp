package com.example.locationapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.locationapp.Dao_Interfaces.NationalParkDao;
import com.example.locationapp.Dao_Interfaces.NationalParkInstanceDao;
import com.example.locationapp.Dao_Interfaces.UserDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel extends AndroidViewModel {
    private UserDao userDao;
    private NationalParkDao nationalParkDao;
    private NationalParkInstanceDao nationalParkInstanceDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<NationalPark>> allNationalParks;
    private LiveData<List<NationalParkInstance>> allNationalParkInstances;
    private ExecutorService executorService;
    private final MutableLiveData<Boolean> usernameExists = new MutableLiveData<>();

    public ViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAll();
        allNationalParks = db.nationalParkDao().getAllParks();
        allNationalParkInstances = db.nationalParkInstanceDao().getAllInstances();
        executorService = Executors.newFixedThreadPool(2);

    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<List<NationalPark>> getAllNationalParks(){
        return allNationalParks;
    }
    public LiveData<List<NationalParkInstance>> getAllNationalParkInstances(){
        return allNationalParkInstances;
    }
    public LiveData<Boolean> doesUsernameExist(String username){
   executorService.execute(()-> {
       boolean exists = userDao.countByUsername(username) > 0;
       usernameExists.postValue(exists);
   });
   return usernameExists;
   }

    public void insertUser(User user) {
        executorService.execute(() -> userDao.insertAll(user));
    }
    public void insertNationalPark(NationalPark nationalPark) {
        executorService.execute(() -> nationalParkDao.insertAll(nationalPark));
    }
    public void insertNationalParkInstance(NationalParkInstance nationalParkInstance) {
        executorService.execute(() -> nationalParkInstanceDao.insertAll(nationalParkInstance));
    }

}