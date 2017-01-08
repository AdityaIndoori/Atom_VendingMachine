package com.example.aditya.vendicinefinal;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class aInitialScreen extends AppCompatActivity {
    String address = null;
    String string = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    public static BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Button coinreset;
    public static boolean quantflag = true;
    public static BluetoothDevice dispositivo;
    public static double transNumb=0;
    public static boolean fileFlag;
    public static int coins=150,totalnotes=0;
    public static int okacetQuant,bisleri,wild, redbull,aloe,pulpy,sanzQuant,wipesQuant,metrogylQuant,eldoperQuant,doloQuant,gelusil,meftalQuant,nutriaQuant,nutribQuant,condomQuant,whisperQuant,waterQuant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_initial_screen);
        myBluetooth=BluetoothAdapter.getDefaultAdapter();

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            string = extras.getString("Bluetooth","Off");
            Log.v("Intent_Intro","The string via intent is: "+string);
            if (string.equals("On")){
                if (myBluetooth.isEnabled()){
                    myBluetooth.disable();
                }
            }
        }

        coinreset=(Button)findViewById(R.id.coinRst);
        coinreset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                coins=150;
                totalnotes=0;
                okacetQuant = 17;
                metrogylQuant = 18;
                eldoperQuant = 17;
                doloQuant = 17;
                gelusil = 17;
                meftalQuant = 18;
                nutriaQuant = 17;//Merry Berry nutria
                nutribQuant = 32;//Choco Delite nutrib
                sanzQuant = 14;
                condomQuant = 13;
                //clr72=14;
                whisperQuant = 11;//5+6
                wipesQuant = 12;
                bisleri = 10;
                wild = 5;
                redbull = 5;
                aloe = 5;
                pulpy = 5;
                if (myBluetooth.isEnabled()){
                    myBluetooth.disable();
                }
                Toast.makeText(aInitialScreen.this, "All the items are default values and Coins have been RESET to: "+coins, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        if (quantflag==true) {
            okacetQuant = 17;
            metrogylQuant = 18;
            eldoperQuant = 17;
            doloQuant = 17;
            gelusil = 17;
            meftalQuant = 18;
            nutriaQuant = 17;//Merry Berry nutria
            nutribQuant = 32;//Choco Delite nutrib
            sanzQuant = 14;
            condomQuant = 13;
            //clr72=14;
            whisperQuant = 11;//5+6
            wipesQuant = 12;
            bisleri = 10;
            wild = 5;
            redbull = 5;
            aloe = 5;
            pulpy = 5;
        }
        fileFlag=true;
        Intent newint = getIntent();
        address = newint.getStringExtra(bBluetoothPairedDeviceList.EXTRA_ADDRESS);
        boolean flag = newint.getBooleanExtra(bBluetoothPairedDeviceList.START_FLAG,false);

        if (flag)//If this is true it means the user has selected the bt device he wants to connect to
            new ConnectBT().execute(); //Call the class to connect
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(aInitialScreen.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)//if connection is unsuccessful
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
    }

    public void blueMenu(View view){
        Intent intent=new Intent(this,bBluetoothPairedDeviceList.class);
        startActivity(intent);
    }

    public void toMainAct(View view){
        if (isBtConnected)
        {
            Intent intent=new Intent(this,cUserInitialScreen.class);
            startActivity(intent);
        }
        else
            msg("Bluetooth is not connected");
    }

    public void coinReset(View v){
        Toast.makeText(this, "The number of coins is: "+coins, Toast.LENGTH_SHORT).show();
    }
}
