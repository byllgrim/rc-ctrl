package com.example.rc_ctrl;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by robin on 12/24/16.
 */

public class BluetoothDialogFragment extends DialogFragment {
    private DevicesArrayAdapter listAdapter;
    private BroadcastReceiver receiver;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) { //TODO refactor
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.dialog_bluetooth, null);
        ListView listView = (ListView) inflatedView.findViewById(R.id.deviceList);

        listAdapter = new DevicesArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, new ArrayList());
        listView.setAdapter(listAdapter);
        populateAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listAdapter.getItem(position);

                if (item == null || !(item instanceof BluetoothDevice))
                    return;

                new Thread(new ConnectionThread((BluetoothDevice) item)).start();
                //TODO connect to selected device
                //TODO move to dedicated class
            }
        });
        setupBroadcastReceiver();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select bluetooth device");
        builder.setView(inflatedView);
        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       getActivity().unregisterReceiver(receiver);
    }

    private void populateAdapter() {
        listAdapter.add(new SeparatorItem("Paired devices"));
        Set<BluetoothDevice> paired = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        for (BluetoothDevice device : paired) {
            listAdapter.add(device);
        }

        listAdapter.add(new SeparatorItem("Other devices"));
        listAdapter.add(new ScanButtonItem());
    }

    private void setupBroadcastReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!BluetoothDevice.ACTION_FOUND.equals(intent.getAction()))
                    return;

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                listAdapter.insert(device, listAdapter.getCount() - 1);
                //TODO check if MAC address already exist
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(receiver, filter);
    }
}
