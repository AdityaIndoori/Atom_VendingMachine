package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class iPopupOtherProducts extends AppCompatActivity {
    TextView otherName;
    TextView otherPrice;
    TextView otherQuantity,otherMfg,otherExp;
    Button otherConfirm;
    ImageView otherImage;
    int quant;
    int numberOfItems;
    String itemName;
    String itemPrice;
    int intItemPrice;
    BluetoothSocket btSocket = null;
    public InputStream mmInputStream;
    public int flag=0;
    String output,Mfg,Exp;
    byte[] readBuffer;
    int readBufferPosition;
    public inputReceiver receiver;
    int selecteditmQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_activity_popup_other_products);
        otherName=(TextView)findViewById(R.id.otherItemName);
        otherPrice=(TextView)findViewById(R.id.otherItemPrice);
        otherQuantity=(TextView)findViewById(R.id.otherItemQuant);
        otherConfirm=(Button)findViewById(R.id.otherItemConfirm);
        otherImage=(ImageView)findViewById(R.id.otherItemImage);
        otherMfg=(TextView) findViewById(R.id.MfgDate);
        otherExp=(TextView)findViewById(R.id.ExpDate);

        Bundle extras = getIntent().getExtras();
        itemName=extras.getString("ItemName");
        itemPrice=extras.getString("ItemPrice");
        Mfg=extras.getString("mfg");
        Exp=extras.getString("exp");
        Log.v("name",itemName);
        Log.v("price",itemPrice);
        if (itemName.equals("Sanitary Napkins"))
        otherName.setText("Whisper Ultra XXL 360mm");
        else
        otherName.setText(itemName);
        otherPrice.setText("₹ "+itemPrice+".00");
        otherMfg.setText(Mfg);
        otherExp.setText(Exp);
        iconSet(itemName);
        quant=1;
        otherQuantity.setText(""+quant);
        btSocket= aInitialScreen.btSocket;

    }

    public void otherConf(View view){
        flag=0;
        flag++;
        if (selecteditmQuantity<1){
            Toast toast = Toast.makeText(this, "0 " + itemName + " Remaining", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(38);
            toast.show();
        }
        else
        sender(itemName+"%"+itemPrice+"%"+quant);
        //
       // else
         //

    }

    public void sender(final String i){

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    btSocket.getOutputStream().write(i.getBytes());
                    Intent intent= new Intent(iPopupOtherProducts.this,kFirstTimeMoney.class);
                    intent.putExtra("Name",itemName);
                    intent.putExtra("Quantity",quant);
                    intent.putExtra("Price",itemPrice);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }

    public void iconSet(String string){
        if ("Condoms Packets".equals(string)){
            otherImage.setImageResource(R.drawable.condimg);
            selecteditmQuantity = aInitialScreen.condomQuant;
        }
        else if("Hand Sanitizer".equals(string)){
            otherImage.setImageResource(R.drawable.detsanz);
            selecteditmQuantity = aInitialScreen.sanzQuant;
        }
        else if ("Sanitary Napkins".equals(string)){
            otherImage.setImageResource(R.drawable.whisper);
            selecteditmQuantity = aInitialScreen.whisperQuant;
        }
        else if("Wet Wipes".equals(string)){
            otherImage.setImageResource(R.drawable.wetwipes);
            selecteditmQuantity = aInitialScreen.wipesQuant;
        }
        else if ("Water Bottle".equals(string)){
            otherImage.setImageResource(R.drawable.bottle);
            selecteditmQuantity = aInitialScreen.bisleri;
        }
    }

    public class inputReceiver extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String str = null;
            while (str==null){
                str=inputReception();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String str1) {
            super.onPostExecute(str1);
            if (flag==2)
            {
                flag++;
                output=""+str1;
                Toast.makeText(iPopupOtherProducts.this, output, Toast.LENGTH_SHORT).show();
                Log.v("onpost","2) The output is:" + output);
            }
            if (flag==3)
                Log.v("onpost","3) The output is:" + output);

        }
    }

    public String inputReception(){
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        String inputData = null;
        try {
            int bytesAvailable = mmInputStream.available();
            if (bytesAvailable>0){
                int mBytes;
                byte[] packetBytes = null;
                packetBytes=new byte[bytesAvailable];
                mBytes=mmInputStream.read(packetBytes,0,bytesAvailable);
                Log.v("inputReception","mBytes Value: " + mBytes );
                for (int pstn = 0;pstn<mBytes;pstn++){
                    readBuffer[readBufferPosition]=packetBytes[pstn];
                    readBufferPosition++;
                }
            }
            inputData=new String(readBuffer,0,bytesAvailable);
            Log.v("Reception","The string is:"+inputData);
            readBufferPosition=0;
            return inputData;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void receiverInput(){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                receiver=new inputReceiver();
                try {
                    mmInputStream=btSocket.getInputStream();
                    receiver.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
    }

    public void confsim(){
        flag++;
        if (quant>0)
        {
            sender(itemName+"%"+itemPrice+"%"+quant);
            receiverInput();
        }
        else
            Toast.makeText(this, "You have chosen 0 quantity of "+itemName, Toast.LENGTH_SHORT).show();

    }

    public void exitActi(View view){
        finish();
    }



}
