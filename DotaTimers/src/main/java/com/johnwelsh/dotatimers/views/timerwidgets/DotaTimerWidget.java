package com.johnwelsh.dotatimers.views.timerwidgets;

import com.johnwelsh.dotatimers.timer.TimerWidgetManager;

/**
 * Created by john.welsh on 7/6/13.
 */
public interface DotaTimerWidget {
    public void setTimerWidgetManager(TimerWidgetManager manager);
    public void updateTime(int gameTimeInSeconds);
}
