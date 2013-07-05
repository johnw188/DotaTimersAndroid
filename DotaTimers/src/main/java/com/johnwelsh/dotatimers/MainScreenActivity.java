package com.johnwelsh.dotatimers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.johnwelsh.dotatimers.models.HeroModel;

public class MainScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        HeroModel alchModel = new HeroModel("alchemist", 20, 30, 40);
        ImageView hero1 = (ImageView) findViewById(R.id.hero1);
        hero1.setImageResource(alchModel.getIconID());
    }

}
