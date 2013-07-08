package com.johnwelsh.dotatimers.timer;

import android.os.Handler;

/**
 * Created by john.welsh on 7/5/13.
 */
public class GameTimer {
    private long startSystemTime;
    private long initialTime;
    private SecondTickHandler secondTickHandler;

    public GameTimer(SecondTickHandler handler) {
        this.secondTickHandler = handler;
    }

    public void startTimer() {
        startTimer(0);
    }

    private boolean shouldPauseTimer = false;
    public void pauseTimer() {
        shouldPauseTimer = true;
    }

    public int gameTimeForSystemClockTime(long systemClockTime) {
        return (int) (((systemClockTime - startSystemTime + initialTime) + 500) / 1000);
    }

    public void startTimer(long initialTimeMS)
    {
        shouldPauseTimer = false;
        startSystemTime = System.currentTimeMillis();
        this.initialTime = initialTimeMS;
        final Handler handler = new Handler();
        final Runnable counter = new Runnable(){
            public void run(){
                if (shouldPauseTimer) {
                    return;
                }
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startSystemTime + initialTime;
                int secondsElapsed = (int) ((elapsedTime + 500) / 1000);
                secondTickHandler.secondTicked(secondsElapsed);

                long nextDelay = startSystemTime % 1000 - currentTime % 1000 + 1000;
                //Log.v("GameTimer", "Start system time: " + startSystemTime + ", Current time: " + currentTime + ", elapsed time: " + elapsedTime + ", nextDelay: " + nextDelay);
                handler.postDelayed(this, nextDelay);
            }
        };

        handler.postDelayed(counter, 1000);
    }

    public void clear() {
    }
}
