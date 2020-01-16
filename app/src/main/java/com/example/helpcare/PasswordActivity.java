package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    private EditText oldPassword;
    private EditText Password1;
    private EditText Password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        oldPassword = findViewById(R.id.oldPassword);
        Password1=findViewById(R.id.newPassword1);
        Password2=findViewById(R.id.newPassword2);
        String[] StariPass = new String[]{"1", "2", "3"}; //zameni sa lozinkom iz baze
        if (Password1.equals(Password2) && StariPass.equals(oldPassword)){
            //zameni Password
        }
        else if(!Password1.equals(Password2)){
            CharSequence text = "LOZINKE SE NE POKLAPAJU";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(!StariPass.equals(oldPassword)){
            CharSequence text = "STARA LOZINKA SE NE POKLAPA";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            CharSequence text = "DOŠLO JE DO GREŠKE";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
