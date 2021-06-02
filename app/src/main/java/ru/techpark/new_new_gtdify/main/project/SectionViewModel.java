package ru.techpark.new_new_gtdify.main.project;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

import ru.techpark.new_new_gtdify.ActivityFragmentListener;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.ProcessType;
import ru.techpark.new_new_gtdify.model.Section;
import ru.techpark.new_new_gtdify.model.repository.CardRepository;
import ru.techpark.new_new_gtdify.model.repository.SectionRepository;

public class SectionViewModel extends AndroidViewModel {
    private SectionRepository sectionRepository;
    private ActivityFragmentListener fragmentListener;
    public MutableLiveData<String> sectionName = new MutableLiveData<>();

    public void setActivityFragmentListener(ActivityFragmentListener activityFragmentListener) {
        this.fragmentListener = activityFragmentListener;
    }

    public SectionViewModel(@NonNull Application application) {
        super(application);
        sectionRepository = new SectionRepository(application);
    }

    public void onSave(View v) {
        Section section = new Section();
        section.setName(sectionName.getValue());
        sectionRepository.addSection(section);
        fragmentListener.onSuccess(true);
    }
}
