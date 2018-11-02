package com.iteso.pdm18_scrollabletabs.controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;

import java.util.ArrayList;

public class ItemProductController {

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){


        ContentValues valuesProduct = new ContentValues();
        ArrayList<ItemProduct> itemProducts = getItemProducts(dh);
        SQLiteDatabase db = dh.getWritableDatabase();
        Store store = itemProduct.getStore();
        Category category = itemProduct.getCategory();

        int productsSize = itemProducts.size();

        if(itemProducts.size() == 0)
            valuesProduct.put(DataBaseHandler.PRODUCT_ID, 0);
        else
            valuesProduct.put(DataBaseHandler.PRODUCT_ID, productsSize);
        valuesProduct.put(DataBaseHandler.PRODUCT_IMAGE, itemProduct.getImage());
        valuesProduct.put(DataBaseHandler.PRODUCT_TITLE, itemProduct.getTitle());
        valuesProduct.put(DataBaseHandler.PRODUCT_IDCATEGORY, category.getId());

        db.insert(DataBaseHandler.TABLE_PRODUCT, null, valuesProduct);

        Log.e("INSERTPRODUCTS", "Se inserto el producto");

        ContentValues valuesStoreProduct = new ContentValues();

        if(itemProducts.size() == 0)
            valuesStoreProduct.put(DataBaseHandler.STOREPRODUCT_ID, 1);
        else
            valuesStoreProduct.put(DataBaseHandler.STOREPRODUCT_IDPRODUCT, productsSize);
        valuesStoreProduct.put(DataBaseHandler.STOREPRODUCT_IDSTORE, store.getId());

        Log.e("ADDITEMPRODUCTOS", String.valueOf(itemProduct.getCode()));
        Log.e("ADDITEMPRODUCTOS", String.valueOf(store.getId()));

        db.insert(DataBaseHandler.TABLE_STOREPRODUCT, null, valuesStoreProduct);

        try {
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<ItemProduct> getItemProducts(DataBaseHandler dh){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String selectProduct = "Select " + DataBaseHandler.PRODUCT_ID + ", "
                + DataBaseHandler.PRODUCT_IDCATEGORY + ", "
                + DataBaseHandler.PRODUCT_IMAGE + ", "
                +  DataBaseHandler.PRODUCT_TITLE
                + " FROM " + DataBaseHandler.TABLE_PRODUCT;

        Cursor cursorProduct = db.rawQuery(selectProduct, null);


        while(cursorProduct.moveToNext()){
            Category category = new Category();

            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursorProduct.getInt(0));
            itemProduct.setImage(cursorProduct.getInt(2));
            itemProduct.setTitle(cursorProduct.getString(3));

            itemProducts.add(itemProduct);
        }
        try {
            cursorProduct.close();
            db.close();
        }catch (Exception e){
        }

        return itemProducts;
    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String selectProduct = "Select " + DataBaseHandler.PRODUCT_ID + ", "
                + DataBaseHandler.PRODUCT_IDCATEGORY + ", "
                + DataBaseHandler.PRODUCT_IMAGE + ", "
                +  DataBaseHandler.PRODUCT_TITLE
                + " FROM " + DataBaseHandler.TABLE_PRODUCT
                + " WHERE " + DataBaseHandler.PRODUCT_IDCATEGORY + " = " + idCategory;

        Cursor cursorProduct = db.rawQuery(selectProduct, null);

        String selectCategory = "Select " + DataBaseHandler.CATEGORY_ID + ","
                + DataBaseHandler.CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY
                + " WHERE " + DataBaseHandler.CATEGORY_ID + " = " + idCategory;

        Cursor cursorCategory = db.rawQuery(selectCategory, null);
        cursorCategory.moveToNext();


        while(cursorProduct.moveToNext()){
            Category category = new Category();
            Store store = new Store();

            category.setId(cursorCategory.getInt(0));
            category.setName(cursorCategory.getString(1));

            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursorProduct.getInt(0));

            itemProduct.setCategory(category);
            itemProduct.setStore(store);
            itemProduct.setImage(cursorProduct.getInt(2));
            itemProduct.setTitle(cursorProduct.getString(3));

            itemProducts.add(itemProduct);
        }
        try {
            cursorProduct.close();
            db.close();
        }catch (Exception e){
        }

        return itemProducts;
    }



}
