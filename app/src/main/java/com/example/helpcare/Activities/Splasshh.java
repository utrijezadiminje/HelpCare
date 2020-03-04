package com.example.helpcare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.helpcare.FirstRunCheck;
import com.example.helpcare.R;

public class Splasshh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasshh);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splasshh.this, FirstRunCheck.class);
                Splasshh.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        },1500);

    }
}
