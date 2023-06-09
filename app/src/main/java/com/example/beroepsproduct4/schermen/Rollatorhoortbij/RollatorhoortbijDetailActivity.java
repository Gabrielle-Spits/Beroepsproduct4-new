package com.example.beroepsproduct4.schermen.Rollatorhoortbij;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Rollatorhoortbij;
import com.example.beroepsproduct4.schermen.Rollatorgegevens.bluetooth.ConnectiemakenBluetoothActivity;
import com.example.beroepsproduct4.schermen.oudergegevens.DetailOudergegevensActivity;
import com.example.beroepsproduct4.schermen.oudergegevens.OudertoevoegenActivity;
import com.example.beroepsproduct4.schermen.oudergegevens.OverzichtClientenactivity;

public class RollatorhoortbijDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollatorhoortbij_detail);
        Intent intent = getIntent();
        Oudergegevens oudergegevens = intent.getParcelableExtra("oudergegevensupdate");

//      als bsn niet leeg is
        if(oudergegevens != null){
            DataDBHelper dbHelper = new DataDBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cur_rollatorhoorbij = db.rawQuery("select * from rollatorhorenbij where bsn=?",new String[]{oudergegevens.getBsn()});
            if((cur_rollatorhoorbij != null) && (cur_rollatorhoorbij.getCount() > 0)){
                while (cur_rollatorhoorbij.moveToNext()) {
                        @SuppressLint("Range") String strBsn = cur_rollatorhoorbij.getString(cur_rollatorhoorbij.getColumnIndex("bsn"));
                        @SuppressLint("Range") String strRollator = cur_rollatorhoorbij.getString(cur_rollatorhoorbij.getColumnIndex("rollator"));

    //                  haal velden op van xml bestand
                        TextView  txtvwbsngevuld = findViewById(R.id.txtDetailBsnGevuld);
                        TextView txtRollatorgevuld = findViewById(R.id.txtDetailRollatorGevuld);
                        Button btnBwerkRollatorHoortbij = findViewById(R.id.btnBwerkRollatorHoortbij);
                        Button btnVerwijderRollatorHoortbij = findViewById(R.id.btnVerwijderRollatorhoortbij);
                        Button btnMaakconnectie = findViewById(R.id.btnMaakconnectie);

                        txtvwbsngevuld.setText(strBsn);
                        txtRollatorgevuld.setText(strRollator);

                        btnBwerkRollatorHoortbij.setOnClickListener(v->{
                            String Strbsn = txtvwbsngevuld.getText().toString();
                            String StrRollator = txtRollatorgevuld.getText().toString();
                            Oudergegevens ouderengegevens = new Oudergegevens(Strbsn);
                            Rollatorhoortbij rollatorhoortbij = new Rollatorhoortbij(ouderengegevens, StrRollator);
                            Intent intent1 = new Intent(RollatorhoortbijDetailActivity.this, RollatorhoortbijToevoegActivity.class);
                            intent1.putExtra("rollator", rollatorhoortbij);
                            startActivity(intent1);
                        });
                        btnVerwijderRollatorHoortbij.setOnClickListener(v->{
                            String Strbsn = txtvwbsngevuld.getText().toString();
                            String StrRollator = txtRollatorgevuld.getText().toString();
                            Oudergegevens ouderengegevens = new Oudergegevens(Strbsn);
                            Rollatorhoortbij rollatorhoortbij = new Rollatorhoortbij(ouderengegevens, StrRollator);
                            long result = dbHelper.deleteRollatorhoortbij(rollatorhoortbij);

                            if(result == 1){
                                Intent intent2 = new Intent(RollatorhoortbijDetailActivity.this, MainActivity.class);
                                startActivity(intent2);
                                Toast.makeText(this, "Rollatorhoortbij is succesvol verwijderd", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btnMaakconnectie.setOnClickListener(v->{
                            Intent intent3 = new Intent(RollatorhoortbijDetailActivity.this, ConnectiemakenBluetoothActivity.class);
                            String StrRollator = txtRollatorgevuld.getText().toString();
                            Rollatorhoortbij rollatorhoortbij = new Rollatorhoortbij(StrRollator);
                            intent3.putExtra("rollatornaam",rollatorhoortbij);
                            startActivity(intent3);
                        });
                }
            } else {
                Intent intent1 = new Intent(RollatorhoortbijDetailActivity.this, RollatorhoortbijToevoegActivity.class);
                Oudergegevens ouderrollatorToevoegen = new Oudergegevens(oudergegevens.getBsn());
                intent1.putExtra("rollatorToevoegen", ouderrollatorToevoegen);
                startActivity(intent1);
            }
        }
    }
}