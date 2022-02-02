package com.christo.comp4200_a1.data;
import com.christo.comp4200_a1.model.VaultEntry;
import com.christo.comp4200_a1.util.VaultRoomDatabase;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class VaultRepository {

    private final VaultDao vaultDao;
    private final LiveData<List<VaultEntry>> allEntries;

    public VaultRepository(Application application) {
        VaultRoomDatabase database = VaultRoomDatabase.getDatabase(application);
        vaultDao = database.vaultDao();
        allEntries = vaultDao.getVaultEntries();
    }

    public LiveData<List<VaultEntry>> getAllEntries(){
        return allEntries;
    }
    public void insert(VaultEntry entry){
        VaultRoomDatabase.databaseWriterExecutor.execute(()-> vaultDao.insertEntry(entry));
    }
    public LiveData<VaultEntry> get(long id){
        return vaultDao.get(id);
    }
    public void update(VaultEntry entry){
        VaultRoomDatabase.databaseWriterExecutor.execute(()->vaultDao.update(entry));
    }
    public void delete (VaultEntry entry){
        VaultRoomDatabase.databaseWriterExecutor.execute(()->vaultDao.delete(entry));
    }

}
