package com.example.sravanreddy.filescanner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class AverageFIleSize extends android.support.v4.app.Fragment {
    ArrayList<File> localList;
    TextView average;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localList=new ArrayList<>();
        localList.addAll(MainActivity.globalFiles);
    }

    public int getAverage(){
        Iterator itr=localList.iterator();
        int size=0;
        while(itr.hasNext()){
            File singleFile= (File) itr.next();
            size+=(singleFile.length()/1024);
        }
        return (size/localList.size());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.average_size, null);
        average=view.findViewById(R.id.average_tv);
        average.setText(getAverage()+" kbs");
        return view;
    }
}
