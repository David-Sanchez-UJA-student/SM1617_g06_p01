package com.example.dimanxe.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Activid2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activid2);
        Intent i=getIntent();

        Autentication aut=new Autentication();

        aut.setmUser(i.getStringExtra("User"));
        aut.setmPass(i.getStringExtra("Pass"));
        aut.setmIP(i.getStringExtra("IP"));
        TextView txt =(TextView) this.findViewById(R.id.textView2);
        txt.setText("Nombre:"+aut.getmUser()+"IP:"+aut.getmIP());
        //TODO: Muestras la información sin ni siquiera espacios
    }
}
