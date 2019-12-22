package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText)findViewById(R.id.etName);
        Password= (EditText)findViewById(R.id.etPassword);
        Login =(Button)findViewById(R.id.btnLogin);

    }
    private void val(String iN,String iP,String userN,String userP){
        if(userN==iN && userP==iP) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        }
    }
}
