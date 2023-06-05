package com.example.beroepsproduct4.schermen.oudergegevens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.Rollatorhoortbij.RollatorhoortbijDetailActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.DetailZorgcentrumActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;

public class DetailOudergegevensActivity extends AppCompatActivity {
    private final DataDBHelper dbHelper = new DataDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_oudergegevens);
        //        haal ingevulde gegevens op
        Intent intent = getIntent();
        Oudergegevens oudergegevens = intent.getParcelableExtra("oudergegevens");

//      haal tekstvelden op van xml bestand
        TextView txtvwBsnGevuld = findViewById(R.id.txtDetailBsnGevuld);
        TextView txtvwOudernaamGevuld = findViewById(R.id.txtDetailOudernaamGevuld);
        TextView txtvwAfdelingGevuld = findViewById(R.id.txtdetailAfdelingGevuld);
        TextView txtvwZorgcentrumGevuld = findViewById(R.id.txtDetailZorgcentrumGevuld);

//       buttons xml bestand ophalen
        Button btnBewerkOudergegevens = findViewById(R.id.btnBewerkOudergegevens);
        Button btnVerwijderOudergegevens = findViewById(R.id.btnVerwijderOudergegevens);
        Button btnBekijkRollator = findViewById(R.id.btnBekijkrollator);
        txtvwBsnGevuld.setText(oudergegevens.getBsn());
        txtvwOudernaamGevuld.setText(oudergegevens.getOudernaam());
        txtvwAfdelingGevuld.setText(oudergegevens.getZorgcentrum().getAfdeling());
        txtvwZorgcentrumGevuld.setText(oudergegevens.getZorgcentrum().getZorgcentrum());


        btnBewerkOudergegevens.setOnClickListener(V->{
            String Bsn = txtvwBsnGevuld.getText().toString();
            String Oudernaam = txtvwOudernaamGevuld.getText().toString();
            String Afdeling = txtvwAfdelingGevuld.getText().toString();
            String Zprgcentrum = txtvwZorgcentrumGevuld.getText().toString();

            Zorgcentrum detailzorgcentrum = new Zorgcentrum(Afdeling,Zprgcentrum);
            Oudergegevens oudergegevensupdate = new Oudergegevens(Bsn,Oudernaam,detailzorgcentrum);

            Intent intent1 = new Intent(DetailOudergegevensActivity.this, OudertoevoegenActivity.class);
            intent1.putExtra("oudergegevensupdate", oudergegevensupdate);
            startActivity(intent1);
        });

        btnVerwijderOudergegevens.setOnClickListener(v->{
            String StrBSN = txtvwBsnGevuld.getText().toString();
            Oudergegevens oudergegevensdelete = new Oudergegevens(StrBSN);
//            maakt dbhelper aan
            long result = dbHelper.deleteOudergegevens(oudergegevensdelete);

            if(result == 1){
                Intent intent2 = new Intent(DetailOudergegevensActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        btnBekijkRollator.setOnClickListener(v->{
            String StrBSN = txtvwBsnGevuld.getText().toString();
            Oudergegevens ouderengegevensrollator = new Oudergegevens(StrBSN);
            Intent intent1 = new Intent(DetailOudergegevensActivity.this, RollatorhoortbijDetailActivity.class);
            intent1.putExtra("oudergegevensupdate", ouderengegevensrollator);
            startActivity(intent1);
        });

    }
}