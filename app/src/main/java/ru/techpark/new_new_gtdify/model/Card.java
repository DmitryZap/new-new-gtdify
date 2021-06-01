package ru.techpark.new_new_gtdify.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName="card_table")
public class Card implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String unformattedText;

//    private int priority = 0;

    // private long timestamp;


    // private int section;

//    private Long deadline;
//    private boolean isComplete;

    public Card() {    }
//    public Card(String name, String unformattedText, int priority, Long deadline, boolean isComplete) {
//        this.name = name;
//        this.unformattedText = unformattedText;
//        this.priority = priority;
//        this.deadline = deadline;
//        // this.section = section;
//        this.isComplete = isComplete;
//    }

    protected Card(Parcel in) {
        id = in.readInt();
        name = in.readString();
        unformattedText = in.readString();
//        priority = in.readInt();
//        isComplete = in.readByte() != 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnformattedText() {
        return unformattedText;
    }

//    public int getPriority() {
//        return priority;
//    }
//
//    public Long getDeadline() {
//        return deadline;
//    }
//
//    public boolean isComplete() {
//        return isComplete;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnformattedText(String unformattedText) {
        this.unformattedText = unformattedText;
    }

//    public void setPriority(int priority) {
//        this.priority = priority;
//    }
//
//    public void setDeadline(Long deadline) {
//        this.deadline = deadline;
//    }
//
//    public void setComplete(boolean complete) {
//        isComplete = complete;
//    }

//    public int getSection() {
//        return section;
//    }

//    public long getDeadline() {
//        return deadline;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Card card = (Card) o;
//        return id == card.id &&
//                priority == card.priority &&
//                isComplete == card.isComplete &&
//                Objects.equals(name, card.name) &&
//                Objects.equals(unformattedText, card.unformattedText) &&
//                Objects.equals(deadline, card.deadline);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, unformattedText, priority, deadline, isComplete);
//    }

}
