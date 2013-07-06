package com.johnwelsh.dotatimers.views.timerwidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.johnwelsh.dotatimers.R;

/**
 * Created by john.welsh on 7/6/13.
 */
public class RoshTimer extends TimerView {
    public RoshTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBaseImageFromID(R.drawable.roshan);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startRoshTimer();
            }
        });
    }

    private boolean timingAegis = false;
    private void startRoshTimer() {
        timingAegis = true;
        setBaseImageFromID(R.drawable.wisp);
        startTiming(360);
    }

    @Override
    protected void onTimerFinished() {
        super.onTimerFinished();
        if (timingAegis) {
            timingAegis = false;
            setBaseImageFromID(R.drawable.batrider);
            startTiming(240);
        }
    }
}
