package com.example.sravanreddy.filescanner;

import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sravanreddy on 4/8/18.
 */

public class MyFileScanner extends AsyncTaskLoader<ArrayList<File>> {
   ArrayList<File> files;
   Context context;
    public MyFileScanner(Context context,   ArrayList<File> files) {
        super(context);
        this.files=files;
        this.context=context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public void deliverResult(ArrayList<File> data) {
        super.deliverResult(data);
    }

    @Override
    public ArrayList<File> loadInBackground() {
        files=scanFiles(Environment.getExternalStorageDirectory());
        return files;
    }
    private ArrayList<File> scanFiles(File root) {
        ArrayList<File> localList=new ArrayList<>();
        File mFile = new File(root.getPath());

        for(File singleFile: mFile.listFiles()){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(singleFile.isDirectory()&&!singleFile.isHidden()){
//                localList.addAll(scanFiles(singleFile));
                localList.addAll(scanFiles(singleFile));
            }
            else{
                localList.add(singleFile);
            }
        }
        return localList;
    }
}