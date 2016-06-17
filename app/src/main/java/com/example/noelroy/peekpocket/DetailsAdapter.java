package com.example.noelroy.peekpocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Noel Roy on 02-11-2015.
 */
public class DetailsAdapter extends ArrayAdapter<Details> {

    private Context context;

    //Constructor
    public DetailsAdapter(Context context, int resource,  List<Details> objects) {
        super(context, resource, objects);

        this.context = context;
    }


//The get view is the most crucial part of the adapter, here the listview asks the
//adapter for the row to display

    @Override
    public View getView(int position, View row, ViewGroup parent) {

        //Get an instance of our holder
        Holder holder;


        //Check if this is the first time we are creating this row for the listview
        if (row == null){

            //Row was null and thus we need to get components from the row_details.xml
            holder = new Holder();

            //get the Android's layout inflater service which will read our row_details.xml
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Fill our row view with the xml layout file
            row = inflater.inflate(R.layout.list_item, null);

            //Fill our holder with the text view components
            holder.textDate = (TextView)row.findViewById(R.id.txt_date);
            holder.textId = (TextView)row.findViewById(R.id.txt_id);
            holder.textDesc = (TextView)row.findViewById(R.id.txt_desc);
            holder.textAmount = (TextView)row.findViewById(R.id.txt_amount);

            //This is very imp! attach our holder to the row
            row.setTag(holder);

        }else{

            //row was created before! thus get the holder object from the row tag
            holder = (Holder)row.getTag();
        }


        //At this point we have our row, either created from new or got it from the row tag object
        //we can now fill the data

        //First get our object from the list which is in the position of the listview
        //The position as you can see is passed to the getView method by the listview

        Details details = getItem(position);

        holder.textDate.setText(details.getDate());
        holder.textId.setText("ID: " + details.getId());
        holder.textDesc.setText("Description: " + details.getName());
        holder.textAmount.setText("Amount: " + details.getAmount());

        //we are done formatting our row..return to listview to show
        return row;
    }

    //A holder will be resposable to hold our components to improve listview performance
//We replicate the components we have in the row_details.xml
    private class Holder{

        TextView textId;
        TextView textDate;
        TextView textDesc;
        TextView textAmount;


    }

}