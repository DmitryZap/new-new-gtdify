package ru.techpark.new_new_gtdify.card;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import ru.techpark.new_new_gtdify.ActivityFragmentListener;
import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.databinding.ActivityCardBinding;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.ProcessType;
import ru.techpark.new_new_gtdify.utils.KeyboardHelper;


// TODO: Заменить на ViewBinding
public class CardActivity extends AppCompatActivity implements ActivityFragmentListener {

    Toolbar mToolbar;
    CardViewModel mViewModel;
    EditText mDateCard;
    EditText mTimeCard;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ActivityCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_card);

        mViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        binding.setViewmodel(mViewModel);
        mViewModel.setActivityListener(this);

        // Toolbar back button
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back);
        mToolbar.setNavigationOnClickListener(v -> finish());

        // Date and time

        calendar = Calendar.getInstance();

        mDateCard = (EditText) findViewById(R.id.card_date);
        mTimeCard = (EditText) findViewById(R.id.card_time);

        mDateCard.setOnClickListener(v -> {
            KeyboardHelper.hideKeyboard((EditText) v);
            calendar = mViewModel.getCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(v.getContext(),
                    (view, year1, month1, dayOfMonth1) -> {
                        month1 += 1;
                        mViewModel.setDateInCalendar(year1, month1, dayOfMonth1);
                        mDateCard.setText((String.format("%02d", dayOfMonth1) + "/" + String.format("%02d", month1) + "/" + year1));
                    }, year, month, dayOfMonth);
            dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, v.getContext().getString(R.string.dialog_select), dpd);
            dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, v.getContext().getString(R.string.dialog_cancel), dpd);
            dpd.show();
        });
        mTimeCard.setOnClickListener(v -> {
            KeyboardHelper.hideKeyboard((EditText) v);

            calendar = mViewModel.getCalendar();

            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(v.getContext(),
                    (view, hourOfDay1, minute1) -> {
                        mViewModel.setTimeInCalendar(hourOfDay1, minute1, -1);
                        mTimeCard.setText(String.format("%02d", hourOfDay1) + ":" + String.format("%02d", minute1));

                    }, hourOfDay, minute, true);

            tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, v.getContext().getString(R.string.dialog_select), tpd);
            tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, v.getContext().getString(R.string.dialog_cancel), tpd);
            tpd.show();
        });
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(Boolean state) {
        Intent returnIntent = new Intent();
        if (state) {
            setResult(Activity.RESULT_OK, returnIntent);
        } else {
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }
        finish();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onProcess(ProcessType processType, Object data) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                KeyboardHelper.hideKeyboard((EditText) v);
            }
        }

        return super.dispatchTouchEvent(event);
    }
}
