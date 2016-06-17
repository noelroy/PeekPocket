package com.example.noelroy.peekpocket;

/**
 * Created by Noel Roy on 14-10-2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PublicprofileFragment extends Fragment {

    private List<Details> detailsList = new ArrayList<>();

    private ListView mListView;

    private DetailsAdapter adapter;

    private Button button;

    private DatePicker datePicker;

    public PublicprofileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.publicprofileview, container, false);

        datePicker=(DatePicker) view.findViewById(R.id.monthPicker);
        button=(Button) view.findViewById(R.id.btnMonth);
        mListView = (ListView) view.findViewById(R.id.list_amt_month);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
                String dateFormat;
                dateFormat = dateformat.format(new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth()));


                //Get the data here!!
                DatabaseHelper dbHandler;
                dbHandler = new DatabaseHelper(getActivity().getApplicationContext());
                detailsList = dbHandler.getMonthDetails(dateFormat);

                //Initiate our adapter
                adapter = new DetailsAdapter(getActivity().getApplicationContext(), R.layout.list_item, detailsList);
                //set adapter to the listview
                if (adapter != null) {
                    mListView.setAdapter(adapter);
                }
            }
        });
        //get an instance of our listview
        return view;
    }
}
