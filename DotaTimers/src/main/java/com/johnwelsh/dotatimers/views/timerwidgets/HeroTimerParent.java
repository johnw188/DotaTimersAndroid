package com.johnwelsh.dotatimers.views.timerwidgets;

import com.johnwelsh.dotatimers.models.HeroModel;

/**
 * Created by john.welsh on 7/7/13.
 */
public interface HeroTimerParent {
    public void timerNeedsHeroSelection(HeroTimer timer);
    public void timerNeedsConfig(int gameTimeWhenTimerStarted, HeroModel model, HeroTimer timer);
}
