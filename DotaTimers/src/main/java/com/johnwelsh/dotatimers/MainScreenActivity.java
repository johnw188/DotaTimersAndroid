package com.johnwelsh.dotatimers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.johnwelsh.dotatimers.models.HeroModel;
import com.johnwelsh.dotatimers.timer.SecondTickHandler;
import com.johnwelsh.dotatimers.timer.TimerWidgetManager;
import com.johnwelsh.dotatimers.views.timerwidgets.RoshTimer;
import com.johnwelsh.dotatimers.views.timerwidgets.TimerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreenActivity extends Activity {

    private TextView text;
    SimpleDateFormat formatter = new SimpleDateFormat("m':'ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        text = (TextView) findViewById(R.id.gameTimer);
        HeroModel alchModel = new HeroModel("alchemist", 20, 30, 40);
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

}
