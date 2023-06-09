package com.example.beroepsproduct4.schermen.Rollatorgegevens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.beroepsproduct4.Bluetooth.BluetoothSend;
import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;

import java.io.IOException;

public class OverzichtMeldingenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_meldingen);

    }
}