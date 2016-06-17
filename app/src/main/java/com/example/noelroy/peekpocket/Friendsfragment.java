package com.example.noelroy.peekpocket;

/**
 * Created by Noel Roy on 14-10-2015.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Friendsfragment extends Fragment {

    private List<Details> detailsList = new ArrayList<>();
    private ListView mListView;
    private DetailsAdapter adapter;

    private EditText fromDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
    private String dateFormat;

    public Friendsfragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.friendsview, container, false);
        fromDateEtxt = (EditText) view.findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();
        mListView = (ListView) view.findViewById(R.id.list_amt_date);

        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        dateFormat=dateFormatter.format(newDate.getTime());
                        fromDateEtxt.setText(dateFormat);
                        DatabaseHelper dbHandler;
                        dbHandler = new DatabaseHelper(getActivity().getApplicationContext());
                        detailsList = dbHandler.getDateDetails(dateFormat);

                        //Initiate our adapter
                        adapter = new DetailsAdapter(getActivity().getApplicationContext(), R.layout.list_item, detailsList);
                        //set adapter to the listview
                        if (adapter != null) {
                            mListView.setAdapter(adapter);
                        }
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.show();



            }
        });
        return view;
    }


}

