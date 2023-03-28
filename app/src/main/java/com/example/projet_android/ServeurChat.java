package com.example.projet_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

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
         start_client("192.168.1.44", andro_ihm);

         findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent open_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(open_cam,100);
             }
         });

    }
    private void start_client(String ip, IHM ihm){
        BttpClient c = new BttpClient(ip, get_port(), ihm);
        Thread client = new Thread(c);
        client.start();
    }

    private int get_port(){
        return getIntent().getIntExtra(MainActivity.PORT, DEFAULT_PORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        InputImage i = InputImage.fromBitmap(photo, 0);
        scanQrCode(i, findViewById(R.id.client_input));
    }

    private void scanQrCode(InputImage image, EditText input){

        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE
                        ).build();
        BarcodeScanner scanner = BarcodeScanning.getClient(options);

        Task<List<Barcode>> result = scanner.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {

                    public void onSuccess(List<Barcode> barcodes) {
                        if (barcodes.size() != 1)
                            throw new RuntimeException("Nb code baar incorrecte");

                        Barcode barcode = barcodes.get(0);

                        input.setText(barcode.getDisplayValue());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    public void onFailure(@NonNull Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}