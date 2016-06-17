package com.example.noelroy.peekpocket;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Noel Roy on 09-10-2015.
 */

public class AddActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickAddName(View view) {

        String amount = ((EditText) findViewById(R.id.txtAmount)).getText().toString();
        String name = ((EditText) findViewById(R.id.txtName)).getText().toString();
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);

        if (amount.equalsIgnoreCase(""))
            Toast.makeText(AddActivity.this, "The Amount field cannot be left blank.", Toast.LENGTH_SHORT).show();
        else if (name.equalsIgnoreCase(""))
            Toast.makeText(AddActivity.this, "The Description field cannot be left blank.", Toast.LENGTH_SHORT).show();
        else {
            DatePicker sDate;
            sDate = (DatePicker) findViewById(R.id.mdatePicker1);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = dateformat.format(new Date(sDate.getYear() - 1900, sDate.getMonth(), sDate.getDayOfMonth()));

            Details details = new Details("0", name, dateFormat, Float.valueOf(amount), spinner1.getSelectedItem().toString());
            dbHelper.createContact(details);
            Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG)
                    .show();

            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
