package com.example.rc_ctrl;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.lang.Runnable;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by robin on 12/25/2016.
 */

public class BluetoothWorkerThread implements Runnable, OnItemClickListener {
    private DevicesArrayAdapter adapter;

    public BluetoothWorkerThread(DevicesArrayAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        adapter.add("One");
        adapter.add("Two");
        adapter.add("Three");
        adapter.add("Four");
        adapter.add("Five");
        adapter.add("Six");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Click on " + position);
    }
}
