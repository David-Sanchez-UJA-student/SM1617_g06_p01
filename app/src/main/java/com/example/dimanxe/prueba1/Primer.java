package com.example.dimanxe.prueba1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Primer extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        SharedPreferences pr=getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if(pr.contains("user")){
            Logeo log=null;
            log.setUser(pr.getString("user",""));
            log.setExpires(pr.getString("Exp",""));
            log.setSid(pr.getString("sID",""));

            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd-H-m-s");
            try {
                if(dt1.parse(log.getExpires()).getTime()+360000<dt1.parse(log.getExpires()).getTime()){
                    Intent i;
                    i=new Intent(this,Activid3.class);
                    startActivity(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.main_frame)==null) {
            FragmentTransaction ft = fm.beginTransaction();
            AuthFragment au = AuthFragment.newInstance("", "",6000,"");
            ft.add(R.id.main_frame, au);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //TODO: hubiera estado bien tener un menú para mostrar algo más de información de la práctica

}
