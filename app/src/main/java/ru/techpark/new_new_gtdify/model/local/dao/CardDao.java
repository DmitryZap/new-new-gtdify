package ru.techpark.new_new_gtdify.model.local.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import ru.techpark.new_new_gtdify.model.Card;

@Dao
public interface CardDao {
    @Query("SELECT * FROM card_table")
    Flowable<List<Card>> getAllCards();

    @Query("SELECT * FROM card_table WHERE id IN (:cardIds)")
    Flowable<List<Card>> getAllCardsByIds(int[] cardIds);

    @Query("SELECT * FROM card_table WHERE id = :uid LIMIT 1")
    Card findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCard(Card card);

    // TODO: выборка по секции
    //    @Query("SELECT * FROM card_table WHERE section =:sectionID ")
    //    List<Card> getAllCardsBySection(int sectionID);

    @Update
    void updateCard(Card card);

    @Delete
    void deleteCard(Card card);
}
