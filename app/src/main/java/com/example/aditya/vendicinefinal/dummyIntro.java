package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.aditya.vendicinefinal.Intro.coins;

public class dummyIntro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_intro);
        Log.v("TotalNotes","The Total Number of Notes are: " + Intro.totalnotes);
    }

    public void toSelAct(View view){
        if (coins>20 || Intro.totalnotes<450){
            Intent intent=new Intent(this,SelectionUI.class);
            startActivity(intent);
        }
        else{
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","Machine under Servicing");
            startActivity(popIntent);
        }
    }


    public void setupscr(View v){
        Intro.quantflag=false;
        Intent intent2=new Intent(this,Intro.class);
        intent2.putExtra("Bluetooth","On");
        startActivity(intent2);
    }

}
