package com.example.rc_ctrl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by robin on 12/26/2016.
 */

public class DevicesArrayAdapter extends ArrayAdapter {
    public DevicesArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
        //TODO return separators for paired and discovered devices
    }
}
