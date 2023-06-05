package com.example.beroepsproduct4.schermen.wooncentrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.oudergegevens.OverzichtClientenactivity;

import java.security.AccessController;

public class DetailZorgcentrumActivity extends AppCompatActivity {
    private final DataDBHelper dbHelper = new DataDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_zorgcentrum);

//        haal velden op
        TextView txtdetailAfdelingGevuld = findViewById(R.id.txtdetailAfdelingGevuld);
        TextView txtDetailZorgcentrumGevuld = findViewById(R.id.txtDetailZorgcentrumGevuld);

//        haal buttons op
        Button btnVerwijderZorgcentrum = findViewById(R.id.btnVerwijderZorgcentrum);
        Button btnBewerkZorgcentrum = findViewById(R.id.btnBewerkZorgcentrum);
        Button btnBekijkClienten = findViewById(R.id.btnBekijkClienten);

//        haal ingevulde gegevens op
        Intent intent1 = getIntent();
        Zorgcentrum zorgcentrum = intent1.getParcelableExtra("zorgcentrum");

//        zet ingevulde gegvens in de tekstvakken
        txtdetailAfdelingGevuld.setText(zorgcentrum.getAfdeling());
        txtDetailZorgcentrumGevuld.setText(zorgcentrum.getZorgcentrum());

        btnBewerkZorgcentrum.setOnClickListener(v->{
            Intent intent = new Intent(DetailZorgcentrumActivity.this, ZorgcentrumToevoegenActivity.class);
            intent.putExtra("zorgcentrum", zorgcentrum);
            Log.e("",zorgcentrum.getZorgcentrum());
            startActivity(intent);
        });

        btnVerwijderZorgcentrum.setOnClickListener(v->{
            String StrAfdeling = txtdetailAfdelingGevuld.getText().toString();
            String StrZorgcentrum = txtDetailZorgcentrumGevuld.getText().toString();
            Zorgcentrum zorgcentrum1 = new Zorgcentrum(StrAfdeling,StrZorgcentrum);
//            maakt dbhelper aan
            long result = dbHelper.deleteZorgcentrum(zorgcentrum1);

            if(result == 1){
                Intent intent = new Intent(DetailZorgcentrumActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnBekijkClienten.setOnClickListener(v->{
            Intent intent = new Intent(DetailZorgcentrumActivity.this, OverzichtClientenactivity.class);
            intent.putExtra("zorgcentrum", zorgcentrum);
            startActivity(intent);
        });
    }
}