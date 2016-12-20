package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionUI extends AppCompatActivity {

    BluetoothSocket btSocket = null;
    byte[] readBuffer;
    int readBufferPosition;String Mdate,Mmonth,Myear,Edate,Emonth,Eyear;
    public InputStream mmInputStream;
    public static inputReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_ui);
        btSocket=Intro.btSocket;
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    try {
                        btSocket.getOutputStream().write("0".getBytes());
                        receiverInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (i==1){
                    receiver=new inputReceiver();
                    try {
                        btSocket.getOutputStream().write("1".getBytes());
                        receiverInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });*/
    }

    public void receiverInput(){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {receiver=new inputReceiver();
                try {
                    mmInputStream=btSocket.getInputStream();
                    receiver.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 500);
    }

    public void toaster(String  i){
        Toast.makeText(this,"" + i, Toast.LENGTH_SHORT).show();
    }

    public class inputReceiver extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String str = inputReception();
            while (str==null){
                str=inputReception();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String str1) {
            super.onPostExecute(str1);
            toaster(str1);
        }
    }

    public String inputReception(){
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        String inputData = null;
        Log.v("Async","The method has started");
        try {
            Log.v("Async","Inside Try");
            int bytesAvailable = mmInputStream.available();
            Log.v("Async","The number of bytes received are: " + bytesAvailable);
            if (bytesAvailable>0){
                int mBytes;
                byte[] packetBytes = null;
                packetBytes=new byte[bytesAvailable];
                mBytes=mmInputStream.read(packetBytes);
                Log.v("Async","Inside the if Condition");
                inputData=new String(packetBytes,0,mBytes);
            }
            return inputData;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void tabletClick(View view){
        Log.v("Click","Tablet");
        Intent intent=new Intent(this,tabletSelection.class);
        startActivity(intent);
    }

    public void barClick(View view){
        Intent intent=new Intent(this,nutriSel.class);
        startActivity(intent);
    }

    public void sanzClick(View view){
        if (Intro.sanzQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="01";
            Myear="16";
            Edate="02";Emonth="02";Eyear="19";
            Intent intent=new Intent(this,otherSelection.class);
            intent.putExtra("ItemName","Hand Sanitizer");
            intent.putExtra("ItemPrice","80");
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void condClick(View view){
        if (Intro.condomQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="07";
            Myear="16";
            Edate="01";Emonth="10";Eyear="18";
            Intent intent=new Intent(this,otherSelection.class);
            intent.putExtra("ItemName","Condoms Packets");
            intent.putExtra("ItemPrice","40");
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void sanClick(View view){

        if (Intro.whisperQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="08";
            Myear="16";
            Edate="01";Emonth="01";Eyear="17";
            Intent intent=new Intent(this,otherSelection.class);
            intent.putExtra("ItemName","Sanitary Napkins");
            intent.putExtra("ItemPrice","100");
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void wipesClick(View view){
        if (Intro.condomQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="06";
            Myear="16";
            Edate="01";Emonth="06";Eyear="18";
            Intent intent=new Intent(this,otherSelection.class);
            intent.putExtra("ItemName","Wet Wipes");
            intent.putExtra("ItemPrice","80");
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void drinkClick(View view){
        Intent intent=new Intent(this,wipesSel.class);
        startActivity(intent);
    }

    public void backSelClick(View view){
        Intent intent= new Intent(this,dummyIntro.class);
        startActivity(intent);
    }

}
