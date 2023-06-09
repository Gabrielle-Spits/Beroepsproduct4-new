package com.example.beroepsproduct4.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.model.Rollatorgegevens;
import com.example.beroepsproduct4.model.Zorgcentrum;

import java.util.List;

public class RollatorgegevensArrayAdapter extends ArrayAdapter<Rollatorgegevens> {
    private int resourceLayout;
    private Context bContext;

    public RollatorgegevensArrayAdapter(Context context, int resource, List<Rollatorgegevens> items) {
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

        Rollatorgegevens rollatorgegevens = getItem(position);

        if (rollatorgegevens != null) {
            TextView tt1 = v.findViewById(R.id.tvRollator);
            TextView tt2 = v.findViewById(R.id.tvDatum);

            if (tt1 != null) {
                tt1.setText(rollatorgegevens.getRollator().getrollator());
            } else {
                tt1.setText("hier werkt iets niet");
            }

            if (tt2 != null) {
                tt2.setText(rollatorgegevens.getDatum());
            }
        }
        return v;
    }
}
