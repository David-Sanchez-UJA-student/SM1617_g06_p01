package com.example.dimanxe.prueba1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
        aut.mPort=Integer.parseInt(i.getStringExtra("port"));
        TextView txt =(TextView) this.findViewById(R.id.textView2);
        txt.setText("Nombre:"+aut.getmUser()+"   IP:"+aut.getmIP());
        new Conection().execute("USER","12345","6000","192.168.1.40");


    }

    private class Conexion extends AsyncTask<String,Float,Integer>{
        public static final String PREFS_NAME="MyPrefsFile";
        @Override
        protected Integer doInBackground(String... params) {
            Intent i=getIntent();
            Autentication aut=new Autentication();

            aut.setmUser(i.getStringExtra("User"));
            aut.setmPass(i.getStringExtra("Pass"));
            aut.setmIP(i.getStringExtra("IP"));
            aut.mPort=Integer.parseInt(i.getStringExtra("port"));
            Socket sClient=null;
            DataOutputStream output=null;

            BufferedReader is = null;
            try{
                sClient=new Socket(aut.getmIP(),aut.mPort);
                is = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
                output=new DataOutputStream(sClient.getOutputStream());
            }catch (IOException e){
                System.out.println(e);
            }
            String resp="";
            int count=0;
            if(sClient!=null&&is!=null&&output!=null){
                try {
                    output.writeBytes("USER USER\r\n");
                    output.writeBytes("PASS 12345\r\n");
                    String responseLine;
                    while((responseLine=is.readLine())!=null){
                        if(count==2){
                            resp=responseLine;
                            break;
                        }
                        if(responseLine.indexOf("OK")!=-1){
                            count+=1;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(resp.indexOf("ERROR")!=-1){
                    return 1;// 1 usuario o pass incorrecto
                }else{
                    String sID=resp.substring(10);
                    //TODO guardar en preferencias compartidas
                    SharedPreferences.Editor editor=getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("SID",sID);
                    return 2;//2 para cuando usuario y pass correcto
                }

            }
            return null;
        }
    }
}
