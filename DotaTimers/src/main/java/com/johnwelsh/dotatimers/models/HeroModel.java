package com.johnwelsh.dotatimers.models;

/**
 * Created by john.welsh on 7/5/13.
 */
public class HeroModel {
    private String heroName;
    private int cooldown1;
    private int cooldown2;
    private int cooldown3;
    private int iconID;

    public HeroModel(String heroName, int cooldown1, int cooldown2, int cooldown3) {
        this.heroName = heroName;
        this.cooldown1 = cooldown1;
        this.cooldown2 = cooldown2;
        this.cooldown3 = cooldown3;
        this.iconID = HeroIconMap.getIconForHero(heroName);
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
        this.iconID = HeroIconMap.getIconForHero(heroName);
    }

    public int getCooldown1() {
        return cooldown1;
    }

    public void setCooldown1(int cooldown1) {
        this.cooldown1 = cooldown1;
    }

    public int getCooldown2() {
        return cooldown2;
    }

    public void setCooldown2(int cooldown2) {
        this.cooldown2 = cooldown2;
    }

    public int getCooldown3() {
        return cooldown3;
    }

    public int getIconID() {
        return iconID;
    }

    public void setCooldown3(int cooldown3) {
        this.cooldown3 = cooldown3;
    }
}
