package com.bewant2be.doit.driverassitant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        SharedPreferences pref = context.getSharedPreferences("config", context.MODE_PRIVATE);
        String licencePlate = pref.getString("licencePlate", "default");
        String enginID  = pref.getString("enginID","default");

        if (licencePlate.equals("default")){
            Intent intent = new Intent(MainActivity.this, ConfigParaActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent("com.bewant2be.doit.AM");
            sendBroadcast(intent);
        }
    }
}
