package com.example.all_app_example.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.all_app_example.AnimalAdapter;
import com.example.all_app_example.MainActivity;
import com.example.all_app_example.R;
import com.example.all_app_example.data.Animal;
import com.example.all_app_example.data.AnimalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class DataListFragment extends Fragment implements AnimalAdapter.OnItemClickListener {

    public DataListFragment() {
        // Required empty public constructor
    }

    public static DataListFragment newInstance(String param1, String param2) {
        DataListFragment fragment = new DataListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AnimalViewModel viewModel = ViewModelProviders.of(this).get(AnimalViewModel.class);
        viewModel.getAllAnimals().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                animalAdapter.setAnimals(animals);
            }
        });
        animalAdapter.setAnimals(viewModel.getAllAnimals().getValue());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           //
        }
    }
    AnimalAdapter animalAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data_list, container, false);
        animalAdapter = new AnimalAdapter(this);
        RecyclerView recyclerView = v.findViewById(R.id.rv);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button fab_add = v.findViewById(R.id.fab_add_animal);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((MainActivity)getContext()).findViewById(R.id.fl_container)!=null) {
                    getFragmentManager().beginTransaction().replace(R.id.fl_container,
                            new DetailFragment())
                            .commit();
                } else {
                    getFragmentManager().beginTransaction().add(R.id.fl_fragment1,
                            new DetailFragment())
                            .addToBackStack(null)
                            .commit();
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

    @Override
    public void onItemClick(int position, Animal animal) {
        if(((MainActivity)getContext()).findViewById(R.id.fl_container)!=null){
            getFragmentManager().beginTransaction().replace(R.id.fl_container,
                    DetailFragment.newInstance(animal.name,animal.description,position))
            .commit();
        } else {
            getFragmentManager().beginTransaction().add(R.id.fl_fragment1,
                    DetailFragment.newInstance(animal.name,animal.description,position))
                    .addToBackStack(null)
                    .commit();
        }
    }



}
