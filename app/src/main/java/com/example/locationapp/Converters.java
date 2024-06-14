package com.example.locationapp;

import android.os.Build;
import androidx.room.TypeConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {
    @TypeConverter
    public static LocalDate fromTimestamp(String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return value == null ? null : LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return null;
    }

    @TypeConverter
    public static String dateToTimestamp(LocalDate date) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date == null ? null : date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return "";
    }
}

