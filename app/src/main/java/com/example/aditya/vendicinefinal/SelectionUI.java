package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionUI extends AppCompatActivity {

    BluetoothSocket btSocket = null;
    ListView listView;
    ArrayAdapter<String> stringArrayAdapter;
    byte[] readBuffer;
    int readBufferPosition;
    public InputStream mmInputStream;
    inputReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_ui);

        listView=(ListView)findViewById(R.id.listView2);
        btSocket=Intro.btSocket;
        int numberOfItems = 10;
//ListView Part Starts
        String[] productList = new String[numberOfItems];
        for (int j=0;j<numberOfItems;j++){
            productList[j]="Product "+j +"\nPrice: " + "$50";
        }
        List<String> listArray = new ArrayList<>(Arrays.asList(productList));
        stringArrayAdapter= new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView5,listArray);
        listView.setAdapter(stringArrayAdapter);
        //ListView Part ends
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
        });
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
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            toaster(aVoid);

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
}
