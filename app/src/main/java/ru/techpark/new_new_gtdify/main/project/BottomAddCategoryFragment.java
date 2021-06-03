package ru.techpark.new_new_gtdify.main.project;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ru.techpark.new_new_gtdify.ActivityFragmentListener;
import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.databinding.ActivityCardBinding;
import ru.techpark.new_new_gtdify.databinding.FragmentBottomAddCategoryBinding;
import ru.techpark.new_new_gtdify.databinding.FragmentProjectBinding;
import ru.techpark.new_new_gtdify.model.ProcessType;
import ru.techpark.new_new_gtdify.model.local.AppDatabase;

public class BottomAddCategoryFragment extends BottomSheetDialogFragment implements ActivityFragmentListener {
    private SectionViewModel mViewModel;
    private FragmentBottomAddCategoryBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBottomAddCategoryBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(SectionViewModel.class);

        mViewModel.setActivityFragmentListener(this);
        FragmentBottomAddCategoryBinding dataBinding = DataBindingUtil.
                inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_bottom_add_category,
                        null, false);
        dataBinding.setViewmodel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(Boolean state) {
        dismiss();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onProcess(ProcessType processType, Object data) {

    }
}
