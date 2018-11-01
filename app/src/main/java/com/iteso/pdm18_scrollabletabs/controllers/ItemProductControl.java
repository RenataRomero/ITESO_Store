package com.iteso.pdm18_scrollabletabs.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;

import java.util.ArrayList;

public class ItemProductControl {

    public void add(ItemProduct itemProduct, DataBaseHandler dh){

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues valuesProduct = new ContentValues();

        Store store = itemProduct.getStore();
        Category category = itemProduct.getCategory();

        valuesProduct.put(DataBaseHandler.PRODUCT_ID, itemProduct.getCode());
        valuesProduct.put(DataBaseHandler.PRODUCT_IMAGE, itemProduct.getImage());
        valuesProduct.put(DataBaseHandler.PRODUCT_TITLE, itemProduct.getTitle());
        valuesProduct.put(DataBaseHandler.PRODUCT_IDCATEGORY, category.getId());

        db.insert(DataBaseHandler.TABLE_PRODUCT, null, valuesProduct);

        ContentValues valuesStoreProduct = new ContentValues();

        valuesStoreProduct.put(DataBaseHandler.PRODUCT_ID, itemProduct.getCode());
        valuesStoreProduct.put(DataBaseHandler.STORE_ID, store.getId());

        db.insert(DataBaseHandler.TABLE_STOREPRODUCT, null, valuesProduct);

        try {
            db.close();
        }catch (Exception e){

        }
    }


    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + DataBaseHandler.PRODUCT_ID + ","
                + DataBaseHandler.PRODUCT_IDCATEGORY + ", "
                + DataBaseHandler.PRODUCT_IMAGE + ", "
                + DataBaseHandler.PRODUCT_TITLE + ", "
                + " FROM " + DataBaseHandler.TABLE_PRODUCT
                + " WHERE " + DataBaseHandler.PRODUCT_IDCATEGORY + " = " + idCategory;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();

            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));

            itemProduct.setDescription(cursor.getString(2));

            itemProducts.add(itemProduct);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){
        }

        return itemProducts;
    }

}
