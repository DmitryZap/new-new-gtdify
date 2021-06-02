package ru.techpark.new_new_gtdify.main.project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.repository.CardRepository;

public class ProjectViewModel extends AndroidViewModel {

    public MutableLiveData<List<Card>> cards = new MutableLiveData<>();

    private final CardRepository repository;


    public ProjectViewModel(Application application) {
        super(application);
        repository = new CardRepository(application);
        repository.getCardList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    cards.postValue(it);
                });
    }

    public MutableLiveData<List<Card>> getCardList() {
        return cards;
    }

    public void updateCard(Card card) {
        repository.updateCard(card);
    }
}
