package com.example.Radius_t_AndroidApp;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    View checker;

    boolean scanning;
    Handler handler;
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    BluetoothLeScanner bluetoothLeScanner;
    long SCAN_PERIOD;

    LeDeviceListAdapter leDeviceListAdapter;
    RecyclerView recyclerView;

    // Device scan callback.
    private ScanCallback leScanCallback =
            new ScanCallback() {

                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    checker.setVisibility(View.VISIBLE);
                    leDeviceListAdapter.addDevice(result.getDevice());
                    leDeviceListAdapter.notifyDataSetChanged();

                    if (result != null) {

                    }

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        SCAN_PERIOD = 10000;
        ArrayList<BluetoothDevice> thermometerList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvDevs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        leDeviceListAdapter = new LeDeviceListAdapter(MainActivity.this, thermometerList);
        recyclerView.setAdapter(leDeviceListAdapter);
        bluetoothManager = getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

        checker = findViewById(R.id.check);
        checker.setVisibility(View.INVISIBLE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            //checker.setVisibility(View.VISIBLE);
        }



        Button startScan = (Button) findViewById(R.id.scanBtn);
        startScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scanLeDevice();
            }
        });

    }

    public void scanLeDevice() {

        if (!scanning) {

            // Stops scanning after a predefined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    scanning = false;


                    bluetoothLeScanner.stopScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            scanning = true;
            bluetoothLeScanner.startScan(leScanCallback);
        } else {
            scanning = false;
            bluetoothLeScanner.stopScan(leScanCallback);
        }
    }

}

