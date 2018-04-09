package com.example.sravanreddy.filescanner;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static ArrayList<File> globalFiles;
    Button scanFiles, moreInfo;
    ArrayList<File> files, savedFiles;
    ProgressDialog progressDialog;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    FilescannerAsync filescannerAsync;
    boolean falg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedFiles=new ArrayList<File>();
        globalFiles=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        scanFiles=findViewById(R.id.scan_button);
        moreInfo=findViewById(R.id.getInfo_button);
        scanFiles.setOnClickListener(this);
        moreInfo.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scan_button:
                if(files!=null) files.clear();
                filescannerAsync=new FilescannerAsync();
                filescannerAsync.execute();
                break;
            case R.id.getInfo_button:
                Intent details=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(details);
                break;
        }
    }



    private MyAdapter setAdapter(ArrayList<File> files) {
        myAdapter=new MyAdapter(files,this);
        savedFiles.addAll(files);
        globalFiles.addAll(files);
        return myAdapter;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        files=new ArrayList<>();
//        files.addAll((ArrayList<File>) savedInstanceState.getSerializable("Files"));
//        Log.i("OnRestore Called", "OnRestore called"+files.size());
//
//        myAdapter=new MyAdapter(files, this);
        recyclerView.setAdapter(setAdapter((ArrayList<File>) savedInstanceState.getSerializable("Files")));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Files", savedFiles);
        Log.i("OnRestore Called", "OnRestore Called"+savedFiles.size());
    }

    public class FilescannerAsync extends AsyncTask<Void, Integer, Void>{
        boolean falg;
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();
            recyclerView.setAdapter( setAdapter(files));
        }

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Task is running");
            //progressDialog.setCancelable(false);
            progressDialog.setButton("Stop", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cancelScan();
                }
            });
            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    cancelScan();
                }
            });
            progressDialog.show();
            super.onPreExecute();
            falg=false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            files=scanFiles(Environment.getExternalStorageDirectory());
            return null;
        }
        private ArrayList<File> scanFiles(File root) {
            ArrayList<File> localList=new ArrayList<>();
            File mFile = new File(root.getPath());
            if(falg==false)
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelScan();
    }

    public void cancelScan(){
        filescannerAsync.cancel(true);
        falg=true;
    }
}


