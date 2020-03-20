package com.example.dz1_work.data;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AnimalViewModel extends AndroidViewModel {
    private Repository repository = Repository.getInstance(getApplication());
    public AnimalViewModel(@NonNull Application application) {
        super(application);
    }
    public void insert(Animal animal){
        repository.insert(animal);
    }

    public void update(Animal animal){
        repository.update(animal);
    }
    public void delete(Animal animal){
        repository.delete(animal);
    }

    public LiveData<List<Animal>> getAll(){
        return repository.getAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
