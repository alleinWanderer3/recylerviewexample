package com.example.all_app_example.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {
    private RepositoryAnimal repository = new RepositoryAnimal(getApplication());
    private LiveData<List<Animal>> allAnimals = repository.getAllAnimals();

    public AnimalViewModel(@NonNull Application application) {
        super(application);
    }
    public void insert(Animal animal){
        repository.insert(animal);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public void updateItem(Animal animal){
        repository.updateItem(animal);
    }
    public LiveData<List<Animal>> getAllAnimals(){
        return allAnimals;
    }
}
