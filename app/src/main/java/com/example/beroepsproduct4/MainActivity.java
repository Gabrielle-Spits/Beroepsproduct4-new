package com.example.beroepsproduct4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.beroepsproduct4.Bluetooth.BluetoothSend;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final BluetoothSend bluetoothSend = new BluetoothSend();
    public static Context applicatiion;
    public static NotificationManager notficatie;
    public static String device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicatiion = getApplicationContext();
        notficatie = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Button btnZorgcentrumToevoegen = findViewById(R.id.btnToevoegenZorgcentrum);
        btnZorgcentrumToevoegen.setOnClickListener(view -> {
                    Intent intent = new Intent(view.getContext(), ZorgcentrumToevoegenActivity.class);
                    startActivity(intent);
                }
        );
        tread();
    }



    public static void tread(){
        Thread getData = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    if (bluetoothSend.isConnected()) {
                        bluetoothSend.getBluetoothw();
                    }
                } catch (IOException e) {
                    Log.e("IOException", e.getMessage());
                } catch (NullPointerException e) {
                    Log.e("NullPointerException", e.getMessage());
                }
            }
        });
        getData.start();
    }
}