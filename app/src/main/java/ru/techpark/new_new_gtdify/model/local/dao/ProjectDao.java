package ru.techpark.new_new_gtdify.model.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import ru.techpark.new_new_gtdify.model.Project;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project_table")
    Flowable<List<Project>> getAllProjects();

    @Query("SELECT * FROM project_table WHERE id = :uid LIMIT 1")
    Project findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProject(Project project);

    // TODO: выборка по секции
    //    @Query("SELECT * FROM card_table WHERE project =:projectID ")
    //    List<Card> getAllCardsByProject(int projectID);

    @Update
    void updateProject(Project project);

    @Delete
    void deleteProject(Project project);
}
