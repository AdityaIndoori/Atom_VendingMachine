package com.example.aditya.vendicinefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class nutriSel extends AppCompatActivity {

    String Mdate,Mmonth,Myear,Edate,Emonth,Eyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri_sel);
    }

    public void nutria(View view){
        if (Intro.nutriaQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
            /*
            Toast toast = Toast.makeText(this, "The product you have chosen is out of stock", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(38);
            toast.show();*/
        }
        else {
            String details="This is the merryberry nutri";
            TextView tabNameView= (TextView)findViewById(R.id.nutria_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.nutria_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING",substr);
            Intent intent=new Intent(this,PopupQuantity.class);
            intent.putExtra("TabletName",tablet_name);
            intent.putExtra("TabletPrice",substr);
            intent.putExtra("TabletDetails",details);
            Mdate="13";
            Mmonth="09";
            Myear="16";
            Edate="13";Emonth="16";Eyear="17";
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void nutrib(View view){
        if (Intro.nutribQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            String details="This is the choco delite nutri";
            TextView tabNameView= (TextView)findViewById(R.id.nutrib_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.nutrib_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING",substr);
            Intent intent=new Intent(this,PopupQuantity.class);
            intent.putExtra("TabletName",tablet_name);
            intent.putExtra("TabletPrice",substr);
            intent.putExtra("TabletDetails",details);
            Mdate="30";
            Mmonth="09";
            Myear="16";
            Edate="30";Emonth="06";Eyear="17";
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void exitActiv(View view){
        finish();
    }

}
