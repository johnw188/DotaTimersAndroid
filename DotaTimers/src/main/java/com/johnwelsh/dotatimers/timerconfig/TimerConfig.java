package com.johnwelsh.dotatimers.timerconfig;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.johnwelsh.dotatimers.R;

public class TimerConfig extends LinearLayout {
    public TimerConfig(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context, attrs);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.timer_config, this, true);
    }
}
