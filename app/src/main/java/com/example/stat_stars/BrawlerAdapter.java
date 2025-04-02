package com.example.stat_stars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BrawlerAdapter extends BaseAdapter {
    ArrayList<Brawler> brawlers;
    Context context;

    public BrawlerAdapter(ArrayList<Brawler> brawlers, Context context) {
        this.brawlers = brawlers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return brawlers.size();
    }

    @Override
    public Object getItem(int i) {
        return brawlers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_brawler, viewGroup, false);
        Brawler brawler = brawlers.get(i);
        TextView brawlerTv = view.findViewById(R.id.brawlerTv);
        TextView trophiesTv = view.findViewById(R.id.trophiesTv);
        ImageView brawlerIv = view.findViewById(R.id.brawlerIv);
        brawlerTv.setText(brawler.brawlerName);
        trophiesTv.setText(brawler.trophies);
        int imageId = context.getResources().getIdentifier(
                brawler.brawlerName.toLowerCase(), "drawable", context.getPackageName()
        );
        brawlerIv.setImageResource(imageId);
        return view;

    }
}