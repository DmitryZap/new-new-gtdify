package ru.techpark.new_new_gtdify;

import android.app.Application;

import androidx.room.Room;

import ru.techpark.new_new_gtdify.model.local.AppDatabase;
import ru.techpark.new_new_gtdify.model.local.dao.CardDao;

public class App extends Application {

    private AppDatabase database;
    private CardDao cardDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }
    

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "gtdify-db").
                build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public CardDao getCardDao() {
        return cardDao;
    }

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }
}
