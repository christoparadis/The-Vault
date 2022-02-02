package com.christo.comp4200_a1.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<VaultEntry> selectedEntry = new MutableLiveData<>();
    private boolean isEdit;

    public void selectEntry(VaultEntry entry){
        selectedEntry.setValue(entry);
    }
    public LiveData<VaultEntry> getSelectedEntry(){
        return selectedEntry;
    }
    public void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
    public boolean getIsEdit(){
        return isEdit;
    }
}
