package com.christo.comp4200_a1.adaptor;

import com.christo.comp4200_a1.model.VaultEntry;

public interface onEntryClickListener {
    void onEntryClick(VaultEntry entry);
    void onEntryRadioClick(VaultEntry entry);
    void onPassWordClick(VaultEntry entry);
}
