package com.johnwelsh.dotatimers.heroselection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.johnwelsh.dotatimers.R;

public class HeroSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_selection);
        ListView heroList = (ListView) findViewById(R.id.heroList);
    }


}
