package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.beans.User;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    public static final String MY_PREFERENCES= "com.iteso.PDM18_ScrollableTabs.PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

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

