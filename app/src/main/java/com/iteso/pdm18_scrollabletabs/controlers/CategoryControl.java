package com.iteso.pdm18_scrollabletabs.controlers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.DataBase.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.beans.Category;

import java.util.ArrayList;

public class CategoryControl {

    public void addCategory(Category category, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.CATEGORY_ID, category.getId());
        values.put(DataBaseHandler.CATEGORY_NAME, category.getName());

        db.insert(DataBaseHandler.TABLE_CATEGORY, null, values);

        try {
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<Category> getCategory (DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + DataBaseHandler.CATEGORY_ID + ","
                + DataBaseHandler.CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){
        }

        return categories;
    }

    public void deleteCategory(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_CATEGORY, "id = ?",
                new String[]{String.valueOf(id)});
        try{
            db.close();
        }catch (Exception e){
        }
    }
}
