package com.johnwelsh.dotatimers.views.timerwidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.johnwelsh.dotatimers.R;
import com.johnwelsh.dotatimers.timer.TimerWidgetManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john.welsh on 7/5/13.
 */
public abstract class TimerView extends RelativeLayout implements DotaTimerWidget {
    private ImageView baseImage;
    private TextView timeLabel;
    protected TimerWidgetManager manager;
    SimpleDateFormat formatter = new SimpleDateFormat("m':'ss");

    private boolean currentlyTiming = false;
    private int gameTimeWhenTimingStarted;
    private int secondsToTime;

    @Override
    public void setTimerWidgetManager(TimerWidgetManager manager) {
        this.manager = manager;
    }

    @Override
    public void updateTime(int gameTimeInSeconds) {
        onTimerUpdated(gameTimeInSeconds);
        if (currentlyTiming) {
            int newTimerValue = secondsToTime - (gameTimeInSeconds - gameTimeWhenTimingStarted);
            if (newTimerValue <= 0) {
                currentlyTiming = false;
                onTimerFinished();
            } else {
                setTimerTextToSeconds(newTimerValue);
            }
        }
    }

    public void startTiming(int secondsToTime, long systemClockAtStartOfTiming) {
        startTiming(secondsToTime, manager.getGameTimeForSystemClockTime(systemClockAtStartOfTiming));
    }

    public void startTiming(int secondsToTime) {
        startTiming(secondsToTime, manager.getCurrentGameTime());
    }

    public void startTiming(int secondsToTime, int initialGameTime) {
        currentlyTiming = true;
        onTimerStarted(secondsToTime, initialGameTime);
        gameTimeWhenTimingStarted = initialGameTime;
        this.secondsToTime = secondsToTime;
        setTimerTextToSeconds(secondsToTime - (manager.getCurrentGameTime() - initialGameTime));
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context, attrs);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.timer_view, this, true);
        baseImage = (ImageView) findViewById(R.id.timerImage);
        timeLabel = (TextView) findViewById(R.id.countdownText);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onTimerClicked();
            }
        });
    }

    public void setBaseImageFromID(int id) {
        baseImage.setImageResource(id);
    }


    private void showTimerText() {
        baseImage.setColorFilter(R.color.black_overlay);
        timeLabel.setVisibility(VISIBLE);
    }

    private void hideTimerText() {
        baseImage.clearColorFilter();
        timeLabel.setVisibility(INVISIBLE);
    }

    private void setTimerTextToSeconds(int seconds) {
        timeLabel.setText(stringFromSeconds(seconds));
    }

    protected String stringFromSeconds(int seconds) {
        return formatter.format(new Date(seconds * 1000));
    }

    protected void onThirtySecondWarning() {

    }

    protected void onTimerUpdated(int gameTimeInSeconds) {

    }

    protected void onTimerStarted(int secondsToTime, int initialGameTime) {
        showTimerText();
    }

    protected void onTimerFinished() {
        hideTimerText();
    }

    public void onTimerClicked() {

    }
}
