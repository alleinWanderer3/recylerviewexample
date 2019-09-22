package com.example.all_app_example.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.all_app_example.MainActivity;
import com.example.all_app_example.R;
import com.example.all_app_example.data.Animal;
import com.example.all_app_example.data.AnimalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class DetailFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "decription";
    private static final String ARG_POSITION = "pos";

    private String mTitle = "";
    private String mDescription = "";
    private int mPosition = -1;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static DetailFragment newInstance(String title, String description, int position) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mDescription = getArguments().getString(ARG_DESCRIPTION);
            mPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton);
        final EditText ed_title = v.findViewById(R.id.ed_title);
        final EditText ed_description = v.findViewById(R.id.ed_description);
        ed_title.setText(mTitle);
        ed_description.setText(mDescription);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ed_title.getText().toString();
                String descr = ed_description.getText().toString();
                if(title.equals("")|descr.equals("")) {
                    Toast.makeText(getContext(),"fill title and description",Toast.LENGTH_SHORT).show();
                    return;
                }
                AnimalViewModel animalViewModel = ViewModelProviders.of(getActivity()).get(AnimalViewModel.class);
                if (mPosition == -1){
                    animalViewModel.insert(new Animal(title,descr));
                } else {
                    animalViewModel.updateItem(new Animal(title,descr));
                }

                if(((MainActivity)getContext()).findViewById(R.id.fl_container) == null){
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
