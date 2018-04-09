package com.example.sravanreddy.filescanner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class Frequencies extends android.support.v4.app.Fragment {
    ArrayList<File> localList;
    RecyclerView recyclerView;
    ArrayList<String> keys;
    ArrayList<Integer> values;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localList=new ArrayList<>();
        localList.addAll(MainActivity.globalFiles);
    }

    public void getFrequencies(){
        HashMap<String, Integer> fileEx=new HashMap<>();
        Iterator<File> itr=localList.iterator();

        while(itr.hasNext()){
            File singleFile=itr.next();
            String name=singleFile.getName();
            int index=name.indexOf('.');
            if(index>=0)
            {String extensions=name.substring(index);
            if(fileEx.containsKey(extensions))
            fileEx.put(extensions, fileEx.get(extensions)+1);
            else
            fileEx.put(extensions, 1);}
        }
        keys=new ArrayList<>();
        keys.addAll(fileEx.keySet());
        values=new ArrayList<>();
        Iterator<String> itr2=keys.iterator();
        while(itr2.hasNext()){
            values.add(fileEx.get(itr2.next()));
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frequencies, null);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        getFrequencies();
        FreqListAdapter adapter=new FreqListAdapter(keys, values);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
