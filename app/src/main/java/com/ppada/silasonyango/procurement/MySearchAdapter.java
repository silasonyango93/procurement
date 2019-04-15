package com.ppada.silasonyango.procurement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SilasOnyango on 3/4/2018.
 */

public class MySearchAdapter extends ArrayAdapter<Searches> {



    LayoutInflater inflater;
    List<Searches> heroList;
    Context context;
    int resource;

    public MySearchAdapter(Context context, int resource, List<Searches> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;


        inflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);

        //getting the hero of the specified position
        Searches hero = heroList.get(position);

        //adding values to the list item
        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());


        //adding a click listener to the button to remove item from the list



        //finally returning the view
        return view;
    }

}
