package com.johnwelsh.dotatimers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.johnwelsh.dotatimers.heroselection.HeroSelectionActivity;
import com.johnwelsh.dotatimers.models.HeroModel;
import com.johnwelsh.dotatimers.timer.SecondTickHandler;
import com.johnwelsh.dotatimers.timer.TimerWidgetManager;
import com.johnwelsh.dotatimers.timerconfig.TimerConfig;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimer;
import com.johnwelsh.dotatimers.views.timerwidgets.HeroTimerParent;
import com.johnwelsh.dotatimers.views.timerwidgets.RoshTimer;
import com.johnwelsh.dotatimers.views.timerwidgets.TimerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreenActivity extends Activity implements HeroTimerParent {
    static final int SELECTED_HERO_RESULT = 0;

    private TextView text;
    SimpleDateFormat formatter = new SimpleDateFormat("m':'ss");

    private HeroTimer[] timers;

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

        for (HeroTimer timer : timers) {
            timer.setHeroTimerParent(this);
        }
        text = (TextView) findViewById(R.id.gameTimer);
        HeroModel alchModel = new HeroModel("alchemist", "Alchemist", 20, 30, 40);
        final TimerView hero1 = (TimerView) findViewById(R.id.hero1);
        hero1.setBaseImageFromID(alchModel.getIconID());
        hero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero1.startTiming(70);
            }
        });

        final TimerWidgetManager manager = new TimerWidgetManager();

        RoshTimer rosh = (RoshTimer) findViewById(R.id.roshButton);

        manager.addTimerWidget(rosh);

        manager.addTimerWidget(hero1);

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
    public void timerNeedsConfig(int gameTimeWhenTimerStarted) {
        TimerConfig config = new TimerConfig(this, null);
        
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
