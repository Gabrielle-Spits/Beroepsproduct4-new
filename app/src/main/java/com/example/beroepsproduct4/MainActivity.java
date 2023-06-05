package com.example.beroepsproduct4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.beroepsproduct4.Bluetooth.BluetoothSend;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;

public class MainActivity extends AppCompatActivity {

    public static final BluetoothSend bluetoothSend = new BluetoothSend();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnZorgcentrumToevoegen = findViewById(R.id.btnToevoegenZorgcentrum);
        btnZorgcentrumToevoegen.setOnClickListener(view -> {
                    Intent intent = new Intent(view.getContext(), ZorgcentrumToevoegenActivity.class);
                    startActivity(intent);
                }
        );
    }
}