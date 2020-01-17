package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private EditText name;
    private Button password;
    private TextView logOut;
    private Button changeUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name=findViewById(R.id.editText);
        password = findViewById(R.id.button2);
        logOut=findViewById(R.id.logOut);
        name.requestFocus();
        String Username = PrefUtils.getUser(this);
        name.setText(Username);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        changeUsername=findViewById(R.id.button);
        final String changeUser=changeUsername.getText().toString();
        imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User1 = name.getText().toString();
                String User2 = PrefUtils.getUser(getApplicationContext());
                if (User1.equals(User2)) {
                    CharSequence text = "UPIŠITE NOVO IME";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();

                } else{
                    String cmd = "newname";
                    Database database = new Database(this,getApplicationContext());
                    database.execute(cmd, User2, User1);
                    PrefUtils.saveUser(User1, getApplicationContext());
                }
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("PAŽNJA");
                builder.setMessage("DA LI SIGURNO ŽELITE DA SE ODJAVITE?");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Logout();
                    }
                });
                builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    public void Logout()
    {
        PrefUtils.saveLog(false,this);
        Intent intent = new Intent(UserActivity.this,LoginActivity.class);
        startActivity(intent);
        CharSequence text = "USPEŠNO STE SE IZLOGOVALI";
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
