package com.johnwelsh.dotatimers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.johnwelsh.dotatimers.heroselection.HeroSelectionActivity;
import com.johnwelsh.dotatimers.models.HeroModel;
import com.johnwelsh.dotatimers.timer.GameTimer;
import com.johnwelsh.dotatimers.timer.SecondTickHandler;
import com.johnwelsh.dotatimers.timer.TimeFormatter;
import com.johnwelsh.dotatimers.timer.TimerWidgetManager;
import com.johnwelsh.dotatimers.timerconfig.TimerConfig;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimer;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimerParent;
import com.johnwelsh.dotatimers.views.timerwidgets.RoshTimer;

import java.util.Stack;

public class MainScreenActivity extends Activity implements HeroTimerParent {
    static final int SELECTED_HERO_RESULT = 0;

    private TextView text;

    private HeroTimer[] timers;
    private RelativeLayout timerConfigArea;
    private TimerWidgetManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        timers = new HeroTimer[]{
                (HeroTimer) findViewById(R.id.hero1),
                (HeroTimer) findViewById(R.id.hero2),
                (HeroTimer) findViewById(R.id.hero3),
                (HeroTimer) findViewById(R.id.hero4),
                (HeroTimer) findViewById(R.id.hero5)
        };

        timerConfigArea = (RelativeLayout) findViewById(R.id.timerConfigArea);
        manager = new TimerWidgetManager();
        if (savedInstanceState != null) {
            manager.setGameTimer(savedInstanceState.<GameTimer>getParcelable(GAME_TIMER));
            timers[0].setHeroModel(savedInstanceState.<HeroModel>getParcelable(HERO1));
            timers[1].setHeroModel(savedInstanceState.<HeroModel>getParcelable(HERO2));
            timers[2].setHeroModel(savedInstanceState.<HeroModel>getParcelable(HERO3));
            timers[3].setHeroModel(savedInstanceState.<HeroModel>getParcelable(HERO4));
            timers[4].setHeroModel(savedInstanceState.<HeroModel>getParcelable(HERO5));
        }

        text = (TextView) findViewById(R.id.gameTimer);

        RoshTimer rosh = (RoshTimer) findViewById(R.id.roshButton);

        manager.addTimerWidget(rosh);

        for (HeroTimer timer : timers) {
            timer.setHeroTimerParent(this);
            manager.addTimerWidget(timer);
        }

        manager.addSecondTickHandler(new SecondTickHandler() {
            @Override
            public void secondTicked(int numberOfSeconds) {
                text.setText(TimeFormatter.formatGameTime(numberOfSeconds));
            }
        });

        TextView text = (TextView) findViewById(R.id.gameTimer);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.startGameClock();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private HeroTimer timerBeingSelected;

    @Override
    public void timerNeedsHeroSelection(HeroTimer timer) {
        timerBeingSelected = timer;
        Intent intent = new Intent(this, HeroSelectionActivity.class);
        startActivityForResult(intent, SELECTED_HERO_RESULT);
    }

    private Stack<TimerConfig> timerConfigStack = new Stack<TimerConfig>();

    @Override
    public void timerNeedsConfig(int gameTimeWhenTimerStarted, HeroModel model, final HeroTimer timer) {
        if (timerConfigStack.size() == 0) {
            showTimerConfigArea();
        }

        final TimerConfig config = new TimerConfig(this, null);
        config.setHeroModel(model);
        config.setInitialGameTime(gameTimeWhenTimerStarted);
        config.setCallback(new TimerConfig.TimerConfigCallback() {
            @Override
            public void abilityUsed(int gameTimeWhenAbilityWasUsed, int durationOfCooldown) {
                popTimerConfig();
                timer.startTiming(durationOfCooldown, gameTimeWhenAbilityWasUsed);
            }

            @Override
            public void buybackUsed() {

            }
        });
        timerConfigArea.addView(config);
        timerConfigStack.push(config);
    }

    private void popTimerConfig() {
        TimerConfig config = timerConfigStack.pop();
        timerConfigArea.removeView(config);
        if (timerConfigStack.size() == 0) {
            hideTimerConfigArea();
        }
    }

    @Override
    public void onBackPressed() {
        if (timerConfigStack.size() > 0) {
            popTimerConfig();
        } else {
            super.onBackPressed();
        }
    }

    private void showTimerConfigArea() {
        findViewById(R.id.headerSectionLayout).setVisibility(View.GONE);
        findViewById(R.id.timeline).setVisibility(View.GONE);
        findViewById(R.id.timerConfigArea).setVisibility(View.VISIBLE);
    }

    private void hideTimerConfigArea() {
        findViewById(R.id.headerSectionLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.timeline).setVisibility(View.VISIBLE);
        findViewById(R.id.timerConfigArea).setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECTED_HERO_RESULT) {
            if (resultCode == RESULT_OK) {
                HeroModel model = data.getParcelableExtra("heroModel");
                timerBeingSelected.setHeroModel(model);
            }
        }
    }

    private static final String GAME_TIMER = "gameTimer";
    private static final String HERO1 = "hero1";
    private static final String HERO2 = "hero2";
    private static final String HERO3 = "hero3";
    private static final String HERO4 = "hero4";
    private static final String HERO5 = "hero5";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(GAME_TIMER, manager.getTimer());
        outState.putParcelable(HERO1, timers[0].getModel());
        outState.putParcelable(HERO2, timers[1].getModel());
        outState.putParcelable(HERO3, timers[2].getModel());
        outState.putParcelable(HERO4, timers[3].getModel());
        outState.putParcelable(HERO5, timers[4].getModel());
    }
}
