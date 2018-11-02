package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.tools.Constant;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "itesoStore.db";
    private static final int DATABASE_VERSION = 1;

    private static DataBaseHandler dataBaseHandler;

    //TABLES
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_STOREPRODUCT = "StoreProduct";

    //Columns STORE
    public static final String STORE_ID = "IdStore";
    public static final String STORE_NAME = "Name";
    public static final String STORE_PHONE = "Phone";
    public static final String STORE_IDCITY= "IdCity";
    public static final String STORE_THUMBNAIL = "Thumbnail";
    public static final String STORE_LATITUDE = "Latitude";
    public static final String STORE_LONGITUDE = "Longitude";

    //Columns City
    public static final String CITY_ID = "IdCity";
    public static final String CITY_NAME = "Name";

    //Columns Category
    public static final String CATEGORY_ID = "IdCategory";
    public static final String CATEGORY_NAME = "Name";

    //Columns Product
    public static final String PRODUCT_ID = "IdProduct";
    public static final String PRODUCT_TITLE = "Title";
    public static final String PRODUCT_IMAGE = "Image";
    public static final String PRODUCT_IDCATEGORY = "IdCategory";

    //Columns StoreProduct
    public static final String STOREPRODUCT_ID = "IdStoreProduct";
    public static final String STOREPRODUCT_IDPRODUCT = "IdProduct";
    public static final String STOREPRODUCT_IDSTORE = "IdStore";


    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null){
            Log.e("INSTANCIAESNULA", "Entro al if");
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("TABLAS", "Entro al ONCREATE");
        String tableCity = "CREATE TABLE " + TABLE_CITY + " ("
                + CITY_ID + " INTEGER PRIMARY KEY, "
                + CITY_NAME + " TEXT)";

        db.execSQL(tableCity);

        String tableCategory = "CREATE TABLE " + TABLE_CATEGORY + " ("
                + CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + CATEGORY_NAME + " TEXT)";

        db.execSQL(tableCategory);


        String tableProduct = "CREATE TABLE " + TABLE_PRODUCT + " ("
                + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_TITLE + " TEXT, "
                + PRODUCT_IMAGE + " INTEGER, "
                + PRODUCT_IDCATEGORY + ")";

        db.execSQL(tableProduct);

        String tableStore = "CREATE TABLE " + TABLE_STORE + " ("
                + STORE_ID + " INTEGER PRIMARY KEY,"
                + STORE_NAME + " TEXT, "
                + STORE_PHONE + " TEXT, "
                + STORE_THUMBNAIL + " INTEGER, "
                + STORE_LATITUDE + " DOUBLE, "
                + STORE_LONGITUDE + " DOUBLE, "
                + STORE_IDCITY + ")";

        db.execSQL(tableStore);

        String tableStoreProduct = "CREATE TABLE " + TABLE_STOREPRODUCT + " ("
                + STOREPRODUCT_ID + " INTEGER PRIMARY KEY,"
                + STOREPRODUCT_IDPRODUCT + ", "
                + STOREPRODUCT_IDSTORE + ")";

        db.execSQL(tableStoreProduct);

        ContentValues valuesTechnology = new ContentValues();
        valuesTechnology.put(CATEGORY_ID, Constant.FRAGMENT_TECHNOLOGY); // set name
        valuesTechnology.put(CATEGORY_NAME, "Technology");

        db.insert(TABLE_CATEGORY,
                null,  valuesTechnology);

        ContentValues valuesHome= new ContentValues();
        valuesHome.put(CATEGORY_ID, Constant.FRAGMENT_HOME); // set name
        valuesHome.put(CATEGORY_NAME, "Home");

        db.insert(TABLE_CATEGORY,
                null,  valuesHome);

        ContentValues valuesElectronics = new ContentValues();
        valuesElectronics.put(CATEGORY_ID, Constant.FRAGMENT_ELECTRONICS); // set name
        valuesElectronics.put(CATEGORY_NAME, "Electronics");

        db.insert(TABLE_CATEGORY,
                null,  valuesElectronics);

        ContentValues valuesCityZapopan = new ContentValues();
        valuesCityZapopan.put(CITY_NAME, "Zapopan");
        valuesCityZapopan.put(CITY_NAME, "Zapopan"); // set name

        db.insert(TABLE_CITY,
                null,  valuesCityZapopan);

        ContentValues valuesCityGuadalajara = new ContentValues();
        valuesCityGuadalajara.put(CITY_NAME, "Guadalajara"); // set name

        db.insert(TABLE_CITY,
                null,  valuesCityGuadalajara);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}












