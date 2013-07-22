package com.johnwelsh.dotatimers.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john.welsh on 7/5/13.
 */
public class HeroModel implements Parcelable {
    private String heroDisplayName;
    private String heroName;
    private int cooldown1;
    private int cooldown2;
    private int cooldown3;
    private int iconID;

    public HeroModel(Parcel parcel) {
        heroDisplayName = parcel.readString();
        heroName = parcel.readString();
        cooldown1 = parcel.readInt();
        cooldown2 = parcel.readInt();
        cooldown3 = parcel.readInt();
        iconID = parcel.readInt();
        secondsToTime = parcel.readInt();
        gameTimeWhenTimingStarted = parcel.readInt();
        currentlyTiming = parcel.readByte() == 1;
    }

    public HeroModel() {
    }

    public HeroModel(String heroName, String heroDisplayName, int cooldown1, int cooldown2, int cooldown3) {
        this.heroName = heroName;
        this.heroDisplayName = heroDisplayName;
        this.cooldown1 = cooldown1;
        this.cooldown2 = cooldown2;
        this.cooldown3 = cooldown3;
        this.iconID = HeroIconMap.getIconForHero(heroName);
    }

    public String getHeroDisplayName() {
        return heroDisplayName;
    }

    public void setHeroDisplayName(String heroDisplayName) {
        this.heroDisplayName = heroDisplayName;
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

    // Timer persistence stuff
    private int secondsToTime;
    private int gameTimeWhenTimingStarted;
    private boolean currentlyTiming;

    public int getSecondsToTime() {
        return secondsToTime;
    }

    public void setSecondsToTime(int secondsToTime) {
        this.secondsToTime = secondsToTime;
    }

    public int getGameTimeWhenTimingStarted() {
        return gameTimeWhenTimingStarted;
    }

    public void setGameTimeWhenTimingStarted(int gameTimeWhenTimingStarted) {
        this.gameTimeWhenTimingStarted = gameTimeWhenTimingStarted;
    }

    public boolean isCurrentlyTiming() {
        return currentlyTiming;
    }

    public void setCurrentlyTiming(boolean currentlyTiming) {
        this.currentlyTiming = currentlyTiming;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(heroDisplayName);
        parcel.writeString(heroName);
        parcel.writeInt(cooldown1);
        parcel.writeInt(cooldown2);
        parcel.writeInt(cooldown3);
        parcel.writeInt(iconID);
        parcel.writeInt(secondsToTime);
        parcel.writeInt(gameTimeWhenTimingStarted);
        parcel.writeByte((byte) (currentlyTiming ? 1 : 0));
    }

    public static final Creator<HeroModel> CREATOR = new Creator<HeroModel>() {
        @Override
        public HeroModel createFromParcel(Parcel parcel) {
            return new HeroModel(parcel);
        }

        @Override
        public HeroModel[] newArray(int i) {
            HeroModel[] models = new HeroModel[i];
            for (int j = 0; j < i; j++) {
                models[j] = new HeroModel();
            }
            return models;
        }
    };
}
