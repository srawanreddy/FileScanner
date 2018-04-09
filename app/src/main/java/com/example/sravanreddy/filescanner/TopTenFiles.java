package com.example.sravanreddy.filescanner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class TopTenFiles extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    ArrayList<File> localList;
    List<Map.Entry<String, Long>> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localList=new ArrayList<>();
        localList.addAll(MainActivity.globalFiles);
    }
        public void sort(ArrayList<File> localList){
            HashMap<String, Long> filesList=new HashMap<>();
            Iterator<File> itr=localList.iterator();
            while (itr.hasNext()){
                File singleFile=itr.next();
               filesList.put(singleFile.getName(), (singleFile.length()/1024)) ;
            }
            list= new LinkedList<>(filesList.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
                @Override
                public int compare(Map.Entry<String, Long> stringLongEntry, Map.Entry<String, Long> t1) {
                     return t1.getValue().compareTo(stringLongEntry.getValue());
                }
            });
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.top_ten, null);
        recyclerView=view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        sort(localList);
        setAdapter();
        return view;
    }
    public void setAdapter(){
        TopTenAdapter adapter=new TopTenAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}
