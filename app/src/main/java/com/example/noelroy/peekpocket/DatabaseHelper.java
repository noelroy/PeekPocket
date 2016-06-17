package com.example.noelroy.peekpocket;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String id = "id";
    static final String name = "name";
    static final String date = "date";
    static final String amount = "amount";
    static final String type = "type";
    static final String DATABASE_NAME = "mydb4";
    static final String TABLE_NAME = "names4";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " amount REAL NOT NULL, "
            + " date TEXT NOT NULL, "
            + " type TEXT NOT NULL, "
            + " name TEXT NOT NULL);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    public Context myContext;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Details> getAllDetails() {

        //create a new list in which we put all persons
        List<Details> detailsList = new ArrayList<>();

        TabsMainActivity currentType = new TabsMainActivity();

        String typetemp = currentType.gettype(myContext);

        SQLiteDatabase db = getWritableDatabase();
        String query;
        if (typetemp.equalsIgnoreCase("balance")) {
            query = "SELECT * FROM " + TABLE_NAME;
        } else {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE type = '" + typetemp + "'";
        }

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results


        if (c != null) {
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {

                //create new details object
                Details details = new Details(c.getString(c.getColumnIndex(id)), c.getString(c.getColumnIndex(name)), c.getString(c.getColumnIndex(date)), c.getFloat(c.getColumnIndex(amount)), c.getString(c.getColumnIndex(type)));

                //Here use static declared on top of the class..don't use "" for the table column

                detailsList.add(details);


                c.moveToNext();
            }

            c.close();
        }

        db.close();

        //return our list of persons
        return detailsList;

    }

    public List<Details> getDateDetails(String dateFormat) {

        //create a new list in which we put all persons

        List<Details> detailsList = new ArrayList<>();

        TabsMainActivity currentType = new TabsMainActivity();

        String typetemp = currentType.gettype(myContext);

        SQLiteDatabase db = getWritableDatabase();
        String query;
        if (typetemp.equalsIgnoreCase("balance")) {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE date = '" + dateFormat + "'";
        } else {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE type = '" + typetemp + "' AND " + " date = '" + dateFormat + "'";
        }

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results


        if (c != null) {
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {

                //create new details object
                Details details = new Details(c.getString(c.getColumnIndex(id)), c.getString(c.getColumnIndex(name)), c.getString(c.getColumnIndex(date)), c.getFloat(c.getColumnIndex(amount)), c.getString(c.getColumnIndex(type)));

                //Here use static declared on top of the class..don't use "" for the table column

                detailsList.add(details);


                c.moveToNext();
            }

            c.close();
        }

        db.close();

        //return our list of persons
        return detailsList;

    }

    public List<Details> getMonthDetails(String dateFormat) {

        //create a new list in which we put all persons
        List<Details> detailsList = new ArrayList<>();

        TabsMainActivity currentType = new TabsMainActivity();

        String typetemp = currentType.gettype(myContext);

        SQLiteDatabase db = getWritableDatabase();
        String query;
        if (typetemp.equalsIgnoreCase("balance")) {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE date like '" + dateFormat + "%'";
        } else {
            query = "SELECT * FROM " + TABLE_NAME + " WHERE type = '" + typetemp + "' AND " + " date like '" + dateFormat + "%'";
        }

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results


        if (c != null) {
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {

                //create new details object
                Details details = new Details(c.getString(c.getColumnIndex(id)), c.getString(c.getColumnIndex(name)), c.getString(c.getColumnIndex(date)), c.getFloat(c.getColumnIndex(amount)), c.getString(c.getColumnIndex(type)));

                //Here use static declared on top of the class..don't use "" for the table column

                detailsList.add(details);


                c.moveToNext();
            }

            c.close();
        }

        db.close();

        //return our list of persons
        return detailsList;

    }

    public void createContact(Details details) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name,details.getName());
        contentValues.put(date,details.getDate());
        contentValues.put(amount,details.getAmount());
        contentValues.put(type,details.getType());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }

    public void delete(Details details) {
        SQLiteDatabase db = getWritableDatabase();
        String query;
        query = "DELETE FROM " + TABLE_NAME + " WHERE id = '" + details.getId() + "'";
        db.execSQL(query);
        db.close();
    }

}
