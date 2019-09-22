package com.example.dz1_work;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dz1_work.data.Animal;
import com.example.dz1_work.data.AnimalViewModel;
import com.example.dz1_work.data.Repository;

import java.util.List;


public class DataListFragment extends Fragment implements AnimalAdapter.OnItemClickListener {
    public AnimalAdapter animalAdapter;
    AnimalViewModel animalViewModel;

    public DataListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animalViewModel = ViewModelProviders.of(getActivity()).get(AnimalViewModel.class);
        animalViewModel.getAll().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                animalAdapter.setData(animals);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data_list, container, false);
        RecyclerView rv = v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        animalAdapter = new AnimalAdapter(this);


        rv.setAdapter(animalAdapter);
        v.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity().findViewById(R.id.container_detail)==null){
                    getFragmentManager().beginTransaction()
                            .add(R.id.container_portrait,new DetailFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container_detail,new DetailFragment())
                            .commit();
                }
            }
        });
        return v;
    }


    @Override
    public void onItemClick(int position, Animal animal) {
        if(getActivity().findViewById(R.id.container_detail)==null){
            getFragmentManager().beginTransaction()
                    .add(R.id.container_portrait,DetailFragment.newInstance(animal.name,
                            animal.description,
                            position))
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_detail,DetailFragment.newInstance(animal.name,
                            animal.description,
                            position))
                    .commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.data_list_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_delete){
            int index = animalAdapter.getSelectedIndex();
            if(index != -1){
                animalViewModel.delete(animalAdapter.animals.get(index));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
