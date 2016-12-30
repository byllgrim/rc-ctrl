package com.example.rc_ctrl;

import android.bluetooth.BluetoothDevice;

/**
 * Created by robin on 12/30/2016.
 */

public class ConnectionThread implements Runnable {
    private BluetoothDevice device;

    public ConnectionThread(BluetoothDevice device) {
        this.device = device;
    }

    @Override
    public void run() {
        System.out.println("Click on device \"" + device.getName() + "\"");
    }
}
