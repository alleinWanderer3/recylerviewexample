package com.example.all_app_example.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryAnimal {
    private LiveData<List<Animal>> allAnimals;
    private AnimalDAO animalDAO;
    public RepositoryAnimal(Context context){
        AnimalDataBase dataBase = AnimalDataBase.getInstance(context.getApplicationContext());
        animalDAO = dataBase.getAnimalDAO();
        allAnimals = animalDAO.getAllAnimals();
    }

    LiveData<List<Animal>> getAllAnimals(){
        return allAnimals;
    }

    void insert(Animal animal){
        new AsyncTask<Animal, Void, Void>(){
            @Override
            protected Void doInBackground(Animal... a) {
                animalDAO.insertAnimal(a[0]);
                return null;
            }
        }.execute(animal);
    }
    void deleteAll(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                animalDAO.deleteAll();
                return null;
            }
        }.execute();
    }

    void updateItem(Animal animal){
        new AsyncTask<Animal,Void,Void>(){
            @Override
            protected Void doInBackground(Animal... animals) {
                animalDAO.update(animals[0]);
                return null;
            }
        }.execute(animal);
    }

    void insertAll(final List<Animal>  animals){
        new AsyncTask<List<Animal>, Void, Void>(){
            @Override
            protected Void doInBackground(List<Animal>... a) {
                animalDAO.insertAll(a[0]);
                return null;
            }
        }.execute(animals);
    }
}
