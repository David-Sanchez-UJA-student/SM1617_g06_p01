package com.example.dimanxe.prueba1;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by dimanxe on 29/11/2016.
 */

public class Conection extends AsyncTask<String,Float,String> {
    public static final String PREFS_NAME = "MyPrefsFile";



    @Override
    protected String doInBackground(String... params) {

        Autentication aut = new Autentication();

        aut.setmUser(params[0]);
        aut.setmPass(params[1]);
        aut.setmIP(params[3]);
        aut.mPort = Integer.parseInt(params[2]);
        Socket sClient = null;
        DataOutputStream output = null;

        BufferedReader is = null;
        try {
            SocketAddress sockaddr= new InetSocketAddress("192.168.1.40",6000);
            sClient = new Socket();
            sClient.connect(sockaddr,5000);
            is = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
            output = new DataOutputStream(sClient.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        String resp = "";
        int count = 0;
        if (sClient != null && is != null && output != null) {
            try {
                output.writeBytes("USER USER\r\n");
                output.writeBytes("PASS 12345\r\n");
                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    if (count == 2) {
                        resp = responseLine;
                        break;
                    }
                    if (responseLine.indexOf("OK") != -1) {
                        count += 1;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (resp.indexOf("ERROR") != -1) {
                return "ERROR";// 1 usuario o pass incorrecto
            } else {
                String sID = resp.substring(10);
                //TODO guardar en preferencias compartidas

                return sID;//2 para cuando usuario y pass correcto
            }

        }
        return "ERROR";
    }
}
