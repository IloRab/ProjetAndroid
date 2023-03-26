package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import client.BttpClient;
import client.IHM;

public class ServeurChat extends AppCompatActivity {
    private static final int DEFAULT_PORT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur_chat);

        IHM andro_ihm = new AndroidIHM(this, findViewById(R.id.server_output),findViewById(R.id.client_input),findViewById(R.id.submit_btn));


         andro_ihm.display_msg("Hello world!");

         BttpClient c = new BttpClient("192.168.1.44", get_port(), andro_ihm);
         new Thread(c).start();

    }

    private int get_port(){
        return getIntent().getIntExtra(MainActivity.PORT, DEFAULT_PORT);
    }
}