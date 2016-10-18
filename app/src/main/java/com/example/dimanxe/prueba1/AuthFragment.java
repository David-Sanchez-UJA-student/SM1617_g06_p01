package com.example.dimanxe.prueba1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by dimanxe on 06/10/2016.
 */

public class AuthFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mUser="";
    private String mPass="";

    public AuthFragment(){

    }
    public static AuthFragment newInstance(String user, String pass) {
        AuthFragment fragment = new AuthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getString(ARG_PARAM1);
            mPass = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View fragmento = inflater.inflate(R.layout.autfrag, container, false);

        final EditText user = (EditText)fragmento.findViewById(R.id.Nombre);
        final EditText pass = (EditText)fragmento.findViewById(R.id.Pass);
        final EditText ip=(EditText)fragmento.findViewById(R.id.IP);
        user.setText(mUser);
        pass.setText(mPass);
        Button boton = (Button)fragmento.findViewById(R.id.boton1);
        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Autentication aut= new Autentication();
                aut.setmUser(user.getText().toString());
                aut.setmPass(pass.getText().toString());
                aut.setmIP(ip.getText().toString());
                Intent i;
                i = new Intent(fragmento.getContext(),Activid2.class);
                i.putExtra("datos",aut.getClass());
                startActivity(i);
            }

                                 });
        //TODO añadir el boton y el evento para enviar la informacion, añadir tambien el paso de parametros entre actividades.

        return fragmento;

    }

}
