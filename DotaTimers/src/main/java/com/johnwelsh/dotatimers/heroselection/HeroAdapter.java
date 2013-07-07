package com.johnwelsh.dotatimers.heroselection;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.johnwelsh.dotatimers.models.HeroModel;

/**
 * Created by john.welsh on 7/7/13.
 */
public class HeroAdapter extends ArrayAdapter<HeroModel> {
    private Context context;
    private int layoutID;
    private HeroModel[] heroModels;

    public HeroAdapter(Context context, int layoutID, HeroModel[] objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
        this.heroModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutID, parent, false);
        }

        return row;
    }

    static class HeroRowHolder {

    }
}
