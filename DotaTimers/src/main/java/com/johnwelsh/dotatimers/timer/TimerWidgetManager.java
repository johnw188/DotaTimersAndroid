package com.johnwelsh.dotatimers.timer;

import com.johnwelsh.dotatimers.views.timerwidgets.DotaTimerWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john.welsh on 7/6/13.
 */
public class TimerWidgetManager {
    private List<SecondTickHandler> handlersToNotify = new ArrayList<SecondTickHandler>();
    private List<DotaTimerWidget> timerWidgets = new ArrayList<DotaTimerWidget>();

    private int currentGameTime = 0;

    public int getCurrentGameTime() {
        return currentGameTime;
    }

    GameTimer timer = new GameTimer(new SecondTickHandler() {
        @Override
        public void secondTicked(int numberOfSeconds) {
            handleSecondTick(numberOfSeconds);
        }
    });

    public void addTimerWidget(DotaTimerWidget widget) {
        widget.setTimerWidgetManager(this);
        timerWidgets.add(widget);
    }

    public void addSecondTickHandler(SecondTickHandler handler) {
        handlersToNotify.add(handler);
    }

    public void startGameClock() {
        timer.startTimer();
    }

    public void pauseGameClock() {
        timer.pauseTimer();
    }

    private void handleSecondTick(int second) {
        currentGameTime = second;
        for (DotaTimerWidget timerWidget : timerWidgets) {
            timerWidget.updateTime(second);
        }
        for (SecondTickHandler secondTickHandler : handlersToNotify) {
            secondTickHandler.secondTicked(second);
        }
    }
}
