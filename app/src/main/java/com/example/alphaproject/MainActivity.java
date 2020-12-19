package com.example.alphaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if ((st.equals("sms"))) {
            Intent si = new Intent(this, Sms.class);
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
    } }