package com.iteso.pdm18_scrollabletabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.DataBase.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.controllers.CategoryControl;
import com.iteso.pdm18_scrollabletabs.controllers.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {

    Spinner imagenes, categorias, tiendas;
    EditText titulo;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        imagenes = findViewById(R.id.activity_item_spinner_imagenes);
        categorias = findViewById(R.id.activity_item_spinner_categorias);
        tiendas = findViewById(R.id.activity_item_spinner_tiendas);

        titulo = findViewById(R.id.activity_item_edit_text_titulo);
        guardar = findViewById(R.id.activity_item_button_guardar);

        DataBaseHandler dh = DataBaseHandler.getInstance(ActivityItem.this);
        CategoryControl categoryControl = new CategoryControl();
        StoreControl storeControl = new StoreControl();

        ArrayList<Category> categories = categoryControl.getCategories(dh);
        ArrayList<Store> stores = storeControl.getStores(dh);

        ArrayList<String> namesCategories = new ArrayList<String>();
        ArrayList<String> namesStores = new ArrayList<String>();
        ArrayList<String> namesImages = new ArrayList<String>();

        for(int i = 0; i < categories.size(); i++){

            namesCategories.add(categories.get(i).getName());

        }

        for(int i = 0; i < stores.size(); i++){

            namesStores.add(stores.get(i).getName() + " " + stores.get(i).getCity().getName());

        }

        ArrayAdapter<String> namesCategoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, namesCategories);
        ArrayAdapter<String> namesStoresAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, namesStores);
        ArrayAdapter<String> namesImagesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, namesImages);

        categorias.setAdapter(namesCategoriesAdapter);
        tiendas.setAdapter(namesStoresAdapter);
        imagenes.setAdapter(namesImagesAdapter);



    }
}