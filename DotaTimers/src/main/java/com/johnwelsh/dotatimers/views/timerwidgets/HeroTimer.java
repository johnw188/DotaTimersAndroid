package com.johnwelsh.dotatimers.views.timerwidgets;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.johnwelsh.dotatimers.heroselection.HeroSelectionActivity;
import com.johnwelsh.dotatimers.models.HeroModel;

/**
 * Created by john.welsh on 7/6/13.
 */
public class HeroTimer extends TimerView {
    private HeroModel model;

    public HeroTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected String stringFromSeconds(int seconds) {
        return Integer.toString(seconds);
    }

    @Override
    public void onTimerClicked() {
        if (model == null) {
            displayHeroSelectorAlertDialog();
        } else {
            displayTimerConfig();
        }
    }

    private void displayHeroSelectorAlertDialog() {
        Intent intent = new Intent(getContext(), HeroSelectionActivity.class);
        Context context = getContext();
        if (context != null) {
            context.startActivity(intent);
        }
    }

    private void displayTimerConfig() {

    }
}
