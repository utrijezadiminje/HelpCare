package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    private EditText oldPassword;
    private EditText Password1;
    private EditText Password2;
    private Button Change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        oldPassword = findViewById(R.id.oldPassword);
        Password1 = findViewById(R.id.newPassword1);
        Password2 = findViewById(R.id.newPassword2);
        Change = findViewById(R.id.btnReset);
        oldPassword.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(oldPassword, InputMethodManager.SHOW_IMPLICIT);
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass1 = Password1.getText().toString();
                String Pass2 = Password2.getText().toString();
                String OldPass = oldPassword.getText().toString();
                String UserP = PrefUtils.getPassword(getApplicationContext());
                if (Pass1.equals(Pass2) && UserP.equals(OldPass)) {
                    String cmd = "newpass";
                    String UserN = PrefUtils.getUser(getApplicationContext());
                    Database database = new Database(this,getApplicationContext());
                    database.execute(cmd, UserN, UserP, Pass1);
                    PrefUtils.savePassword(Pass1, getApplicationContext());
                } else if (!Pass1.equals(Pass2)) {
                    CharSequence text = "LOZINKE SE NE POKLAPAJU";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!OldPass.equals(UserP)) {
                    CharSequence text = "STARA LOZINKA SE NE POKLAPA";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    CharSequence text = "DOŠLO JE DO GREŠKE";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
