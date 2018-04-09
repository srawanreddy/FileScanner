package com.example.sravanreddy.filescanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class TopTenAdapter extends RecyclerView.Adapter<TopTenAdapter.MyViewHolder>{
    List<Map.Entry<String, Long>> list;

    public TopTenAdapter(List<Map.Entry<String, Long>> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.freq_list_layout, null);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getKey());
        holder.size.setText(list.get(position).getValue()+" Kbs");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, size;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.file_ex);
            size=itemView.findViewById(R.id.frequency);
        }
    }
}
