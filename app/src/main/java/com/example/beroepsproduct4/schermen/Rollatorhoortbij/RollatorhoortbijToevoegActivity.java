package com.example.beroepsproduct4.schermen.Rollatorhoortbij;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Rollatorhoortbij;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.oudergegevens.OudertoevoegenActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;

import java.util.ArrayList;

public class RollatorhoortbijToevoegActivity extends AppCompatActivity {
    private final DataDBHelper dbHelper = new DataDBHelper(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollatorhoortbij_toevoeg);

        Intent intent = getIntent();
        Rollatorhoortbij rollator = intent.getParcelableExtra("rollator");
        Button btnRollatorhoortbijToevooegen = findViewById(R.id.btnToevoegRollatorhoortbij);
        EditText edttxtRollator = findViewById(R.id.edttxtRollator);
        Spinner spnnerBsn = findViewById(R.id.spnnrBsn);

        if(rollator != null){
            btnRollatorhoortbijToevooegen.setText("update");
            edttxtRollator.setText(rollator.getrollator());

            btnRollatorhoortbijToevooegen.setOnClickListener(v->{
                String OldStringRollator = rollator.getrollator();
                String Strrollator = edttxtRollator.getText().toString();
                if(Strrollator.isEmpty()){
                    Toast.makeText(this, "Vul alle gegevens in", Toast.LENGTH_SHORT).show();
                }else {
                    Oudergegevens bsnspinner = (Oudergegevens) spnnerBsn.getSelectedItem();
                    String Strbsn = bsnspinner.getBsn();
                    Oudergegevens bsn = new Oudergegevens(Strbsn);
                    Rollatorhoortbij rollatorData = new Rollatorhoortbij(bsn,Strrollator);
                    long result = dbHelper.updateRollatorhoortbij(OldStringRollator,rollatorData);
                    if(result == 1){
                        Intent intent1 = new Intent(RollatorhoortbijToevoegActivity.this, MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(this, "Rollator is succesvol toegevoegd", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(this, "Deze rollator bestaat al", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            ArrayList<Oudergegevens> alouderenbsn = dbHelper.SpinnerOuderenbsn(rollator.getOudergegevens().getBsn());
            ArrayAdapter<Oudergegevens> ouderenbsnArrayAdapter = new ArrayAdapter<>(RollatorhoortbijToevoegActivity.this, android.R.layout.simple_spinner_item, alouderenbsn);
            ouderenbsnArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnnerBsn.setAdapter(ouderenbsnArrayAdapter);
            selectSpinnerValue(spnnerBsn, rollator.getOudergegevens().getBsn());
        } else{
            Intent intent1 = getIntent();
            Oudergegevens oudergegevens = intent1.getParcelableExtra("rollatorToevoegen");

            btnRollatorhoortbijToevooegen.setOnClickListener(v-> {
                String StrRollator = edttxtRollator.getText().toString();
                if(StrRollator.isEmpty()){
                    Toast.makeText(this, "Vul alle gegevens in", Toast.LENGTH_SHORT).show();
                } else {
                    Oudergegevens bsn = (Oudergegevens) spnnerBsn.getSelectedItem();
                    String Strbsn = bsn.getBsn();
                    Oudergegevens bsninvoer = new Oudergegevens(Strbsn);
                    Rollatorhoortbij r = new Rollatorhoortbij(bsninvoer, StrRollator);
                    long result = dbHelper.insertRollatorhoortbij(r);
                    if (result == -1 || result == 0) {

                    } else {
                        Intent intent2 = new Intent(RollatorhoortbijToevoegActivity.this, MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(this, "Rollatorhoort bij is succesvol toegevoegd ", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            ArrayList<Oudergegevens> alouderenbsn = dbHelper.SpinnerOuderenbsn(oudergegevens.getBsn());
            ArrayAdapter<Oudergegevens> ouderenbsnArrayAdapter = new ArrayAdapter<>(RollatorhoortbijToevoegActivity.this, android.R.layout.simple_spinner_item, alouderenbsn);
            ouderenbsnArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnnerBsn.setAdapter(ouderenbsnArrayAdapter);
            selectSpinnerValue(spnnerBsn, oudergegevens.getBsn());

        }



    }

    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }
}