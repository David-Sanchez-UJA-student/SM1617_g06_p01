package com.example.dimanxe.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activid3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activid3);
        TextView txt=(TextView) this.findViewById(R.id.textView3);
        Intent i=getIntent();
        String user= i.getStringExtra("User");
        txt.setText(user);
    }
}
