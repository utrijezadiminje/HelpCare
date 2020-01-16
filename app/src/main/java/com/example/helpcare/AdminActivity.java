package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AdminActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}