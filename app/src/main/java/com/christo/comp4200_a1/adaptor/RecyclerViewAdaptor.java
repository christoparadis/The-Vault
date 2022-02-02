package com.christo.comp4200_a1.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.christo.comp4200_a1.R;
import com.christo.comp4200_a1.model.VaultEntry;
import com.christo.comp4200_a1.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {

    private final List<VaultEntry> vaultList;
    public static final String SECRET = "*";

    private final onEntryClickListener entryClickListener;

    public RecyclerViewAdaptor(List<VaultEntry> vaultList, onEntryClickListener onEntryClickListener) {
        this.vaultList = vaultList;
        this.entryClickListener = onEntryClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vault_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VaultEntry entry = vaultList.get(position);

        String formatted = Utils.formatDate(entry.getEntryDay());

        holder.title.setText(entry.getWebTitle());
        holder.username.setText(entry.getUsername());
        holder.password.setText(entry.getPassword());
        holder.calendar.setText(formatted);
    }

    @Override
    public int getItemCount() {
        return vaultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public AppCompatRadioButton radioButton;
        public AppCompatTextView title;
        public AppCompatTextView username;
        public AppCompatTextView calendar;
        public Chip password;
        onEntryClickListener onEntryClickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.vault_row_Radio);
            title = itemView.findViewById(R.id.vault_row_Title);
            username = itemView.findViewById(R.id.vault_row_Username);
            password = itemView.findViewById(R.id.vault_row_chip);
            calendar = itemView.findViewById(R.id.vault_row_date);
            this.onEntryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
            password.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            VaultEntry currEntry = vaultList.get(getAdapterPosition());
            int id = view.getId();
            if(id == R.id.vault_row_layout) {
                entryClickListener.onEntryClick(currEntry);
            }else if(id == R.id.vault_row_Radio){
                entryClickListener.onEntryRadioClick(currEntry);
            } else if(id == R.id.vault_row_chip){
                entryClickListener.onPassWordClick(currEntry);
            }
        }
    }
}
