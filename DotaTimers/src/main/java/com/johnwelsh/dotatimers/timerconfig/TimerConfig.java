package com.johnwelsh.dotatimers.timerconfig;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.johnwelsh.dotatimers.R;
import com.johnwelsh.dotatimers.models.HeroModel;

public class TimerConfig extends LinearLayout {
    public interface TimerConfigCallback {
        public void abilityUsed(int gameTimeWhenAbilityWasUsed, int durationOfCooldown);
        public void buybackUsed();
    }

    private TimerConfigCallback callback;
    private HeroModel heroModel;
    private int gameTimeWhenUsed;

    public TimerConfig(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context, attrs);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.timer_config, this, true);
        findViewById(R.id.ult1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.abilityUsed(gameTimeWhenUsed, heroModel.getCooldown1());
            }
        });
        findViewById(R.id.ult2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.abilityUsed(gameTimeWhenUsed, heroModel.getCooldown2());
            }
        });
        findViewById(R.id.ult3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.abilityUsed(gameTimeWhenUsed, heroModel.getCooldown3());
            }
        });
        findViewById(R.id.buyback).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.buybackUsed();
            }
        });
    }

    public void setInitialGameTime(int gameTime) {
        this.gameTimeWhenUsed = gameTime;
    }
    public void setCallback(TimerConfigCallback callback) {
        this.callback = callback;
    }

    public void setHeroModel(HeroModel model) {
        this.heroModel = model;
    }

}
