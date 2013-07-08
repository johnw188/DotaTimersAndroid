package com.johnwelsh.dotatimers.heroselection;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnwelsh.dotatimers.R;
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

        HeroRowHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutID, parent, false);

            holder = new HeroRowHolder();

            if (row != null) {
                holder.heroIcon = (ImageView) row.findViewById(R.id.heroIcon);
                holder.heroName = (TextView) row.findViewById(R.id.heroName);
                row.setTag(holder);
            }
        } else {
            holder = (HeroRowHolder) row.getTag();
        }

        HeroModel hero = heroModels[position];
        holder.heroIcon.setImageResource(hero.getIconID());
        holder.heroName.setText(hero.getHeroDisplayName());

        return row;
    }

    static class HeroRowHolder {
        ImageView heroIcon;
        TextView heroName;
    }
}
