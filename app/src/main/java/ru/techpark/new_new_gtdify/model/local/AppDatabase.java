package ru.techpark.new_new_gtdify.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.local.dao.CardDao;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract CardDao getCardDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "gtdify_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}