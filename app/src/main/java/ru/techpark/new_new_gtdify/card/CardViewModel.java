package ru.techpark.new_new_gtdify.card;

import android.annotation.SuppressLint;
import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import ru.techpark.new_new_gtdify.ActivityListener;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.ProcessType;
import ru.techpark.new_new_gtdify.model.repository.CardRepository;

public class CardViewModel extends AndroidViewModel {
    private Card cardItem;
    public MutableLiveData<Integer> CardID = new MutableLiveData<>();
    public MutableLiveData<String> CardName = new MutableLiveData<>();
    public MutableLiveData<Integer> SectionID = new MutableLiveData<>();
    public MutableLiveData<String> CardUnformattedText  = new MutableLiveData<>();
    public MutableLiveData<String> CardDate = new MutableLiveData<>();
    public MutableLiveData<String> CardTime = new MutableLiveData<>();
    public MutableLiveData<Long> Deadline = new MutableLiveData<>();

    private CardRepository cardRepository;

    private ActivityListener activityListener;

    private Calendar calendar;

    private int year, month, day, hour, minute, second;

    public CardViewModel(Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        calendar = Calendar.getInstance();

        setDate();
    }

    public ActivityListener getActivityListener() {
        return activityListener;
    }

    public void setActivityListener(ActivityListener activityListener) {
        this.activityListener = activityListener;
    }


    public void edit(Card card) {
        if (card != null && card.getId() > 0) {
            cardItem = card;
            CardID.setValue(card.getId());
            CardName.setValue(card.getName());
            CardUnformattedText.setValue(card.getUnformattedText());
//             SectionID.setValue(card.getSection());
            if (card.getDeadline() != null && card.getDeadline() > 0)
                calendar.setTimeInMillis(card.getDeadline());
        }
        setDate();
    }


    public void onSave(View v) {
        Date date = calendar.getTime();

        if (CardID.getValue() != null && CardID.getValue() > 0) {
            Card card = cardItem;
            card.setUnformattedText(CardUnformattedText.getValue());
            card.setName(CardName.getValue());
            // card.setUserId(App.getSession().getUserId());
            // card.setRosterId(RosterID.getValue());
            card.setDeadline(date.getTime());
//            card.setCreateDate(new Date().getTime());
            cardRepository.updateCard(card);
        } else {
            Card card = new Card();
            card.setUnformattedText(CardUnformattedText.getValue());
            card.setName(CardName.getValue());
//            card.setUserId(CardApplication.getSession().getUserId());
//            card.setRosterId(RosterID.getValue());
            card.setDeadline(date.getTime());
//            card.setCreateDate(new Date().getTime());
//            card.setIdentifier(UUID.randomUUID().toString());
            // card.setStatus(CardStatus.TODO.toString());
            cardRepository.addCard(card);
            activityListener.onProcess(ProcessType.ADD, card);
        }

        activityListener.onSuccess(true);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    private void setDate() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        try {

            @SuppressLint("DefaultLocale") String d = (String.format("%02d", day) + "/" + String.format("%02d", month + 1) + "/" + year);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateInDateFormat = newDateFormat.parse(d);
            String dateString = newDateFormat.format(dateInDateFormat);
            CardDate.setValue(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CardTime.setValue(String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }

    public void SetDate(int year, int month, int day) {
        if (year > 0)
            this.year = year;
        if (month > 0)
            this.month = month;
        if (day > 0)
            this.day = day;

        calendar.set(this.year, this.month, this.day);
    }

    public void SetTime(int hour, int minute, int second) {
        if (hour >= 0)
            this.hour = hour;
        if (minute >= 0)
            this.minute = minute;
        if (year >= 0)
            this.second = second;
        calendar.set(this.year, this.month, this.day, this.day, this.minute);
    }


}