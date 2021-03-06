package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.helpcare.Activities.MainActivity;

public class FirstRunCheck extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(PrefUtils.getLog(this))
        {
            SharedPreferences sharedPreferences=getSharedPreferences("preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putBoolean("team",false);
            editor.commit();
            
            Intent intent = new Intent(FirstRunCheck.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(FirstRunCheck.this,LoginActivity.class);
            startActivity(intent);


        }
    }
}