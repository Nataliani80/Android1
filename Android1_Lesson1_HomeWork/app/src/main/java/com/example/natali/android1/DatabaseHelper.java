package com.example.natali.android1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.db";
    public static final int DATABASE_VERSION = 1;
    static final String TABLE_CITIES = "cities";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CITY = "city";

    //static final String TABLE_WEATHER_FORECAST = "weatherForecast";

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CITIES + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CITY + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if((oldVersion == 1)&& (newVersion == 2)) {
//            String upgradeQuery = "ALTER TABLE " + TABLE_CITIES + " ADD COLUMN ";
//            db.execSQL(upgradeQuery);
    }

    public City addCity(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY, name);
        long insertId = db.insert(TABLE_CITIES,
                null,
                values);
        City newCity = new City();
        newCity.setName(name);
        newCity.setId(insertId);
        return newCity;
    }

    public void editCity(City city, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues editedNote = new ContentValues();
        editedNote.put(COLUMN_ID, city.getId());
        editedNote.put(COLUMN_CITY, description);
        db.update(TABLE_CITIES,
                editedNote,
                COLUMN_ID + "=" + city.getId(),
                null);
    }

    public void deleteCity(City city) {
        SQLiteDatabase db = getWritableDatabase();
        long id = city.getId();
        db.delete(DatabaseHelper.TABLE_CITIES, DatabaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_CITIES, null, null);
    }

    private String[] notesAllColumn = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_CITY,
    };

    public ArrayList<City> query(){
        ArrayList<City> listCities= new ArrayList<City>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CITIES,
                notesAllColumn,
                null,
                null,
                null,
                null,
                null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    City city = new City();
                    city.setId(cursor.getLong(0));
                    city.setName(cursor.getString(1));
                    listCities.add(city);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return listCities;
    }
}

