package ru.techpark.new_new_gtdify.main.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class ProjectFragment extends Fragment {

    private FloatingActionButton mFAB;

    private ProjectViewModel mViewModel;
    private @NonNull FragmentProjectBinding binding;
    private ProjectCardRecyclerViewAdapter adapter = new ProjectCardRecyclerViewAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);

        binding = FragmentProjectBinding.inflate(inflater, container, false);
        binding.addCardButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CardActivity.class);
            startActivity(intent);
//                getActivity().finish();
        });

        mViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);



//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.cards_recycler);


        binding.cardsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getCardList().observe(getViewLifecycleOwner(), (List<Card> it) -> {
            adapter.setValues(it);
            adapter.notifyDataSetChanged();
        });

        binding.cardsRecycler.setAdapter(adapter);
//        recyclerView.setAdapter(adapter);

//        return rootView;
        return binding.getRoot();
    }

    public interface OnProjectFragmentInteractionListener {
        // TODO: Update argument type and name
        void onProjectFragmentInteraction(Card item, CardStatus status);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
