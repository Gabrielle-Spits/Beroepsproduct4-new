package com.example.beroepsproduct4.schermen.oudergegevens;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;


import java.util.ArrayList;

public class OudertoevoegenActivity extends AppCompatActivity {
    private final DataDBHelper dbHelper = new DataDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oudertoevoegen);
        Intent intent = getIntent();
        Oudergegevens ouderengegevens = intent.getParcelableExtra("oudergegevensupdate");
        Spinner spinner = findViewById(R.id.spnnrZorgcentrum);
        EditText edtbsn = findViewById(R.id.edttxtBsn);
        EditText edttxtOudernaam = findViewById(R.id.edttxtOudernaam);
        Button btnOudernaamtoevoegen = findViewById(R.id.btnOudernaamtoevoegen);

//      update ouderengegevens
        if(ouderengegevens != null){
            btnOudernaamtoevoegen.setText("update");
            edtbsn.setText(ouderengegevens.getBsn());
            edtbsn.setEnabled(false);
            edttxtOudernaam.setText(ouderengegevens.getOudernaam());

            btnOudernaamtoevoegen.setOnClickListener(v->{
                String Strbsn = edtbsn.getText().toString();
                String StrOudernaam = edttxtOudernaam.getText().toString();
                if(Strbsn.isEmpty() || StrOudernaam.isEmpty()){
                    Toast.makeText(this, "vul alle gegevens in", Toast.LENGTH_SHORT).show();
                } else{
                    Zorgcentrum z = (Zorgcentrum) spinner.getSelectedItem();
                    String Strafdeling = z.getAfdeling();
                    String Strzorgcentrum = z.getZorgcentrum();
                    Zorgcentrum zorgcentrum = new Zorgcentrum(Strafdeling,Strzorgcentrum);
                    Oudergegevens oudergegevens = new Oudergegevens(Strbsn,StrOudernaam,zorgcentrum);
                    long result = dbHelper.updateOudergegevens(oudergegevens);
                    if(result == 1){
                        Intent intent2 = new Intent(OudertoevoegenActivity.this, MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(this, "De oudergegevens zijn succesvol bewerkt", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(this, "Zorgcentrum bestaat al", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ArrayList<Zorgcentrum> alZorgcentrums = dbHelper.SpinnerZorgcentrum();
            ArrayAdapter<Zorgcentrum> zorgcentrumArrayAdapter = new ArrayAdapter<>(OudertoevoegenActivity.this, android.R.layout.simple_spinner_item, alZorgcentrums);
            zorgcentrumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(zorgcentrumArrayAdapter);
            selectSpinnerValue(spinner, ouderengegevens.getZorgcentrum().toString());
        }
        else {
//        haal zorgcentrum op
            Intent intent1 = getIntent();
            Zorgcentrum zorgcentrum = intent1.getParcelableExtra("zorgcentrum");

            btnOudernaamtoevoegen.setOnClickListener(v->{
                String strbsn = edtbsn.getText().toString();
                String strOudernaam = edttxtOudernaam.getText().toString();

                if(strbsn.isEmpty() || strOudernaam.isEmpty()){
                    Toast.makeText(this, "Vul alle velden in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Zorgcentrum z = (Zorgcentrum) spinner.getSelectedItem();
                    String Strafdeling = z.getAfdeling();
                    String Strzorgcentrum = z.getZorgcentrum();
                    Zorgcentrum objZorgcentrum = new Zorgcentrum(Strafdeling,Strzorgcentrum);
                    Oudergegevens oudergegevens = new Oudergegevens(strbsn,strOudernaam,objZorgcentrum);
                    long result = dbHelper.insertOudergegevens(oudergegevens);
                    if (result == -1 || result == 0){
                        Toast.makeText(this, "Er is iets fout gegaan probeer opnieuw", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(this, "oudergegevens is succesvol toegevoegd", Toast.LENGTH_SHORT).show();
                    }
                }
            });

//        spinner content
            ArrayList<Zorgcentrum> alZorgcentrums = dbHelper.SpinnerZorgcentrum();
            ArrayAdapter<Zorgcentrum> zorgcentrumArrayAdapter = new ArrayAdapter<>(OudertoevoegenActivity.this, android.R.layout.simple_spinner_item, alZorgcentrums);
            zorgcentrumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(zorgcentrumArrayAdapter);
            if(zorgcentrum!= null){
                selectSpinnerValue(spinner, zorgcentrum.toString());
            }
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
