package com.example.aditya.vendicinefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class tabletSelection extends AppCompatActivity {

    String Mdate,Mmonth,Myear,Edate,Emonth,Eyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity_tablets_selection);
    }


    public void tablet1Click(View view){

        if (true){
            Log.v("Tablet","Tablet1 is paracip");
        }
        else
        {
            Mdate="01";
            Mmonth="01";
            Myear="01";
            Edate="02";Emonth="02";Eyear="02";
            String details="This is the 1st Tablet Details";
            TextView tabNameView= (TextView)findViewById(R.id.tablet1_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet1_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING",substr);
            Intent intent=new Intent(this,PopupQuantity.class);
            intent.putExtra("TabletName",tablet_name);
            intent.putExtra("TabletPrice",substr);
            intent.putExtra("TabletDetails",details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet2Click(View view){
        if (Intro.doloQuant<1){
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
            Mdate="01";
            Mmonth="08";
            Myear="16";
            Edate="01";Emonth="01";Eyear="20";
            String details="This is the 2nd Tablet Details";
            TextView tabNameView= (TextView)findViewById(R.id.tablet2_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet2_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING",substr);
            Intent intent=new Intent(this,PopupQuantity.class);
            intent.putExtra("TabletName",tablet_name);
            intent.putExtra("TabletPrice",substr);
            intent.putExtra("TabletDetails",details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet3Click(View view){
        if (Intro.metrogylQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="08";
            Myear="16";
            Edate="01";Emonth="07";Eyear="20";
            String details="This is the 3rd Tablet Details";
            TextView tabNameView= (TextView)findViewById(R.id.tablet3_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet3_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING",substr);
            Intent intent=new Intent(this,PopupQuantity.class);
            intent.putExtra("TabletName",tablet_name);
            intent.putExtra("TabletPrice",substr);
            intent.putExtra("TabletDetails",details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet4Click(View view){
        if (Intro.okacetQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="09";
            Myear="16";
            Edate="01";Emonth="03";Eyear="18";
            String details = "This is the 4th Tablet Details";
            TextView tabNameView = (TextView) findViewById(R.id.tablet4_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet4_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING", substr);
            Intent intent = new Intent(this, PopupQuantity.class);
            intent.putExtra("TabletName", tablet_name);
            intent.putExtra("TabletPrice", substr);
            intent.putExtra("TabletDetails", details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet5Click(View view){
        if (Intro.eldoperQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="04";
            Myear="16";
            Edate="01";Emonth="09";Eyear="17";
            String details = "This is the 5th Tablet Details";
            TextView tabNameView = (TextView) findViewById(R.id.tablet5_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet5_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING", substr);
            Intent intent = new Intent(this, PopupQuantity.class);
            intent.putExtra("TabletName", tablet_name);
            intent.putExtra("TabletPrice", substr);
            intent.putExtra("TabletDetails", details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet6Click(View view){
        if (Intro.gelusil<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {
            Mdate="01";
            Mmonth="03";
            Myear="16";
            Edate="01";Emonth="05";Eyear="19";
            String details = "This is the 6th Tablet Details";
            TextView tabNameView = (TextView) findViewById(R.id.tablet6_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet6_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING", substr);
            Intent intent = new Intent(this, PopupQuantity.class);
            intent.putExtra("TabletName", tablet_name);
            intent.putExtra("TabletPrice", substr);
            intent.putExtra("TabletDetails", details);
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void tablet7Click(View view){
        if (Intro.meftalQuant<1){
            Intent popIntent = new Intent(this,PopupActivity.class);
            popIntent.putExtra("String","The product you have chosen is out of stock");
            startActivity(popIntent);
        }
        else {

            String details = "This is the 7th Tablet Details";
            TextView tabNameView = (TextView) findViewById(R.id.tablet7_name);
            String tablet_name = tabNameView.getText().toString();
            TextView tabPriceView = (TextView) findViewById(R.id.tablet7_price);
            String tablet_price = tabPriceView.getText().toString();
            String substr = tablet_price.substring(2);
            Log.v("SUBSTRING", substr);
            Intent intent = new Intent(this, PopupQuantity.class);
            intent.putExtra("TabletName", tablet_name);
            intent.putExtra("TabletPrice", substr);
            intent.putExtra("TabletDetails", details);
            Mdate="01";
            Mmonth="07";
            Myear="16";
            Edate="01";Emonth="07";Eyear="19";
            intent.putExtra("mfg",Mdate+"/"+Mmonth+"/"+Myear);
            intent.putExtra("exp",Edate+"/"+Emonth+"/"+Eyear);
            startActivity(intent);
        }
    }

    public void exitActiv(View view){
        finish();
    }
}
