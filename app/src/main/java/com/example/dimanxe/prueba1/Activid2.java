package com.example.dimanxe.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activid2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activid2);
        Intent i=getIntent();
        Bundle ex= i.getExtras();
        Autentication aut;

    }
}
