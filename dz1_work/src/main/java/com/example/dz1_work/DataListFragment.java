package com.example.dz1_work;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dz1_work.data.Animal;
import com.example.dz1_work.data.AnimalViewModel;
import com.example.dz1_work.data.Repository;

import java.util.List;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;
import static androidx.recyclerview.widget.ItemTouchHelper.END;
import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static androidx.recyclerview.widget.ItemTouchHelper.START;


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
                if (getActivity().findViewById(R.id.container_detail) == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container_portrait, new DetailFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container_detail, new DetailFragment())
                            .commit();
                }
            }
        });
        prepareListener(rv);
        return v;
    }


    @Override
    public void onItemClick(int position, Animal animal) {
        if (getActivity().findViewById(R.id.container_detail) == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container_portrait, DetailFragment.newInstance(animal.name,
                            animal.description,
                            position))
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_detail, DetailFragment.newInstance(animal.name,
                            animal.description,
                            position))
                    .commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.data_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            int index = animalAdapter.getSelectedIndex();
            if (index != -1) {
                animalViewModel.delete(animalAdapter.animals.get(index));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    boolean swipe;
    int flag;
    boolean stop_flag = false;
    float dx = 0;
    public void prepareListener(final RecyclerView rv) {
        ItemTouchHelper.SimpleCallback itSimpleCallback = new ItemTouchHelper.SimpleCallback(0, RIGHT | LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {

                if (swipe) {
                    swipe = false;
                    Log.d("swipe", "dx in convert" +dx );
                    return 0;
                }//*/
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull final RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if(actionState == ACTION_STATE_SWIPE) {
                recyclerView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        swipe = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                        return false;
                    }
                });

                    AnimalAdapter.AnimalViewHolder holder = (AnimalAdapter.AnimalViewHolder) viewHolder;
                    Log.d("swipe", "swipe=" + swipe);
                    Log.d("swipe", "dx = " + dX);
                    if(swipe){
                        holder.showMenuBtns(true);
                    } else {
                        //  holder.showMenuBtns(false);
                    }


                 }


                dx = dX;
               /* if (dX > 300 || dX < -300) {

                    stop_flag = true;
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            return false;
                        }
                    });
                    holder.showMenuBtns(true);

                } else {
                    stop_flag = false;
                    holder.showMenuBtns(false);

                }*/
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               /* int pos = viewHolder.getAdapterPosition();
                if (direction==RIGHT){

                    animalViewModel.delete(animalAdapter.getAnimals().get(pos));
                    rv.getAdapter().notifyItemRemoved(pos);
                } else if(direction == LEFT){
                    Animal animal = animalAdapter.getAnimals().get(pos);
                    if(getActivity().findViewById(R.id.container_detail)==null){
                        getFragmentManager().beginTransaction()
                                .add(R.id.container_portrait,DetailFragment.newInstance(animal.name,
                                        animal.description,
                                        pos))
                                .addToBackStack(null)
                                .commit();
                    } else {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container_detail,DetailFragment.newInstance(animal.name,
                                        animal.description,
                                        pos))
                                .commit();
                    }
                }*/
            }
        };
        new ItemTouchHelper(itSimpleCallback).attachToRecyclerView(rv);
    }
    
}
