package com.example.aditya.vendicinefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.example.aditya.vendicinefinal.aInitialScreen.coins;

public class cUserInitialScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_user_initial_screen);
        Log.v("TotalNotes","The Total Number of Notes are: " + aInitialScreen.totalnotes);
    }

    public void toSelAct(View view){
        if (coins>20 || aInitialScreen.totalnotes<450){
            Intent intent=new Intent(this,dAllProducts.class);
            startActivity(intent);
        }
        else{
            Intent popIntent = new Intent(this,jPopupMessage.class);
            popIntent.putExtra("String","Machine under Servicing");
            startActivity(popIntent);
        }
    }


    public void setupscr(View v){
        aInitialScreen.quantflag=false;
        Intent intent2=new Intent(this,aInitialScreen.class);
        intent2.putExtra("Bluetooth","On");
        startActivity(intent2);
    }

}
