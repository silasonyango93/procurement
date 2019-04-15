package com.ppada.silasonyango.procurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Belal on 9/14/2017.
 */

//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter2 extends ArrayAdapter<Annotations> {
    DatabaseHelper myDb;
    LayoutInflater inflater;
    Button btContinueBook,btCancelBook;
    android.app.AlertDialog alertDialog;
    //the list values in the List of type hero
    List<Annotations> heroList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public MyListAdapter2(Context context, int resource, List<Annotations> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;

        myDb = new DatabaseHelper(this.context);
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
        Annotations hero = heroList.get(position);

        //adding values to the list item
        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());


        //adding a click listener to the button to remove item from the list

        textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View v = inflater.inflate(R.layout.book_preview, null);

                btContinueBook = (Button) v.findViewById(R.id.btn_continue);
                btCancelBook = (Button) v.findViewById(R.id.btn_cancel_book);
                btContinueBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
//removing the item


                        String val= heroList.get(position).getName();

                        myDb.deleteData(val);

                        Toast.makeText(context,
                                "BookMark Succesfully deleted", Toast.LENGTH_SHORT).show();
                        heroList.remove(position);

                        //reloading the list
                        notifyDataSetChanged();


                    }
                });

                btCancelBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                previewUpload(v);

                //removeHero(position);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View v = inflater.inflate(R.layout.book_preview, null);

                btContinueBook = (Button) v.findViewById(R.id.btn_continue);
                btCancelBook = (Button) v.findViewById(R.id.btn_cancel_book);
                btContinueBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
//removing the item


                        String val= heroList.get(position).getName();

                        myDb.deleteData(val);

                        Toast.makeText(context,
                                "Annotation Succesfully deleted", Toast.LENGTH_SHORT).show();
                        heroList.remove(position);

                        //reloading the list
                        notifyDataSetChanged();


                    }
                });

                btCancelBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                previewUpload(v);

            }
        });


        //finally returning the view
        return view;
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void previewUpload(View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);



        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog = builder.create();
        alertDialog.show();





    }
}
