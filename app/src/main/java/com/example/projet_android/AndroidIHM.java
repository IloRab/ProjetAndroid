package com.example.projet_android;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import client.IHM;

public class AndroidIHM implements IHM {
   // private final ViewGroup chat_view;
    //  private final Context context;
    private final TextView output;

    private final EditText input;

    private Activity act;

    public AndroidIHM(
            //  ViewGroup chat_view,
            //  Context context,
            Activity act,
            TextView output, EditText input, Button submit){
        //  this.chat_view = chat_view;
        // this.context = context;
        this.input = input;
        this.output = output;
        this.act = act;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synchronized (input){
                    input.notifyAll();
                }
            }
        });
    }

    @Override
    public void get_Display(String msg) {
       // LinearLayout parentLayout = (LinearLayout)findViewById(R.id.parent);
      //  TextView textView = new TextView(context);
     //   textView.setText(msg);
       // chat_view.addView(textView);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(msg);
            }
        });

    }

    @Override
    public String get_Query() {
        String res = "<end>";
        synchronized (input){
            try {
                input.wait();
                res = input.getText().toString();
                input.clearComposingText();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return res;
    }
}
