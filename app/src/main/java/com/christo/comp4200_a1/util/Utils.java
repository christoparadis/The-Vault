package com.christo.comp4200_a1.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date){
        Date temp =  Calendar.getInstance().getTime();

            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
            simpleDateFormat.applyPattern("EEE, MMM d");
            return simpleDateFormat.format(temp);


    }

    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
