package com.example.alphaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sms extends AppCompatActivity {
    final int SE=1;
    EditText number;
    EditText text;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        number= findViewById(R.id.editTextPhone);
        text= findViewById(R.id.editTextTextMultiLine);
        send= findViewById(R.id.button);

        send.setEnabled(false);
        if(checkper(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.SEND_SMS},SE);


        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("main"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("storage"))) {
            Intent si = new Intent(this, Storage.class);
            startActivity(si);
        }
        if ((st.equals("auth"))) {
            Intent si = new Intent(this, Auth.class);
            startActivity(si);
        }
        if ((st.equals("location"))) {
            Intent si = new Intent(this, Location2.class);
            startActivity(si);
        }
        return true;
    }

    public void send(View view) {
        String phonenumber= number.getText().toString();
        String textmassage = text.getText().toString();

        if (phonenumber==null || phonenumber.length()==0 || textmassage==null || textmassage.length()==0){
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkper(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, textmassage, null, null);
            Toast.makeText(this, "massage sent", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean checkper( String permission ){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }

}
