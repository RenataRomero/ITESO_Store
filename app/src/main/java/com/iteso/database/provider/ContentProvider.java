package com.iteso.database.provider;


import com.iteso.pdm18_scrollabletabs.database.*;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ContentProvider extends android.content.ContentProvider {


    private static final String AUTHORITY =
            "com.iteso.database.provider.ContentProvider";
    private static final String PRODUCTS_TABLE = "products";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCTS_TABLE);
    public static final String[] PRODUCT = {""};




    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;
    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#",
                PRODUCTS_ID);
    }

    private DataBaseHandler myDB;
    @Override
    public boolean onCreate() {
        myDB = new DataBaseHandler(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataBaseHandler.TABLE_PRODUCT);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case PRODUCTS_ID:
                queryBuilder.appendWhere(DataBaseHandler.PRODUCT_ID + "="
                        + uri.getLastPathSegment());
                break;
            case PRODUCTS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(myDB.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType(Uri arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri arg0, ContentValues arg1) {
        // TODO Auto-generated method stub
        return null;
    }




    @Override
    public int update(Uri arg0, ContentValues arg1, String arg2,
                      String[] arg3) {
        // TODO Auto-generated method stub
        return 0;
    }

}