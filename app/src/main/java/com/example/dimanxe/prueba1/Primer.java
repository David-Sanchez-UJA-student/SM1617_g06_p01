package com.example.dimanxe.prueba1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Primer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.main_frame)==null) {
            FragmentTransaction ft = fm.beginTransaction();
            AuthFragment au = AuthFragment.newInstance("", "");
            ft.add(R.id.main_frame, au);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //TODO: hubiera estado bien tener un menú para mostrar algo más de información de la práctica

}
