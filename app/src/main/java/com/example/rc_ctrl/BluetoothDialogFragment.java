package com.example.rc_ctrl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by robin on 12/24/16.
 */

public class BluetoothDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) { //TODO refactor
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.dialog_bluetooth, null);
        ListView listView = (ListView) inflatedView.findViewById(R.id.deviceList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        adapter.add("One");
        adapter.add("Two");
        adapter.add("Three");
        adapter.add("Four");
        adapter.add("Five");
        adapter.add("Six");
        //TODO add items in bluetooth worker thread
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click on " + position);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Select device"); //TODO setTitle()?
        builder.setView(inflatedView);
        return builder.create();
    }
}
