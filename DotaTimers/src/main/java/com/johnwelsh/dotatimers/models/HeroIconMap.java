package com.johnwelsh.dotatimers.models;

import com.johnwelsh.dotatimers.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john.welsh on 7/5/13.
 */
public class HeroIconMap {
    private static Map<String, Integer> heroIcons;
    public static int getIconForHero(String hero) {
        if (heroIcons == null) {
            initMap();
        }
        if (!heroIcons.containsKey(hero)) {
            throw new IllegalArgumentException("Invalid hero specified");
        }

        return heroIcons.get(hero);
    }

    public static void initMap() {
        heroIcons = new HashMap<String, Integer>();
        heroIcons.put("alchemist", R.drawable.alchemist);
        heroIcons.put("ancient_apparition", R.drawable.ancient_apparition);
        heroIcons.put("antimage", R.drawable.antimage);
        heroIcons.put("axe", R.drawable.axe);
        heroIcons.put("bane", R.drawable.bane);
    }
}
