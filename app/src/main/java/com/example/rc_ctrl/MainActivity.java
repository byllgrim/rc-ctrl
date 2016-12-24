package com.example.rc_ctrl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBluetoothDialog(View view) {
        BluetoothDialogFragment dialog = new BluetoothDialogFragment();
        dialog.show(getSupportFragmentManager(), "BluetoothDialogFragment");
    }
}
