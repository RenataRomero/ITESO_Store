package com.iteso.pdm18_scrollabletabs.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

public class StoreControl {
    public void addStore(Store store, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.STORE_ID, store.getId());
        values.put(DataBaseHandler.STORE_IDCITY, store.getCity().getId());
        values.put(DataBaseHandler.STORE_NAME, store.getName());
        values.put(DataBaseHandler.STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.STORE_THUMBNAIL, store.getThumbnail());
        values.put(DataBaseHandler.STORE_LATITUDE, store.getLatitude());
        values.put(DataBaseHandler.STORE_LONGITUDE, store.getLongitude());

        db.insert(DataBaseHandler.TABLE_STORE, null, values);

        try {
            db.close();
        } catch (Exception e) {

        }
    }

    public ArrayList<Store> getStores(DataBaseHandler dh) {
        ArrayList<Store> stores = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + "s." + DataBaseHandler.STORE_ID + ", "
                + "s." + DataBaseHandler.STORE_IDCITY + ", "
                + "s." + DataBaseHandler.STORE_LONGITUDE + ", "
                + "s." + DataBaseHandler.STORE_LATITUDE + ", "
                + "s." + DataBaseHandler.STORE_NAME + ", "
                + "s." + DataBaseHandler.STORE_PHONE + ", "
                + "s." + DataBaseHandler.STORE_THUMBNAIL + ", "
                + "c." + DataBaseHandler.CITY_NAME
                + " FROM " + DataBaseHandler.TABLE_STORE + " s INNER JOIN " + DataBaseHandler.TABLE_CITY + " c ON s." + DataBaseHandler.STORE_IDCITY + " = c." + DataBaseHandler.CITY_ID;

        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()) {
            Store store = new Store();
            City city = new City();

            store.setId(cursor.getInt(0));
            city.setId(cursor.getInt(1));
            store.setLongitude(cursor.getDouble(2));
            store.setLatitude(cursor.getDouble(3));
            store.setName(cursor.getString(4));
            store.setPhone(cursor.getString(5));
            store.setThumbnail(cursor.getInt(6));
            city.setName(cursor.getString(7));

            store.setCity(city);

            stores.add(store);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }

        return stores;
    }

    public Store getStoreById(int idStore, DataBaseHandler dh) {

        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + "s." + DataBaseHandler.STORE_ID + ", "
                + "s." + DataBaseHandler.STORE_IDCITY + ", "
                + "s." + DataBaseHandler.STORE_LONGITUDE + ", "
                + "s." + DataBaseHandler.STORE_LATITUDE + ", "
                + "s." + DataBaseHandler.STORE_NAME + ", "
                + "s." + DataBaseHandler.STORE_PHONE + ", "
                + "s." + DataBaseHandler.STORE_THUMBNAIL + ", "
                + "c." + DataBaseHandler.CITY_NAME
                + " FROM " + DataBaseHandler.TABLE_STORE + " s INNER JOIN " + DataBaseHandler.TABLE_CITY + " c ON s." + DataBaseHandler.STORE_IDCITY + " = c." + DataBaseHandler.CITY_ID
                + " WHERE " + DataBaseHandler.STORE_ID + " = " + idStore + ";";

        Cursor cursor = db.rawQuery(select, null);

        Store store = new Store();
        City city = new City();

        store.setId(cursor.getInt(0));
        city.setId(cursor.getInt(1));
        store.setLongitude(cursor.getDouble(2));
        store.setLatitude(cursor.getDouble(3));
        store.setName(cursor.getString(4));
        store.setPhone(cursor.getString(5));
        store.setThumbnail(cursor.getInt(6));
        city.setName(cursor.getString(7));

        store.setCity(city);


        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }

        return store;

    }
}