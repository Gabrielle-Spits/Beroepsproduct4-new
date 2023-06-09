package com.example.beroepsproduct4.schermen.Rollatorgegevens.bluetooth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.beroepsproduct4.Bluetooth.BluetoothSend;
import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Rollatorhoortbij;
import com.example.beroepsproduct4.schermen.Rollatorgegevens.OverzichtMeldingenActivity;

import java.io.IOException;


public class ConnectieLijstFragment extends Fragment {
    private ArrayAdapter<String> pairedDevicesArrayAdapter;
    private final BluetoothSend bluetoothSend = MainActivity.bluetoothSend;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Rollatorhoortbij rollatorhoortbij = getActivity().getIntent().getParcelableExtra("rollatornaam");

        View v =  inflater.inflate(R.layout.fragment_connectie_lijst, container, false);
        ListView list = v.findViewById(R.id.lvConecctieLijst);
        pairedDevicesArrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1);
        bluetoothSend.showPairedDevices(list, pairedDevicesArrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String device = list.getAdapter().getItem(i).toString();
                Log.i("diveice name", device);
                try {
                    bluetoothSend.createConnection(device);
                    try {
                        if(bluetoothSend.isConnected()){
                            String oldrollator = rollatorhoortbij.getrollator();
                            MainActivity.device = device;
                            pairedDevicesArrayAdapter.clear();
                            Toast.makeText(getContext(),"verbinding is gemaakt", Toast.LENGTH_SHORT).show();
                            MainActivity.tread();
                            DataDBHelper dbHelper = new DataDBHelper(getContext());
                            long result = dbHelper.updaterollatornaamhoortbij(oldrollator,device);
                            if(result == 1){
                                Rollatorhoortbij rollatorhoortbij = new Rollatorhoortbij(device);
                                Intent intent = new Intent(getContext(), OverzichtMeldingenActivity.class);
                                intent.putExtra("rollatornaamwwhere",rollatorhoortbij);
                                startActivity(intent);
                            }
                        }
                    } catch (IOException e){
                        e.getMessage();
                    }
                } catch (IOException e) {
                    Log.e("IOExeptoin", e.getMessage());
                }
            }
        });

        Button btnAlverbonden = v.findViewById(R.id.btnAlverbonden);

        btnAlverbonden.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), OverzichtMeldingenActivity.class);
            startActivity(intent);
        });

        return v;
    }
}