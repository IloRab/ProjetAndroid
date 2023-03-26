package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String PORT = "com.smile.biblio.PORT";

    private static List<Integer> btns = new ArrayList<>();
    private static Map<Integer, Integer> ports = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns.add(R.id.reservation_btn);
        btns.add(R.id.emprunt_btn);
        btns.add(R.id.retour_btn);

        ports.put(R.id.reservation_btn,3000);
        ports.put(R.id.emprunt_btn,4000);
        ports.put(R.id.retour_btn,5000);

        set_onclick(get_all(btns),new View.OnClickListener() {
            public void onClick(View view) {
                to_chat(ports.get(view.getId()));
            }
        });

        }

    private List<Button> get_all(List<Integer> btns_id ){
       List<Button> buttons = new ArrayList<Button>();
       for (Integer e : btns_id){
            buttons.add(findViewById(e));
       }
       return buttons;
    }

    private void set_onclick(List<Button> btns, View.OnClickListener listener){
        for(Button e : btns) {
            e.setOnClickListener(listener);
        }
    }

    private void to_chat(int port){
        Intent intent = new Intent(this, ServeurChat.class);
        intent.putExtra(PORT, port);
        startActivity(intent);
    }
}