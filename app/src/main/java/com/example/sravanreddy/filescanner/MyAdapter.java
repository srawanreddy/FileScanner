package com.example.sravanreddy.filescanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<File> filesList;
    Context context;

    public MyAdapter(ArrayList<File> filesList, Context context) {
        this.filesList = filesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listLayout= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, null);
        return new MyViewHolder(listLayout);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    File singleFIle=filesList.get(position);
    holder.nameTv.setText(singleFIle.getName());
    holder.sizeTv.setText((singleFIle.length()/1024)+" kbs");
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public void setMyAdapter(    ArrayList<File> filesList){
        this.filesList=filesList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv, sizeTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.fileName);
            sizeTv=itemView.findViewById(R.id.fileSize);
        }
    }
}
