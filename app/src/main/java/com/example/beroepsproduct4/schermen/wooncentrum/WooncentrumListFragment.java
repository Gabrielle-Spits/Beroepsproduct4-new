package com.example.beroepsproduct4.schermen.wooncentrum;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.database.DataDBHelper;
import com.example.beroepsproduct4.model.Zorgcentrum;
import com.example.beroepsproduct4.util.ZorcentrumArrayAdapter;

import java.util.ArrayList;


public class WooncentrumListFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wooncentrum_list, container, false);
        ListView lvZorgcentrum = v.findViewById(R.id.lvZorgcentrum);
        DataDBHelper dbHelper = new DataDBHelper(v.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select afdeling,zorgcentrum from zorgcentrums";
        Cursor cur_beheerder = db.rawQuery(sql, null);
        ArrayList<Zorgcentrum> alZorgcentrums = new ArrayList<>();
        if (cur_beheerder.moveToFirst()) {
            do {
                Zorgcentrum zorgcentrum = new Zorgcentrum();
                zorgcentrum.setAfdeling(cur_beheerder.getString(0));
                zorgcentrum.setZorgcentrum(cur_beheerder.getString(1));
                alZorgcentrums.add(zorgcentrum);
            } while (cur_beheerder.moveToNext());
            db.close();
        }
        if(alZorgcentrums.isEmpty()){
            Intent intent = new Intent(this.getContext(), ZorgcentrumToevoegenActivity.class);
            startActivity(intent);
        }
        ArrayAdapter<Zorgcentrum> zorgcentrumArrayAdapter = new ZorcentrumArrayAdapter(v.getContext(), R.layout.zorgcentrum_list_adapter, alZorgcentrums);
        lvZorgcentrum.setAdapter(zorgcentrumArrayAdapter);
        lvZorgcentrum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Zorgcentrum zorgcentrum = (Zorgcentrum) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), DetailZorgcentrumActivity.class);
                intent.putExtra("zorgcentrum", zorgcentrum);
                startActivity(intent);
            }
        });
        return v;





    }
}