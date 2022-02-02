package com.christo.comp4200_a1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.christo.comp4200_a1.data.VaultRepository;

import java.util.List;

public class VaultViewModel extends AndroidViewModel {

    public static VaultRepository repository;
    public final LiveData<List<VaultEntry>> allEntries;


    public VaultViewModel(@NonNull Application application) {
        super(application);
        repository = new VaultRepository(application);
        allEntries = repository.getAllEntries();
    }
    public LiveData<VaultEntry> get(long id){return repository.get(id);}
    public LiveData<List<VaultEntry>> getAllEntries(){return allEntries;}
    public static void insert(VaultEntry entry){repository.insert(entry);}
    public static void update(VaultEntry entry){repository.update(entry);}
    public static void delete(VaultEntry entry){repository.delete(entry);}
}
