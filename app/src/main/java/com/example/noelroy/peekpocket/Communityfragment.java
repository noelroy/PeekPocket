package com.example.noelroy.peekpocket;

/**
 * Created by Noel Roy on 14-10-2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.noelroy.peekpocket.MyProvider.*;

public class Communityfragment extends Fragment implements OnItemLongClickListener {

    private List<Details> detailsList = new ArrayList<>();

    private ListView mListView;

    private DetailsAdapter adapter;

    public Communityfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.communityview, container, false);

        //get an instance of our listview
        mListView = (ListView) rootView.findViewById(R.id.list_amt);

        //Get the data here!!
        DatabaseHelper dbHandler;
        dbHandler = new DatabaseHelper(getActivity().getApplicationContext());
        detailsList = dbHandler.getAllDetails();

        //Initiate our adapter
        adapter = new DetailsAdapter(getActivity().getApplicationContext(), R.layout.list_item, detailsList);

        //set adapter to the listview
        if (adapter != null) {
            mListView.setAdapter(adapter);
        }

        mListView.setOnItemLongClickListener(this);

        return rootView;

    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

        final Details details = (Details) parent.getItemAtPosition(position);

        AlertDialog.Builder main_builder = new AlertDialog.Builder(getActivity());
        main_builder.setItems(R.array.long_menu_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface main_dialog, int item) {
                if (item == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure you want to delete this entry?").setTitle("Delete Entry");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseHelper dbHandler;
                            dbHandler = new DatabaseHelper(getActivity().getApplicationContext());
                            dbHandler.delete(details);
                            Toast.makeText(getActivity().getBaseContext(), "Record Deleted", Toast.LENGTH_LONG)
                                    .show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        AlertDialog alert = main_builder.create();
        alert.show();

        return true;
    }
}