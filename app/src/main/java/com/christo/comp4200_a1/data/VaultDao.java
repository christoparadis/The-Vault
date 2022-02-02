package com.christo.comp4200_a1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.christo.comp4200_a1.model.VaultEntry;

import java.util.List;

@Dao
public interface VaultDao {

    @Insert
    void insertEntry(VaultEntry vaultEntry);

    @Update
    void update(VaultEntry entry);

    @Delete
    void delete(VaultEntry entry);

    @Query("DELETE FROM vault_table")
    void deleteAll();

    @Query("SELECT * FROM vault_table")
    LiveData<List<VaultEntry>> getVaultEntries();

    @Query("SELECT * FROM vault_table WHERE vault_table.vault_id == :id")
    LiveData<VaultEntry> get(long id);


}
