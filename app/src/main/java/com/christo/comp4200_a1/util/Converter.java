package com.christo.comp4200_a1.util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {
    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestap(Date date){
        return date == null? null : date.getTime();
    }
}
