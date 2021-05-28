package ru.techpark.new_new_gtdify;

import ru.techpark.new_new_gtdify.model.ProcessType;

public interface ActivityListener {
    void onStarted();

    void onSuccess(Boolean state);

    void onFailure(String message);

    void onProcess(ProcessType processType, Object data);
}
