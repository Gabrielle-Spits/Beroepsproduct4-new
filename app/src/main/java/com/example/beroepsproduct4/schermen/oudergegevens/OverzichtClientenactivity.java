package com.example.beroepsproduct4.schermen.oudergegevens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;

public class OverzichtClientenactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_clientenactivity);

        Intent intent1 = getIntent();
        Zorgcentrum zorgcentrum = intent1.getParcelableExtra("zorgcentrum");
        String afdeling = zorgcentrum.getAfdeling();
        String zorgcenttrum = zorgcentrum.getZorgcentrum();
        Zorgcentrum Zorgcentrum1 = new Zorgcentrum(afdeling,zorgcenttrum);
        Button btnToevoegenclient = findViewById(R.id.btnToevoegenclient);

        btnToevoegenclient.setOnClickListener(v->{
            Intent intent = new Intent(OverzichtClientenactivity.this, OudertoevoegenActivity.class);
            intent.putExtra("zorgcentrum",Zorgcentrum1);
            startActivity(intent);
        });
    }
}