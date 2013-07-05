package com.johnwelsh.dotatimers.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.johnwelsh.dotatimers.R;

/**
 * Created by john.welsh on 7/5/13.
 */
public class CountdownOverlay extends View {
    private Paint textPaint;

    private int initialTime;
    private int currentTime;

    public void startTimer(int numberOfSeconds) {
        initialTime = numberOfSeconds;
        currentTime = numberOfSeconds;
    }

    public void decrementTimer() {
        currentTime = currentTime - 1;
        invalidate();
    }

    public CountdownOverlay(Context context) {
        super(context);
    }

    public CountdownOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int textColor = getResources().getColor(R.color.countdown_text);
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }



}
