package com.johnwelsh.dotatimers.views.timerwidgets;

/**
 * Created by john.welsh on 7/7/13.
 */
public interface HeroTimerParent {
    public void timerNeedsHeroSelection(HeroTimer timer);
    public void timerNeedsConfig(int gameTimeWhenTimerStarted);
}
