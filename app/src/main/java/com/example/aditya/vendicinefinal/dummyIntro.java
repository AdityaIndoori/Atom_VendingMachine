package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.aditya.vendicinefinal.Intro.coins;

public class dummyIntro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_intro);
    }

    public void toSelAct(View view){
        if (coins>10){
            Intent intent=new Intent(this,SelectionUI.class);
            startActivity(intent);
        }
        else{
           // Toast.makeText(this, "Machine cannot provide Change, Its under Maintenance", Toast.LENGTH_SHORT).show();
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","Machine under Servicing");
            startActivity(popIntent);
        }
    }


    public void setupscr(View v){
        Intent intent2=new Intent(this,Intro.class);
        startActivity(intent2);
    }

}
