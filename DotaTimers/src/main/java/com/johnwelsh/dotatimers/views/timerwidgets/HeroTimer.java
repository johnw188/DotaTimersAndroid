package com.johnwelsh.dotatimers.views.timerwidgets;

import android.content.Context;
import android.util.AttributeSet;

import com.johnwelsh.dotatimers.models.HeroModel;

/**
 * Created by john.welsh on 7/6/13.
 */
public class HeroTimer extends TimerView {
    private HeroModel model;
    private HeroTimerParent heroTimerParent;

    public HeroTimerParent getHeroTimerParent() {
        return heroTimerParent;
    }

    public void setHeroTimerParent(HeroTimerParent heroTimerParent) {
        this.heroTimerParent = heroTimerParent;
    }

    public HeroTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void startTiming(int secondsToTime, int initialGameTime) {
        super.startTiming(secondsToTime, initialGameTime);
        model.setCurrentlyTiming(true);
        model.setSecondsToTime(secondsToTime);
        model.setGameTimeWhenTimingStarted(initialGameTime);
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

    @Override
    protected void onTimerFinished() {
        super.onTimerFinished();
        model.setCurrentlyTiming(false);
    }

    private void displayHeroSelectorAlertDialog() {
        if (heroTimerParent != null) {
            heroTimerParent.timerNeedsHeroSelection(this);
        }
    }

    private void displayTimerConfig() {
        int gameTimeAtStartOfTiming = manager.getGameTimeForSystemClockTime(System.currentTimeMillis());
        heroTimerParent.timerNeedsConfig(gameTimeAtStartOfTiming, model, this);
    }

    public void setHeroModel(HeroModel model) {
        this.model = model;
        setBaseImageFromID(model.getIconID());
        if (model.isCurrentlyTiming()) {
            startTiming(model.getSecondsToTime(), model.getGameTimeWhenTimingStarted());
        }
    }

    public HeroModel getModel() {
        return model;
    }
}
