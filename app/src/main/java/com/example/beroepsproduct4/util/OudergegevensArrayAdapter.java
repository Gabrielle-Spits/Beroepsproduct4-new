package com.example.beroepsproduct4.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Zorgcentrum;

import java.util.ArrayList;
import java.util.List;

public class OudergegevensArrayAdapter extends ArrayAdapter<Oudergegevens> {
    private int resourceLayout;
    private Context bContext;

    public OudergegevensArrayAdapter(Context context, int resource, List<Oudergegevens> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.bContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(bContext);
            v = vi.inflate(resourceLayout, null);
        }

        Oudergegevens oudergegevens = getItem(position);
        if (oudergegevens != null) {
            TextView tt1 = v.findViewById(R.id.tvbsn);
            TextView tt2 = v.findViewById(R.id.tvoudernaam);
            TextView tt3 = v.findViewById(R.id.tvafdeling);
            TextView tt4 = v.findViewById(R.id.tvZorgcentrum);
            if (tt1 != null) {
                tt1.setText(oudergegevens.getBsn().toString());
            }

            if (tt2 != null) {
                tt2.setText(oudergegevens.getOudernaam());
            }
            if (tt3 != null) {
                tt3.setText(oudergegevens.getZorgcentrum().getAfdeling());
            }
            if (tt4 != null) {
                tt4.setText(oudergegevens.getZorgcentrum().getZorgcentrum());
            }


        }
            return v;

    }
}
