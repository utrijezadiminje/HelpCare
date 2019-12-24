package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class FirstRunCheck extends AppCompatActivity {

    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Perhaps set content view here
        prefs = getSharedPreferences("com.example.helpcare", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Intent intent = new Intent(this,LoginActivity.class);
            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }
    }