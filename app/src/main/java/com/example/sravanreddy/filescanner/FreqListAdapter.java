package com.example.sravanreddy.filescanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class FreqListAdapter extends RecyclerView.Adapter<FreqListAdapter.MyViewHolder> {
    ArrayList<String> keys;
    ArrayList<Integer> values;
    FreqListAdapter( ArrayList<String> keys, ArrayList<Integer> values){
        this.keys=keys;
        this.values=values;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.freq_list_layout, null);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.extension.setText(keys.get(position));
        holder.freq.setText(values.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView extension, freq;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            extension=itemView.findViewById(R.id.file_ex);
            freq=itemView.findViewById(R.id.frequency);
        }
    }
}
