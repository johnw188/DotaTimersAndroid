package com.johnwelsh.dotatimers.views.timerwidgets;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by john.welsh on 7/6/13.
 */
public class HeroTimer extends TimerView {
    public HeroTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected String stringFromSeconds(int seconds) {
        return Integer.toString(seconds);
    }
}
