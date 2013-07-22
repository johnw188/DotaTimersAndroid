package com.johnwelsh.dotatimers.timer;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john.welsh on 7/5/13.
 */
public class GameTimer implements Parcelable {
    private long startSystemTime;
    private long initialTime;
    private SecondTickHandler secondTickHandler;
    private Handler counterHandler;
    private Runnable counter;

    public GameTimer(SecondTickHandler handler) {
        this.secondTickHandler = handler;
    }

    public GameTimer(Parcel parcel) {
        this.startSystemTime = parcel.readLong();
        this.initialTime = parcel.readLong();
        this.timeOnClockAtPause = parcel.readLong();
        if (timeOnClockAtPause == -1) {
            initializeTimerRunnable();
        }
    }

    public void startTimer() {
        startTimer(0);
    }

    // -1 means we're running, but initialize to 0 because we start paused at 0ms
    private long timeOnClockAtPause = 0;

    public void pauseTimer() {
        if (counter == null) {
            return;
        }
        timeOnClockAtPause = System.currentTimeMillis() - startSystemTime + initialTime;
        counterHandler.removeCallbacks(counter);
        counter = null;
        counterHandler = null;
    }

    public void unpauseTimer() {
        startTimer(timeOnClockAtPause);
        timeOnClockAtPause = -1;
    }

    public int gameTimeForSystemClockTime(long systemClockTime) {
        return (int) (((systemClockTime - startSystemTime + initialTime) + 500) / 1000);
    }

    public void startTimer(long initialTimeMS)
    {
        startSystemTime = System.currentTimeMillis();
        this.initialTime = initialTimeMS;
        initializeTimerRunnable();
    }

    private void initializeTimerRunnable() {
        counterHandler = new Handler();
        counter = new Runnable(){
            public void run(){
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startSystemTime + initialTime;
                int secondsElapsed = (int) ((elapsedTime + 500) / 1000);
                if (secondTickHandler != null) {
                    secondTickHandler.secondTicked(secondsElapsed);
                }

                long nextDelay = startSystemTime % 1000 - currentTime % 1000 + 1000;
                //Log.v("GameTimer", "Start system time: " + startSystemTime + ", Current time: " + currentTime + ", elapsed time: " + elapsedTime + ", nextDelay: " + nextDelay);
                counterHandler.postDelayed(this, nextDelay);
            }
        };

        counterHandler.postDelayed(counter, 1000);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(startSystemTime);
        parcel.writeLong(initialTime);
        parcel.writeLong(timeOnClockAtPause);
    }

    public static final Creator<GameTimer> CREATOR = new Creator<GameTimer>() {
        @Override
        public GameTimer createFromParcel(Parcel parcel) {
            return new GameTimer(parcel);
        }

        @Override
        public GameTimer[] newArray(int i) {
            return new GameTimer[0];
        }
    };

    public void setSecondTickHandler(SecondTickHandler secondTickHandler) {
        this.secondTickHandler = secondTickHandler;
    }
}
