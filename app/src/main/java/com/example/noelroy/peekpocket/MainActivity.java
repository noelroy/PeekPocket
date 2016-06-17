package com.example.noelroy.peekpocket;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView d1, d2, d3;
    TextView a1, a2, a3;
    CursorLoader cursorLoader;
    public Date curr_date;
    String month;

    Float income, expense, balance, dbAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d1 = (TextView) findViewById(R.id.date1);
        d2 = (TextView) findViewById(R.id.date2);
        d3 = (TextView) findViewById(R.id.date3);

        a1 = (TextView) findViewById(R.id.rupee1);
        a2 = (TextView) findViewById(R.id.rupee2);
        a3 = (TextView) findViewById(R.id.rupee3);

        curr_date = new Date();

        Calendar cal = Calendar.getInstance();       // get calendar instance
        month = cal.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }

        onClickDisplayNamesMain();


        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_add_white_18dp);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setTheme(R.style.AppTheme)
                .build();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void onClickDisplayNamesMain() {
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        cursorLoader = new CursorLoader(this, Uri.parse("content://com.example.contentproviderexample.MyProvider/cte4"), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        StringBuilder ad1 = new StringBuilder();
        StringBuilder ad2 = new StringBuilder();
        StringBuilder ad3 = new StringBuilder();
        String rupee = getResources().getString(R.string.Rs);
        if (cursor != null) {
            cursor.moveToFirst();
            income = 0f;
            expense = 0f;
            balance = 0f;
            int icount = cursor.getCount();
            if(icount>0) {


                String str[] = cursor.getString(cursor.getColumnIndex("date")).split("-");

                while (!cursor.isAfterLast()) {
                    dbAmount = cursor.getFloat(cursor.getColumnIndex("amount"));
                    if ((cursor.getString(cursor.getColumnIndex("type")).equalsIgnoreCase("Income")) && (str[1].equalsIgnoreCase(month))) {

                        income = income + dbAmount;
                    }
                    if ((cursor.getString(cursor.getColumnIndex("type")).equalsIgnoreCase("Expense")) && (str[1].equalsIgnoreCase(month))) {

                        expense = expense + dbAmount;
                    }
                    cursor.moveToNext();
                }
                cursor.close();

                balance = income - expense;
            }

        }
        ad1.append(balance);
        ad2.append(expense);
        ad3.append(income);


        a1.setText(rupee + ad1);
        a2.setText(rupee + ad2);
        a3.setText(rupee + ad3);


        SimpleDateFormat dateformat = new SimpleDateFormat("MM-yyyy");
        String dateFormat = dateformat.format(curr_date);

        d1.setText(dateFormat);
        d2.setText(dateFormat);
        d3.setText(dateFormat);


    }

    public void Main1(View view) {
        Intent intent = new Intent(this, TabsMainActivity.class);
        intent.putExtra("type", "Balance");
        startActivity(intent);
    }

    public void Main2(View view) {
        Intent intent = new Intent(this, TabsMainActivity.class);
        intent.putExtra("type", "Expense");
        startActivity(intent);
    }

    public void Main3(View view) {
        Intent intent = new Intent(this, TabsMainActivity.class);
        intent.putExtra("type", "Income");
        startActivity(intent);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
