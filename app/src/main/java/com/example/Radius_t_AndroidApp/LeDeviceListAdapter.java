package com.example.Radius_t_AndroidApp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.bluetooth.*;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeDeviceListAdapter extends RecyclerView.Adapter<LeDeviceListAdapter.MyViewHolder> {

    private List<BluetoothDevice> mData;
    private LayoutInflater mInflater;
    private AdapterView.OnItemClickListener mClickListener;

    public LeDeviceListAdapter(Context context, List<BluetoothDevice> devices) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = devices;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.thermometer_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       String deviceName = mData.get(position).getName();
        holder.tv.setText(deviceName);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View itemView){
            super(itemView);
            tv = itemView.findViewById(R.id.tvDevs);
            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view){
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }

    BluetoothDevice getDevice(int id){
        return mData.get(id);
    }

//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }


    public void addDevice(BluetoothDevice bd){
        mData.add(bd);

    }


}
