package com.example.beroepsproduct4.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beroepsproduct4.R;
import com.example.beroepsproduct4.model.Zorgcentrum;

import java.util.List;

public class ZorcentrumArrayAdapter extends ArrayAdapter<Zorgcentrum> {
    private int resourceLayout;
    private Context bContext;

    public ZorcentrumArrayAdapter(Context context, int resource, List<Zorgcentrum> items) {
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

        Zorgcentrum zorgcentrum = getItem(position);

        if (zorgcentrum != null) {
            TextView tt1 = v.findViewById(R.id.tvAfdeling);
            TextView tt2 = v.findViewById(R.id.tvZorgcentrum);

            if (tt1 != null) {
                tt1.setText(zorgcentrum.getAfdeling());
            }

            if (tt2 != null) {
                tt2.setText(zorgcentrum.getZorgcentrum());
            }
        }
        return v;
    }
}
