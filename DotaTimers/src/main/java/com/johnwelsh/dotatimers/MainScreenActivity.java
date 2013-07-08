package com.johnwelsh.dotatimers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.johnwelsh.dotatimers.heroselection.HeroSelectionActivity;
import com.johnwelsh.dotatimers.models.HeroModel;
import com.johnwelsh.dotatimers.timer.SecondTickHandler;
import com.johnwelsh.dotatimers.timer.TimerWidgetManager;
import com.johnwelsh.dotatimers.timerconfig.TimerConfig;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimer;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimerParent;
import com.johnwelsh.dotatimers.views.timerwidgets.RoshTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreenActivity extends Activity implements HeroTimerParent {
    static final int SELECTED_HERO_RESULT = 0;

    private TextView text;
    SimpleDateFormat formatter = new SimpleDateFormat("m':'ss");

    private HeroTimer[] timers;
    private RelativeLayout timerConfigArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        timers = new HeroTimer[]{
                (HeroTimer) findViewById(R.id.hero1),
                (HeroTimer) findViewById(R.id.hero2),
                (HeroTimer) findViewById(R.id.hero3),
                (HeroTimer) findViewById(R.id.hero4),
                (HeroTimer) findViewById(R.id.hero5)
        };

        timerConfigArea = (RelativeLayout) findViewById(R.id.timerConfigArea);
        final TimerWidgetManager manager = new TimerWidgetManager();

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
                text.setText(formatter.format(new Date(numberOfSeconds * 1000)));
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

    private HeroTimer timerBeingSelected;

    @Override
    public void timerNeedsHeroSelection(HeroTimer timer) {
        timerBeingSelected = timer;
        Intent intent = new Intent(this, HeroSelectionActivity.class);
        startActivityForResult(intent, SELECTED_HERO_RESULT);
    }

    @Override
    public void timerNeedsConfig(int gameTimeWhenTimerStarted, HeroModel model, final HeroTimer timer) {
        showTimerConfigArea();
        final TimerConfig config = new TimerConfig(this, null);
        config.setHeroModel(model);
        config.setInitialGameTime(gameTimeWhenTimerStarted);
        config.setCallback(new TimerConfig.TimerConfigCallback() {
            @Override
            public void abilityUsed(int gameTimeWhenAbilityWasUsed, int durationOfCooldown) {
                timerConfigArea.removeView(config);
                if (timerConfigArea.getChildCount() == 0) {
                    hideTimerConfigArea();
                }
                timer.startTiming(durationOfCooldown, gameTimeWhenAbilityWasUsed);
            }

            @Override
            public void buybackUsed() {

            }
        });
        timerConfigArea.addView(config);
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
}
