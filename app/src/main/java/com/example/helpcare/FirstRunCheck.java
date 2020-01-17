package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FirstRunCheck extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(PrefUtils.getLog(this))
        {
            Intent intent = new Intent(FirstRunCheck.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(FirstRunCheck.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}