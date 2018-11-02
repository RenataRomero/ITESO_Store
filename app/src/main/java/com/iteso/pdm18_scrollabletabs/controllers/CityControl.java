package com.iteso.pdm18_scrollabletabs.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.beans.City;

import java.util.ArrayList;

public class CityControl {

    public void addCity(City city, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.CITY_ID, city.getId());
        values.put(DataBaseHandler.CITY_NAME, city.getName());

        db.insert(DataBaseHandler.TABLE_CITY, null, values);

        try {
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<City> getCities (DataBaseHandler dh){
        ArrayList<City> cities = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + DataBaseHandler.CITY_ID + ","
                + DataBaseHandler.CITY_NAME
                + " FROM " + DataBaseHandler.TABLE_CITY;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(0));
            city.setName(cursor.getString(1));
            cities.add(city);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){
        }

        return cities;
    }

    public void deleteCity(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_CITY, "id = ?",
                new String[]{String.valueOf(id)});
        try{
            db.close();
        }catch (Exception e){
        }
    }


}
