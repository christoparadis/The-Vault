package com.christo.comp4200_a1.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.christo.comp4200_a1.data.VaultDao;
import com.christo.comp4200_a1.model.VaultEntry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {VaultEntry.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class VaultRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 4;
    public static volatile VaultRoomDatabase INSTANCE;
    public static final String DATABASE_NAME = "Vault_Entries";
    public static final ExecutorService databaseWriterExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriterExecutor.execute(()->{
                        VaultDao vaultDao = INSTANCE.vaultDao();
                        vaultDao.deleteAll();
                    });


                }
            };

    public static VaultRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (VaultRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VaultRoomDatabase.class, DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }

        }
        return INSTANCE;
    }
    public abstract VaultDao vaultDao();

}
