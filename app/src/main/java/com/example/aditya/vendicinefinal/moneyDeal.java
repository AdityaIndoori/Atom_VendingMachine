package com.example.aditya.vendicinefinal;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.media.Image;
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
import java.security.PublicKey;

public class moneyDeal extends AppCompatActivity {
    public static String namestr,pricestr;
    public static int quantityint;
    TextView tv1,tv2;
    ImageView iv1;
    static int totalPrice;
    public static int ten,twenty,fifty,hundred;
    //----------
    BluetoothSocket btSocket = null;
    byte[] readBuffer;
    int readBufferPosition;
    public InputStream mmInputStream;
    public inputReceiver receiver;
    public int flag=0;
    String output=null;
    boolean flagBTN;
    //------------------------------
    int itmPrice=0;
    int outputPrice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_deal);
        flagBTN=true;
        ten=0;
        twenty=0;
        fifty=0;
        hundred=0;
        //---------------
        btSocket=Intro.btSocket;
        try {
            mmInputStream = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--------------------
        Bundle extra = getIntent().getExtras();
        namestr=extra.getString("Name");
        pricestr=extra.getString("Price");
        itmPrice=Integer.parseInt(pricestr);
        Log.v("ItemPrice","The Price is: " + itmPrice);
        quantityint=extra.getInt("Quantity");
        totalPrice=itmPrice*quantityint;

        int roundedPrice=0;
        if (totalPrice<=10)
            roundedPrice=10;
        else if (totalPrice<=20)
            roundedPrice=20;
        else if (totalPrice<=50)
            roundedPrice=50;
        else if (totalPrice<=100)
            roundedPrice=100;
        else
        roundedPrice=100;


        iv1=(ImageView)findViewById(R.id.iconView);
        tv1=(TextView)findViewById(R.id.productIDName);
        tv2=(TextView)findViewById(R.id.productPrice2);
        tv1.setText(" "+namestr +"\n");
        if(roundedPrice>50)
        tv2.setText("Price : ₹"+totalPrice+".00"+"\nPlease Enter :  ₹ "+roundedPrice + ".00\nand Tap OK");
        //
        else if (namestr.equals("RiteBite\nNutri Bar\n(Merry Berry)")){
            tv2.setText("Price : ₹"+totalPrice+".00"+"\n\nAtom accepting only\n₹10, ₹20, ₹50 INR notes for\n"+"RiteBite Nutribar\n(Merry Berry)");
            namestr="Merry Berry";
        }
        else if (namestr.equals("RiteBite\nNutri bar\n(Choco Delite)")){
            tv2.setText("Price : ₹"+totalPrice+".00"+"\n\nAtom accepting only\n₹10, ₹20, ₹50 INR notes for\n"+"RiteBite Nutribar\n(Choco Delite)");
            namestr="Choco Delite";
        }
        else
        tv2.setText("Price : ₹"+totalPrice+".00"+"\n\nAtom accepting only\n₹10, ₹20, ₹50 INR notes for\n"+namestr);
        iconSet(namestr);
    }
    public void okClick(View view){
        //receive the amount entered into the note validator and toast it!
        if (flagBTN){
            flagBTN=false;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag=0;
                    flag++;
                    sender("Money Entered");
                    receiverInput();
                    if (flag==1)
                    {
                        confsim();
                    }
                }
            },1500);
        }
        else{
            Toast toast = Toast.makeText(this, "Please Wait, Processing Cash", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(32);
            toast.show();
        }
    }
    public void sender(final String i){

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    btSocket.getOutputStream().write(i.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
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
    public class inputReceiver extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String str = null;
            while (str == null) {
                str = inputReception();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String str1) {
            super.onPostExecute(str1);
            if (flag == 2) {
                flag++;
                output = "" + str1;
                //Toast.makeText(moneyDeal.this, output, Toast.LENGTH_SHORT).show();
                Log.v("onpostMD", "2) The output is:" + output);
            }
            else if (flag == 3) {
                Log.v("onpostMD", "3) The output is:" + output);
                if (!"".equals(output)){
                    outputPrice = Integer.parseInt(output);
                    Log.v("Note Entered", "The entered note is: " + outputPrice);
                    if (outputPrice >= 10 && outputPrice <= 100) {
                        if (outputPrice==10)
                            ten++;
                        if (outputPrice==20)
                            twenty++;
                        if (outputPrice==50)
                            fifty++;
                        if (outputPrice==100)
                            hundred++;
                        Intent lastIntent = new Intent(getApplicationContext(), lastActivity.class);
                        lastIntent.putExtra("Balance", outputPrice - totalPrice);
                        startActivity(lastIntent);
                    } else if (outputPrice > 100||outputPrice<10) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Cannot Accept Notes Less that ₹ 10/- or greater than ₹ 100/-", Toast.LENGTH_SHORT);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(32);
                        toast.show();
                        flagBTN=true;
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You haven't entered any money!", Toast.LENGTH_SHORT);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(32);
                    toast.show();
                    flagBTN=true;
                }
            }
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
    public void confsim(){
        flag++;
        sender("Money Entered");
        receiverInput();
    }
    public void iconSet(String string){
        if ("Snickers".equals(string))
            iv1.setImageResource(R.drawable.snickers);
        else if ("Hand Sanitizer".equals(string))
            iv1.setImageResource(R.drawable.detsanz);
        else if ("Condoms Packets".equals(string))
            iv1.setImageResource(R.drawable.condimg);
        else if ("Sanitary Napkins".equals(string))
            iv1.setImageResource(R.drawable.whisper);
        else if ("Wet Wipes".equals(string))
            iv1.setImageResource(R.drawable.wetwipes);
        else {
            switch (string){
                case "OKACET COLD":
                    iv1.setImageResource(R.drawable.okacetcold);
                    break;
                case "METROGYL - 400":
                    iv1.setImageResource(R.drawable.metrogyl);
                    break;
                case "ELDOPER":
                    iv1.setImageResource(R.drawable.eldoper);
                    break;
                case "DOLO - 650":
                    iv1.setImageResource(R.drawable.dolo650);
                    break;
                case "GELUSIL":
                    iv1.setImageResource(R.drawable.gelusil);
                    break;
                case "PARACIP - 500":
                    iv1.setImageResource(R.drawable.paracip500);
                    break;
                case "MEFTAL SPAS":
                    iv1.setImageResource(R.drawable.meftalspas);
                    break;
                case "Merry Berry":
                    iv1.setImageResource(R.drawable.nutria);
                    break;
                case "Choco Delite":
                    iv1.setImageResource(R.drawable.nutrib);
                    break;
                case "Water Bottle":
                    iv1.setImageResource(R.drawable.bottle);
                    break;
                case "Wild Vitamin Drink\n(Dragon Fruit)":
                    iv1.setImageResource(R.drawable.wild);
                    break;
                case "Zago Protein Drink\n(Chocolate)":
                    iv1.setImageResource(R.drawable.zago);
                    break;
                case "Aloevera Litchi Drink":
                    iv1.setImageResource(R.drawable.aloe);
                    break;
                case "Minute Maid Pulpy Orange":
                    iv1.setImageResource(R.drawable.pulpy);
                    break;
                default:
                    iv1.setImageResource(R.drawable.commontablet1);
                    break;
            }
        }
    }
    public  void backMoneyClick(View view){
        Intent intent= new Intent(this,dummyIntro.class);
        startActivity(intent);
    }
    public static void maxQuantity(){
        if ("Condoms Packets".equals(namestr))
            Intro.condomQuant=Intro.condomQuant-quantityint;
        else if ("Hand Sanitizer".equals(namestr))
            Intro.sanzQuant=Intro.sanzQuant-quantityint;
        else if ("Sanitary Napkins".equals(namestr))
            Intro.whisperQuant=Intro.whisperQuant-quantityint;
        else if ("Wet Wipes".equals(namestr))
            Intro.wipesQuant=Intro.wipesQuant-quantityint;
        else {
            switch (namestr){
                case "OKACET COLD":
                    Intro.okacetQuant=Intro.okacetQuant-quantityint;
                    break;
                case "METROGYL - 400":
                    Intro.metrogylQuant=Intro.metrogylQuant-quantityint;
                    break;
                case "ELDOPER":
                    Intro.eldoperQuant=Intro.eldoperQuant-quantityint;
                    break;
                case "DOLO - 650":
                    Intro.doloQuant=Intro.doloQuant-quantityint;
                    break;
                case "GELUSIL":
                    Intro.gelusil=Intro.gelusil-quantityint;
                    break;
                case "MEFTAL SPAS":
                    Intro.meftalQuant=Intro.meftalQuant-quantityint;
                    break;
                case "Merry Berry":
                    Intro.nutriaQuant=Intro.nutriaQuant-quantityint;
                    break;
                case "Choco Delite":
                    Intro.nutribQuant=Intro.nutribQuant-quantityint;
                    break;
                case "Water Bottle":
                    Intro.bisleri=Intro.bisleri-quantityint;
                    break;
                case "Wild Vitamin Drink\n(Dragon Fruit)":
                    Intro.wild=Intro.wild-quantityint;
                    break;
                case "Zago Protein Drink\n(Chocolate)":
                    Intro.zago=Intro.zago-quantityint;
                    break;
                case "Aloevera Litchi Drink":
                    Intro.aloe=Intro.aloe-quantityint;
                    break;
                case "Minute Maid Pulpy Orange":
                    Intro.pulpy=Intro.pulpy-quantityint;
                    break;
                default:
                    break;
            }
        }
    }
//Steps on inserting money are:
    //1)flg=0;
    //2)flag++;
    //3)sender("Text") to be sent to arduino
    //4)Call the receiveInput() method;
    //5)if(Flag==1):
                //5a)flag++
                //5b)sender("Text"); to be sent to arduino



}
