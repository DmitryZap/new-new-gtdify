package ru.techpark.new_new_gtdify.model.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.techpark.new_new_gtdify.model.Card;

@Dao
public interface CardDao {
    @Query("SELECT * FROM card_table")
    List<Card> getAllCards();

    @Query("SELECT * FROM card_table")
    LiveData<List<Card>> getAllCardsLiveData();

    @Query("SELECT * FROM card_table WHERE id IN (:cardIds)")
    List<Card> loadAllCardsByIds(int[] cardIds);

    @Query("SELECT * FROM card_table WHERE id = :uid LIMIT 1")
    Card findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Card card);

//    @Query("SELECT * FROM card_table WHERE section =:sectionID ")
//    List<Card> getAllCardsBySection(int sectionID);

    @Update
    void update(Card card);

    @Delete
    void delete(Card card);
}
