package com.example.beroepsproduct4.schermen.wooncentrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Zorgcentrum;

public class ZorgcentrumToevoegenActivity extends AppCompatActivity {
    private final DataDBHelper dbHelper = new DataDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorgcentrum_toevoegen);

        Intent intent1 = getIntent();
        Zorgcentrum zorgcentrum = intent1.getParcelableExtra("zorgcentrum");
        EditText edttxtAfdeling = findViewById(R.id.edttxtAfdeling);
        EditText edttxtZorgcenturm = findViewById(R.id.edttxtZorgcenturm);
        Button btnZorgcentrumToevoegen = findViewById(R.id.btnZorgcentrumToevoegen);

        if (zorgcentrum != null){
            btnZorgcentrumToevoegen.setText("Bewerken");
            edttxtAfdeling.setText(zorgcentrum.getAfdeling());
            edttxtZorgcenturm.setText(zorgcentrum.getZorgcentrum());

            btnZorgcentrumToevoegen.setOnClickListener(v->{
                String oldAfdeling = zorgcentrum.getAfdeling();
                String oldZorgcentrum= zorgcentrum.getZorgcentrum();
                Zorgcentrum oldzorgencentrum = new Zorgcentrum(oldAfdeling,oldZorgcentrum);
                String afdeling = edttxtAfdeling.getText().toString();
                String zorcentrum = edttxtZorgcenturm.getText().toString();

                if(afdeling.isEmpty() || zorcentrum.isEmpty()){
                    Toast.makeText(this, "Vul alle velden", Toast.LENGTH_SHORT).show();
                } else{
                    Zorgcentrum zorgcentrum1 = new Zorgcentrum(afdeling,zorcentrum);
                    long result = dbHelper.updatezorgcentrum(zorgcentrum1,oldzorgencentrum);
                    if(result == 1){
                        Intent intent = new Intent(ZorgcentrumToevoegenActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(this, "Zorgcentrum is succesvol bewerkt", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            btnZorgcentrumToevoegen.setOnClickListener(v->{
                String afdeling = edttxtAfdeling.getText().toString();
                String zorcentrum = edttxtZorgcenturm.getText().toString();

                if(afdeling.isEmpty() || zorcentrum.isEmpty()){

                } else {
                    Zorgcentrum zorgcentrum1 = new Zorgcentrum(afdeling,zorcentrum);
                    long result = dbHelper.insertZorcentrum(zorgcentrum1);
                    if (result == -1 || result == 0){

                    }else {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }



    }

}