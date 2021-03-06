package ru.techpark.new_new_gtdify.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.local.AppDatabase;
import ru.techpark.new_new_gtdify.model.local.dao.CardDao;

public class CardRepository {
//    private CardDao cardDao;
    private LiveData<List<Card>> allCards;
    private AppDatabase database;

    public CardRepository(Application application) {
        database = AppDatabase.getInstance(application);
    }

    public void  addCard (Card card) {
//        database.getCardDao().add(card);
        Completable.fromRunnable(() -> database.getCardDao().addCard(card))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

//    public void deleteCard (Card card) {
//        Observable.fromRunnable(() -> database.getCardDao().deleteCard(card))
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }
//
    public void updateCard (Card card) {
        Completable.fromRunnable(() -> database.getCardDao().updateCard(card))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Flowable<List<Card>> getCardList() {
        return database.getCardDao().getAllCards();
    }
}
