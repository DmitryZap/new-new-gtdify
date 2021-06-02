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
import ru.techpark.new_new_gtdify.model.Section;

@Dao
public interface SectionDao {
    @Query("SELECT * FROM section_table")
    Flowable<List<Section>> getAllSections();

    @Query("SELECT * FROM section_table WHERE id = :uid LIMIT 1")
    Section findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSection(Section section);

    // TODO: выборка по секции
    //    @Query("SELECT * FROM card_table WHERE section =:sectionID ")
    //    List<Card> getAllCardsBySection(int sectionID);

    @Update
    void updateSection(Section section);

    @Delete
    void deleteSection(Section section);
}
