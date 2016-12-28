package com.example.rc_ctrl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.lang.Runnable;
import java.util.Set;

import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by robin on 12/25/2016.
 */

public class BluetoothWorkerThread implements Runnable, OnItemClickListener {
    private DevicesArrayAdapter listAdapter;
    private BluetoothAdapter btAdapter;

    public BluetoothWorkerThread(DevicesArrayAdapter listAdapter) {
        this.listAdapter = listAdapter;
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void run() {
        listAdapter.add(new SeparatorItem("Paired devices"));
        Set<BluetoothDevice> paired = btAdapter.getBondedDevices();
        for (BluetoothDevice device : paired) {
            listAdapter.add(device);
        }

        listAdapter.add(new SeparatorItem("Other devices"));
        listAdapter.add("One");
        listAdapter.add("Two");
        listAdapter.add("Three");
        listAdapter.add("Four");
        listAdapter.add("Five");
        listAdapter.add("Six");
        listAdapter.add(new ScanButtonItem());
        //TODO actually scan for bluetooth devices
        //TODO add headers in constructor or creation of dialog
        //TODO run this thread constantly. Also check bt state constantly.
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Click on " + position);
        //TODO connect to selected device
    }
}
