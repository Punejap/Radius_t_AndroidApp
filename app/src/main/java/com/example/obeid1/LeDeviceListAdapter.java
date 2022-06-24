package com.example.obeid1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.bluetooth.*;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LeDeviceListAdapter extends RecyclerView.Adapter<LeDeviceListAdapter.MyViewHolder> {
    ArrayList<BluetoothDevice> thermometerList = new ArrayList<>();
    Activity mactivity;

    public LeDeviceListAdapter(Activity mactivity) {
        this.mactivity = mactivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;

        public MyViewHolder(final View view){
            super(view);

            TextView tv = view.findViewById(R.id.tv_dev);
        }
    }

    public void addDevice(BluetoothDevice bd){
        thermometerList.add(bd);

    }

    @NonNull
    @Override
    public LeDeviceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View thermLine;

        LayoutInflater inflater = (LayoutInflater) mactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        thermLine = inflater.inflate(R.layout.therms, parent, false);
        TextView tv_id = thermLine.findViewById(R.id.tv_dev);

        tv_id.setText("defaulkt");
        return new MyViewHolder(thermLine);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // String deviceDetails = thermometerList.get(position).getName();
        holder.tv.setText("muffins");

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
