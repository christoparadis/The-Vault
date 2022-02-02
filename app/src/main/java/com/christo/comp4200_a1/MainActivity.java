package com.christo.comp4200_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.christo.comp4200_a1.adaptor.RecyclerViewAdaptor;
import com.christo.comp4200_a1.adaptor.onEntryClickListener;
import com.christo.comp4200_a1.model.SharedViewModel;
import com.christo.comp4200_a1.model.VaultEntry;
import com.christo.comp4200_a1.model.VaultViewModel;
import com.christo.comp4200_a1.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements onEntryClickListener {

    private VaultViewModel vaultViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdaptor recyclerViewAdaptor;
    BottomEntryFragment bottomEntryFragment;
    private SharedViewModel sharedViewModel;
    private String TAG = "HELPME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomEntryFragment = new BottomEntryFragment();
        ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        vaultViewModel = new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication())
                .create(VaultViewModel.class);

        vaultViewModel.getAllEntries().observe(this, entries -> {
            recyclerViewAdaptor = new RecyclerViewAdaptor(entries, this);
            recyclerView.setAdapter(recyclerViewAdaptor);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            showBottomFragmentDialog();
        });

    }
    private void showBottomFragmentDialog() {
        bottomEntryFragment.show(getSupportFragmentManager(), bottomEntryFragment.getTag());
    }
    @Override
    public void onEntryClick(VaultEntry entry) {
        sharedViewModel.selectEntry(entry);
        sharedViewModel.setIsEdit(true);
        showBottomFragmentDialog();
        Log.d(TAG, "onEntryClick: "+entry.getWebTitle());
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onEntryRadioClick(VaultEntry entry) {
        Log.d(TAG, "onEntryRadioClick: "+entry.getWebTitle());
        VaultViewModel.delete(entry);
        recyclerViewAdaptor.notifyDataSetChanged();
    }
    @Override
    public void onPassWordClick(VaultEntry entry) {
        Log.d(TAG, "Password Click: "+entry.getWebTitle());
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Password",entry.getPassword());
        clipboardManager.setPrimaryClip(clip);
    }

}