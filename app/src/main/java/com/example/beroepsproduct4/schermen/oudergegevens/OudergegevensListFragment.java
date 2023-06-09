package com.example.beroepsproduct4.schermen.oudergegevens;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.schermen.wooncentrum.DetailZorgcentrumActivity;
import com.example.beroepsproduct4.schermen.wooncentrum.ZorgcentrumToevoegenActivity;
import com.example.beroepsproduct4.util.OudergegevensArrayAdapter;
import com.example.beroepsproduct4.util.ZorcentrumArrayAdapter;

import java.util.ArrayList;


public class OudergegevensListFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_oudergegevens_list, container, false);
//      haalt zorgcentrum op zodat je specifiek kan selecteren
        Zorgcentrum Zorgcentrum = getActivity().getIntent().getParcelableExtra("zorgcentrum");

//      Als zorgcentrum leeg is stuur door naar toevoeg pagina
        if(Zorgcentrum == null){
            Intent intent = new Intent(this.getContext(), OudertoevoegenActivity.class);
            startActivity(intent);
        }
//      laat lijst zien met alle ouderen van specifieke afdeling en zorgcentrum
        else {
            String StrAfdeling = Zorgcentrum.getAfdeling();
            String StrZorgcentrum = Zorgcentrum.getZorgcentrum();

            ListView lstvwOudergegevens = v.findViewById(R.id.lvOudergegevens);
            DataDBHelper dbHelper = new DataDBHelper(v.getContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cur_oudergegevens = db.rawQuery("select * from ouderengegevens where afdeling=? and zorgcentrum=?",new String[]{StrAfdeling,StrZorgcentrum});
            ArrayList<Oudergegevens> alOuderengegevens = new ArrayList<>();
//          als de cursor niet leeg is
            if((cur_oudergegevens != null) && (cur_oudergegevens.getCount() > 0)) {
                if (cur_oudergegevens.moveToFirst()) {
                    do {
                        Oudergegevens oudergegevens = new Oudergegevens();
                        oudergegevens.setBsn(cur_oudergegevens.getString(0));
                        oudergegevens.setOudernaam(cur_oudergegevens.getString(1));
                        oudergegevens.setZorgcentrum(new Zorgcentrum(cur_oudergegevens.getString(2), cur_oudergegevens.getString(3)));
                        alOuderengegevens.add(oudergegevens);
                    } while (cur_oudergegevens.moveToNext());
                    db.close();
                }
            }
//          als de cursor leeg is
            else {
                Intent intent = new Intent(this.getContext(), OudertoevoegenActivity.class);
                intent.putExtra("zorgcentrum",Zorgcentrum);
                startActivity(intent);
            }


            ArrayAdapter<Oudergegevens> oudergegevensArrayAdapter = new OudergegevensArrayAdapter(v.getContext(), R.layout.oudergegevens_list_adapter, alOuderengegevens);
            lstvwOudergegevens.setAdapter(oudergegevensArrayAdapter);
//          zet lijst op click listener
            lstvwOudergegevens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Oudergegevens oudergegevens = (Oudergegevens) adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(view.getContext(), DetailOudergegevensActivity.class);
                    intent.putExtra("oudergegevens", oudergegevens);
                    startActivity(intent);
                }
            });
        }
        return v;
    }
}