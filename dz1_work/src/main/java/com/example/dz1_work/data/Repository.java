package com.example.dz1_work.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository instance;
    private static LiveData<List<Animal>> data;
    private static AnimalDAO animalDAO;
    private Repository(){

    }
    public static Repository getInstance(Context context){
        if(instance == null){
            instance = new Repository();
        }
        animalDAO = AnimalDataBase.getInstance(context).getAnimalDAO();
        return instance;
    }

    public LiveData<List<Animal>> getAll()
    {
        return animalDAO.getAll();
    }

    public void insert(Animal animal){

        new AsyncTask<Animal,Void, Void>(){
            @Override
            protected Void doInBackground(Animal... animals) {
                animalDAO.insert(animals[0]);
                return null;
            }
        }.execute(animal);

    }
    public void delete(Animal animal){
        new AsyncTask<Animal,Void, Void>(){
            @Override
            protected Void doInBackground(Animal... animals) {
                animalDAO.delete(animals[0]);
                return null;
            }
        }.execute(animal);

    }
    public void update(Animal animal){
        new AsyncTask<Animal,Void, Void>(){
            @Override
            protected Void doInBackground(Animal... animals) {
                animalDAO.update(animals[0]);
                return null;
            }
        }.execute(animal);


    }
    public void deleteAll(){
        new AsyncTask<Void,Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                animalDAO.deleteAll();
                return null;
            }
        }.execute();

    }

}
