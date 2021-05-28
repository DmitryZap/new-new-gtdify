package ru.techpark.new_new_gtdify.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.local.AppDatabase;
import ru.techpark.new_new_gtdify.model.local.dao.CardDao;

public class CardRepository {
    private CardDao cardDao;
    private LiveData<List<Card>> allCards;
    private AppDatabase database;

    public CardRepository(Application application) {
        database = AppDatabase.getInstance(application);
        cardDao = database.getCardDao();
    }

    public void addCard (Card card) {
        cardDao.add(card);
    }

    public void deleteCard (Card card) {
        cardDao.delete(card);
    }

    public void updateCard (Card card) {
        cardDao.update(card);
    }

//    public List<Card> getCardList() {
        // TODO: Поиск по пользователю
//    }


}
