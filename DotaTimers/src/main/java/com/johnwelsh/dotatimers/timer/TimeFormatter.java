package com.johnwelsh.dotatimers.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john.welsh on 7/18/13.
 */
 public class TimeFormatter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("m':'ss");
    public static String formatGameTime(int seconds) {
        return formatter.format(new Date(seconds * 1000));
    }
}
