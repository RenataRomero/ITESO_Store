package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.controllers.CityController;
import com.iteso.pdm18_scrollabletabs.controllers.StoreController;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;

import com.iteso.pdm18_scrollabletabs.beans.User;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    public static final String MY_PREFERENCES= "com.iteso.PDM18_ScrollableTabs.PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplashScreen.this);
        CityController cityController = new CityController();
        StoreController storeController = new StoreController();


        ArrayList<Store> stores = storeController.getStores(dh);
        ArrayList<City> cities = cityController.getCities(dh);

        Log.e("CIUDADES", cities.get(1).getName());

        if(stores.size() == 0) {
            Log.e("TIENDAS", "No hay tiendas, entra al if");

            Store store1 = new Store();
            store1.setId(1);
            store1.setName("Best Buy");
            City city = cities.get(0);

            store1.setCity(city);
            store1.setPhone("12345678");
            store1.setThumbnail(R.drawable.bestbuy);
            store1.setLatitude(20.7118858);
            store1.setLongitude(-103.4131599);

            storeController.addStore(store1, dh);

            Store store2 = new Store();
            store2.setId(2);
            store2.setName("Zara Home");

            City city2 = cities.get(1);
            store2.setCity(city2);
            store2.setPhone("12345678");
            store2.setThumbnail(R.drawable.bestbuy);
            store2.setLatitude(20.6774144);
            store2.setLongitude(-103.4339508);

            storeController.addStore(store2, dh);

            Store store3 = new Store();
            store3.setId(3);
            store3.setName("Palacio de Hierro");

            store3.setCity(city);
            store3.setPhone("12345678");
            store3.setThumbnail(R.drawable.bestbuy);
            store3.setLatitude(20.7118858);
            store3.setLongitude(-103.4131599);

            storeController.addStore(store3, dh);

        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                if(user.isLogged){
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivitySplashScreen.this,ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,2000);
    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        User user = new User();
        user.setUsername(sharedPreferences.getString("NAME", "UNKNOWN"));
        user.setPassword(sharedPreferences.getString("PASSWORD", "1234"));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        return user;
    }

}

