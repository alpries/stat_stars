package com.example.stat_stars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Custom adapter for displaying Brawler objects in a ListView
public class BrawlerAdapter extends BaseAdapter {
    ArrayList<Brawler> brawlers; // List of Brawler objects to display
    Context context;             // Context used for inflating views and accessing resources

    // Constructor to initialize the adapter with the list and context
    public BrawlerAdapter(ArrayList<Brawler> brawlers, Context context) {
        this.brawlers = brawlers;
        this.context = context;
    }

    // Returns the number of items in the data set
    @Override
    public int getCount() {
        return brawlers.size();
    }

    // Returns the Brawler object at the specified position
    @Override
    public Object getItem(int i) {
        return brawlers.get(i);
    }

    // Returns the ID for the item (not used here, so just returns 0)
    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Returns the view for each item in the ListView
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Inflate a new view from layout_brawler.xml if it hasn't been created yet
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_brawler, viewGroup, false);

        // Get the current Brawler object
        Brawler brawler = brawlers.get(i);

        // Get references to UI elements in the item layout
        TextView brawlerTv = view.findViewById(R.id.brawlerTv);   // Brawler name text view
        TextView trophiesTv = view.findViewById(R.id.trophiesTv); // Trophies count text view
        ImageView brawlerIv = view.findViewById(R.id.brawlerIv);  // Brawler image view

        // Set text views with the brawler's data
        brawlerTv.setText(brawler.brawlerName);
        trophiesTv.setText(brawler.trophies);

        // Prepare the image name by cleaning the brawler name (remove spaces, symbols, numbers, etc.)
        String name = brawler.brawlerName.toLowerCase();
        name = name.replace("&", "")
                .replace(" ", "_")
                .replace("8", "") // Could be removed or improved; '8' seems unnecessary here
                .replace("-", "_")
                .replace(".", "");

        // Get the resource ID for the image with the processed name from the drawable folder
        int imageId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

        // Set the image resource to the ImageView
        brawlerIv.setImageResource(imageId);

        // Return the fully populated view
        return view;
    }
}
