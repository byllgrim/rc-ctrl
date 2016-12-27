package com.example.rc_ctrl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robin on 12/26/2016.
 */

public class DevicesArrayAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList items;

    public DevicesArrayAdapter(Context context, int resource, ArrayList items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object item = items.get(position);
        if (item instanceof SeparatorItem) {
            View view = LayoutInflater.from(context).inflate(android.R.layout.preference_category, null);
            TextView text = (TextView) view.findViewById(android.R.id.title);
            text.setText(item.toString());
            return view;
        } else if(item instanceof ScanButtonItem) {
            Button button = new Button(context);
            button.setText(item.toString());
            return button;
        } else {
            View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            text.setText(item.toString());
            return view;
        }
    }
}
