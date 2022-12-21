package com.android.sepiapetsapp.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String ASIA_KOLKATA = "Asia/Kolkata";
    public static final String INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String OUTPUT_FORMAT = "dd/MM/yy hh:mm:ss a";

    /**
     * Check the input date and format accordingly
     *
     * @param date
     * @return
     */
    public static String checkAndFormatDateString(String date) {
        if (TextUtils.isEmpty(date)) {
            return "NA";
        } else {
            return formattedDateString(date);
        }
    }

    /**
     * This method converts date string into formatted string
     *
     * @param dateInString
     * @return
     */
    private static String formattedDateString(String dateInString) {

        String formatted = null;
        try {
            // Convert date string into 'Date' object
            SimpleDateFormat input = new SimpleDateFormat(INPUT_FORMAT);
            input.setTimeZone(TimeZone.getTimeZone(ASIA_KOLKATA));

            Date date = null;
            try {
                date = input.parse(dateInString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Convert 'Date' object according to output format
            SimpleDateFormat output = new SimpleDateFormat(OUTPUT_FORMAT);
            formatted = output.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatted;
    }
}
