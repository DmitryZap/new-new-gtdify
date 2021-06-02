package ru.techpark.new_new_gtdify.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.Section;
import ru.techpark.new_new_gtdify.model.local.AppDatabase;

public class SectionRepository {
    //    private CardDao cardDao;
    private LiveData<List<Section>> allSections;
    private AppDatabase database;

    public SectionRepository(Application application) {
        database = AppDatabase.getInstance(application);
    }

    public void  addSection (Section Section) {
//        database.getCardDao().add(card);
        Completable.fromRunnable(() -> database.getSectionDao().addSection(Section))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    //    public void deleteSection (Section section) {
//        Observable.fromRunnable(() -> database.getCardDao().deleteSection(section))
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }
//
    public void updateSection (Section section) {
        Completable.fromRunnable(() -> database.getSectionDao().updateSection(section))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Flowable<List<Section>> getSectionList() {
        return database.getSectionDao().getAllSections();
    }

}
