package ru.techpark.new_new_gtdify.main.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.card.CardActivity;
import ru.techpark.new_new_gtdify.main.MainActivity;

public class ProjectFragment extends Fragment {
    FloatingActionButton fab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);

        fab = (FloatingActionButton) rootView.findViewById(R.id.add_card_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);

//                getActivity().finish();
            }
        });
        return rootView;
    }

}
