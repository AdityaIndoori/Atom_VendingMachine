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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class hPopupEFG extends AppCompatActivity {
    TextView quantIndicator,otherMfg,otherExp;;
    public int quantity;
    BluetoothSocket btSocket = null;
    public String name;
    public String priceStr;
    byte[] readBuffer;
    int readBufferPosition;
    public InputStream mmInputStream;
    public  inputReceiver receiver;
    public int flag=0;
    String output,Mfg,Exp;;
    ImageView tabImgV;
    int selectedItemQnt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_popup_efg);
        tabImgV=(ImageView)findViewById(R.id.tabletImg);
        TextView nameView = (TextView)findViewById(R.id.name);
        TextView priceView = (TextView)findViewById(R.id.price);
        quantIndicator = (TextView)findViewById(R.id.quantityIndicator);
        otherMfg=(TextView) findViewById(R.id.Mfgdate);
        otherExp=(TextView)findViewById(R.id.Expdate);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("TabletName");
        priceStr = extras.getString("TabletPrice");
        Mfg=extras.getString("mfg");
        Exp=extras.getString("exp");
        String detailStr = extras.getString("Tablet+Details");
        imgSetter();
        nameView.setText(name);
        priceView.setText("₹ "+priceStr);
        quantity=1;
        quantIndicator.setText(""+quantity);
        otherMfg.setText(Mfg);
        otherExp.setText(Exp);
        btSocket= aInitialScreen.btSocket;
        try {
            mmInputStream = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void confirmation(View view){

        flag=0;
        flag++;
        if (selectedItemQnt<1){
            Toast toast = Toast.makeText(this, "0 " + name + "'s Remaining", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(38);
            toast.show();
        }
        else
        sender(name+"%"+priceStr+"%"+quantity);
    }

    public void sender(final String i){

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    btSocket.getOutputStream().write(i.getBytes());
                    Intent intent= new Intent(hPopupEFG.this,kFirstTimeMoney.class);
                    intent.putExtra("Name",name);
                    intent.putExtra("Quantity",quantity);
                    intent.putExtra("Price",priceStr);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
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
                Toast.makeText(hPopupEFG.this, output, Toast.LENGTH_SHORT).show();
                Log.v("onpost","2) The output is:" + output);
            }
            if (flag==3)
                Log.v("onpost","3) The output is:" + output);

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

    public void confsim(){
        flag++;
        if (quantity>0)
        {
            sender(name+"%"+priceStr+"%"+quantity);
            receiverInput();
        }
        else
            Toast.makeText(this, "You have chosen 0 quantity of "+name, Toast.LENGTH_SHORT).show();

    }

    public void imgSetter(){
        switch (name){
            case "OKACET COLD":
                tabImgV.setImageResource(R.drawable.okacetcold);
                selectedItemQnt = aInitialScreen.okacetQuant;
                break;
            case "METROGYL - 400":
                tabImgV.setImageResource(R.drawable.metrogyl);
                selectedItemQnt = aInitialScreen.metrogylQuant;
                break;
            case "ELDOPER":
                tabImgV.setImageResource(R.drawable.eldoper);
                selectedItemQnt = aInitialScreen.eldoperQuant;
                break;
            case "DOLO - 650":
                tabImgV.setImageResource(R.drawable.dolo650);
                selectedItemQnt = aInitialScreen.doloQuant;
                break;
            case "GELUSIL":
                tabImgV.setImageResource(R.drawable.gelusil);
                selectedItemQnt = aInitialScreen.gelusil;
                break;
            case "PARACIP - 500":
                tabImgV.setImageResource(R.drawable.paracip500);
                selectedItemQnt = 0;
                break;
            case "MEFTAL SPAS":
                tabImgV.setImageResource(R.drawable.meftalspas);
                selectedItemQnt = aInitialScreen.meftalQuant;
                break;
            case "RiteBite\nNutri Bar\n(Merry Berry)":
                tabImgV.setImageResource(R.drawable.nutria);
                selectedItemQnt = aInitialScreen.nutriaQuant;
                break;
            case "RiteBite\nNutri bar\n(Choco Delite)":
                tabImgV.setImageResource(R.drawable.nutrib);
                selectedItemQnt = aInitialScreen.nutribQuant;
                break;
            case "Water Bottle":
                tabImgV.setImageResource(R.drawable.bottle);
                selectedItemQnt= aInitialScreen.bisleri;
                break;

            case "Wild Vitamin Drink\n(Dragon Fruit)":
                tabImgV.setImageResource(R.drawable.wild);
                selectedItemQnt= aInitialScreen.wild;
                break;

            case "Redbull":
                tabImgV.setImageResource(R.drawable.redbull);
                selectedItemQnt= aInitialScreen.redbull;
                break;

            case "Aloevera Litchi Drink":
                tabImgV.setImageResource(R.drawable.aloe);
                selectedItemQnt= aInitialScreen.aloe;
                break;

            case "Minute Maid Pulpy Orange":
                tabImgV.setImageResource(R.drawable.pulpy);
                selectedItemQnt= aInitialScreen.pulpy;
                break;

            default:
                tabImgV.setImageResource(R.drawable.commontablet1);
                selectedItemQnt = 0;
                break;
        }
    }

    public void exitAct(View view){
        finish();
    }

}
