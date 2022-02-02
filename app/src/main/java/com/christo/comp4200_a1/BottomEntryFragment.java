package com.christo.comp4200_a1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.christo.comp4200_a1.model.SharedViewModel;
import com.christo.comp4200_a1.model.VaultEntry;
import com.christo.comp4200_a1.model.VaultViewModel;
import com.christo.comp4200_a1.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;


public class BottomEntryFragment extends BottomSheetDialogFragment {

    private EditText enterTitle;
    private EditText enterUsername;
    private EditText enterPassword;
    private int selectedButtonId;
    private CalendarView enterCalender;
    private Button saveButtom;
    private Date enterDate;
    Calendar calendar = Calendar.getInstance();
    private SharedViewModel sharedViewModel;
    private boolean isEdit;

    public BottomEntryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_entry, container, false);

        enterTitle = view.findViewById(R.id.vault_entry_Title);
        enterUsername = view.findViewById(R.id.vault_entry_Username);
        enterPassword = view.findViewById(R.id.vault_entry_Password);
        enterCalender = view.findViewById(R.id.vault_entry_calendarView);
        saveButtom = view.findViewById(R.id.vault_entry_submit);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getSelectedEntry().getValue() != null){
            VaultEntry entry = sharedViewModel.getSelectedEntry().getValue();

            isEdit = sharedViewModel.getIsEdit();


            enterTitle.setText("");
            enterUsername.setText("");
            enterPassword.setText("");


            enterTitle.setText(entry.getWebTitle());
            enterUsername.setText(entry.getUsername());
            enterPassword.setText(entry.getPassword());
            //enterCalender.setDate(enterDate.getTime());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(SharedViewModel.class);


        enterCalender.setOnDateChangeListener((enterCalender, year, month, day)-> {
            calendar.clear();
            calendar.set(year,month,day);
            this.enterDate = calendar.getTime();
        });

        enterCalender.setOnClickListener((view1 -> {
            Utils.hideKeyboard(view1);
        }));



        saveButtom.setOnClickListener(view1 -> {
            String title = enterTitle.getText().toString().trim();
            if(!TextUtils.isEmpty(title)){
                VaultEntry entry = new VaultEntry(title, enterUsername.getText().toString(),enterPassword.getText().toString(), enterDate, false);
                if(isEdit){
                    VaultEntry updateEntry = sharedViewModel.getSelectedEntry().getValue();
                    updateEntry.setWebTitle(title);
                    updateEntry.setUsername(enterUsername.getText().toString());
                    updateEntry.setPassword(enterPassword.getText().toString());
                    updateEntry.setEntryDay(enterDate);
                    VaultViewModel.update(updateEntry);
                }else {
                    VaultViewModel.insert(entry);
                }
                if(this.isVisible()){
                    this.dismiss();
                }
            } else {
                Snackbar.make(saveButtom, R.string.empty_field, Snackbar.LENGTH_LONG).show();
            }
            enterTitle.setText("");
            enterUsername.setText("");
            enterPassword.setText("");

        });

    }
}