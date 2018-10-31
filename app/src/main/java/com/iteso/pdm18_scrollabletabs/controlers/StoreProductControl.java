package com.iteso.pdm18_scrollabletabs.controlers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.DataBase.DataBaseHandler;
import com.iteso.pdm18_sesion16.beans.Student;

import java.util.ArrayList;

public class StudentControl {
    public void addStudent(StoreProduct storeProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.STUDENT_ID, storeProduct.getId());
        values.put(DataBaseHandler.STUDENT_NAME, storeProduct.getName());
        values.put(DataBaseHandler.STUDENT_EMAIL, storeProduct.getEmail());

        db.insert(DataBaseHandler.TABLE_STUDENT, null, values);

        try {
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<Student> getStudents (DataBaseHandler dh){
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "Select " + DataBaseHandler.STUDENT_ID + ","
                + DataBaseHandler.STUDENT_NAME + ","
                + DataBaseHandler.STUDENT_EMAIL
                + " FROM " + DataBaseHandler.TABLE_STUDENT;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            students.add(student);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){
        }

        return students;
    }

    public void deleteStudent(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_STUDENT, "id = ?",
                new String[]{String.valueOf(id)});
        try{
            db.close();
        }catch (Exception e){
        }
    }
}