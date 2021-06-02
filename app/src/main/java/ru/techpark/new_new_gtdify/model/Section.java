package ru.techpark.new_new_gtdify.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="section_table")
public class Section {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public Section() {}

    public Section(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
