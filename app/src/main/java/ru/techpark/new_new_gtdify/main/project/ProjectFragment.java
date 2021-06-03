package ru.techpark.new_new_gtdify.main.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.card.CardActivity;
import ru.techpark.new_new_gtdify.card.CardViewModel;
import ru.techpark.new_new_gtdify.databinding.FragmentProjectBinding;
import ru.techpark.new_new_gtdify.main.MainActivity;
import ru.techpark.new_new_gtdify.model.Card;
import ru.techpark.new_new_gtdify.model.CardStatus;

public class ProjectFragment extends Fragment implements CheckBoxClickListener {

    private ProjectViewModel mViewModel;
    private @NonNull FragmentProjectBinding binding;
    private ProjectCardRecyclerViewAdapter adapter = new ProjectCardRecyclerViewAdapter(this);
    private BottomAddCategoryFragment bottomAddCategoryFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Toast toast = Toast.makeText(getContext(),
                            "Карточка создана", Toast.LENGTH_LONG);
                    toast.show();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProjectBinding.inflate(inflater, container, false);
        binding.addCardButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            someActivityResultLauncher.launch(intent);
//                getActivity().finish();
        });

        mViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        binding.sectionsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getCardList().observe(getViewLifecycleOwner(), (List<Card> it) -> {
            adapter.setValues(it);
            // adapter.notifyDataSetChanged();
        });

        binding.sectionsRecycler.setAdapter(adapter);

        // Add section button
        binding.addSectionButton.setOnClickListener(v -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            if (bottomAddCategoryFragment == null && fm.findFragmentByTag("fragment_bottom_add_category") == null) {
                bottomAddCategoryFragment = new BottomAddCategoryFragment();
            } else if (bottomAddCategoryFragment == null) {
                bottomAddCategoryFragment = (BottomAddCategoryFragment) fm.findFragmentByTag("fragment_bottom_add_category");
            }
            bottomAddCategoryFragment.show(getActivity().getSupportFragmentManager(),"fragment_bottom_add_category");
        });

        return binding.getRoot();
    }

    @Override
    public void onCheckClick(Card card) {
        mViewModel.updateCard(card);
    }

//    public interface OnProjectFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onProjectFragmentInteraction(Card item, CardStatus status);
//    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onCreateCard() {
//        Toast toast = Toast.makeText(getApplicationContext(),
//                "Карточка создана", Toast.LENGTH_SHORT);
//        toast.show();
//    }
}

