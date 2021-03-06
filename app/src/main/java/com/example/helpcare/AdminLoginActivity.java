package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.jar.Attributes;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText name;
    private TextView showhide;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        name=findViewById(R.id.admin);
        name.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
        showhide=findViewById(R.id.showhide);
        password=findViewById(R.id.password);
        login=findViewById(R.id.logIn);
        showhide.setVisibility(View.GONE);
        final String show = getString(R.string.show);
        final String hide = getString(R.string.hide);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(password.getText().length()>0){
                    showhide.setVisibility(View.VISIBLE);
                }
                else
                    showhide.setVisibility(View.GONE);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        showhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showhide.getText().equals(show)){
                    showhide.setText(hide);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.length());
                }
                else{
                    showhide.setText(show);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(emptyChecker()) {
                    val(password.getText().toString(), password.getText().toString());
                    final ProgressDialog dialog = ProgressDialog.show(AdminLoginActivity.this, "",
                            "ČEKANJE...", true);
                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            dialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 900);

                }
            }
        });
}
    private void val(String username, String password){
        String cmd = "adminl";
        Database database = new Database(this);
        database.execute(cmd, username, password);
    }

    private boolean emptyChecker()
    {
        if(name.getText().toString().trim().equalsIgnoreCase("")){
            name.setError("UNESITE KORISNIČKO IME");
            return false;
        }
        else
        if(password.getText().toString().trim().equalsIgnoreCase("")){
            password.setError("UNESITE LOZINKU");
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AdminLoginActivity.this,LoginActivity.class);
        startActivity(intent);
    }

}
