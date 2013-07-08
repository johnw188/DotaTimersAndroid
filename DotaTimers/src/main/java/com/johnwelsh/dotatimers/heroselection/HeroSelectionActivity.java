package com.johnwelsh.dotatimers.heroselection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.johnwelsh.dotatimers.R;
import com.johnwelsh.dotatimers.models.HeroModel;

public class HeroSelectionActivity extends Activity {

    private HeroModel[] heroModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hero_selection);
        ListView heroList = (ListView) findViewById(R.id.heroList);
        heroModels = new HeroModel[]{
                new HeroModel("alchemist", "Alchemist", 20, 30, 40),
                new HeroModel("ancient_apparition", "Ancient Apparition", 20, 30, 40),
                new HeroModel("antimage", "Antimage", 20, 30, 40),
                new HeroModel("axe", "Axe", 20, 30, 40),
                new HeroModel("bane", "Bane", 20, 30, 40)
        };
        HeroAdapter adapter = new HeroAdapter(this, R.layout.hero_selection_row, heroModels);
        heroList.setAdapter(adapter);

        heroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                heroSelected(heroModels[i]);
            }
        });
    }

    private void heroSelected(HeroModel model) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("heroModel", model);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
