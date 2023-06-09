package com.example.beroepsproduct4.schermen.Rollatorgegevens;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.beroepsproduct4.Bluetooth.BluetoothSend;
import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Rollatorgegevens;
import com.example.beroepsproduct4.model.Rollatorhoortbij;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.oudergegevens.DetailOudergegevensActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.DetailZorgcentrumActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;
import com.example.beroepsproduct4.util.OudergegevensArrayAdapter;
import com.example.beroepsproduct4.util.RollatorgegevensArrayAdapter;
import com.example.beroepsproduct4.util.ZorcentrumArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class RollatorgegevensListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_rollatorgegevens_list, container, false);
        ListView lvRollatorgegevens = v.findViewById(R.id.lvRolattorgegevens);
        DataDBHelper dbHelper = new DataDBHelper(v.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select rollator, datum from rollatorgegevens";
        Cursor cur_rollatorgegevens = db.rawQuery(sql, null);
        ArrayList<Rollatorgegevens> alRollatorgegevens = new ArrayList<>();
        if (cur_rollatorgegevens.moveToFirst()) {
            do {
                Rollatorgegevens rollatorgegevens = new Rollatorgegevens();
                rollatorgegevens.setRollator(new Rollatorhoortbij(cur_rollatorgegevens.getString(0)));
                rollatorgegevens.setDatum(cur_rollatorgegevens.getString(1));
                alRollatorgegevens.add(rollatorgegevens);
            } while (cur_rollatorgegevens.moveToNext());
        }
        ArrayAdapter<Rollatorgegevens> rollatorgegevensArrayAdapter = new RollatorgegevensArrayAdapter(v.getContext(), R.layout.rollatorgegevens_list_adapter, alRollatorgegevens);
        lvRollatorgegevens.setAdapter(rollatorgegevensArrayAdapter);

        Button refreshlijst = v.findViewById(R.id.refreshlijst);
        refreshlijst.setOnClickListener(view-> {
            Intent intent = new Intent(view.getContext(), OverzichtMeldingenActivity.class);
            startActivity(intent);

        });
        Button verbreekConnectie = v.findViewById(R.id.btnVerbreekconnectie);
        verbreekConnectie.setOnClickListener(view -> {
            BluetoothSend bluetoothSend = MainActivity.bluetoothSend;
            bluetoothSend.closeConnection();
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);

        });
        return v;
        }
    }
