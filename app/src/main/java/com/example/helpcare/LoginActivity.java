package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.helpcare.Activities.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Tw;
    private Button Login;
    private TextView Reg;
    private TextView admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = findViewById(R.id.etName);
        Name.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(Name, InputMethodManager.SHOW_IMPLICIT);
        Password=findViewById(R.id.etPassword);
        Login =findViewById(R.id.btnLogin);
        Tw=findViewById(R.id.textView);
        Reg=findViewById(R.id.textView2);
        admin=findViewById(R.id.textView3);
        Tw.setVisibility(View.GONE);
        Login.setEnabled(true);
        Password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Password.getText().length()>0){
                    Tw.setVisibility(View.VISIBLE);
                }
                else
                    Tw.setVisibility(View.GONE);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Tw.getText()=="PRIKAŽI"){
                    Tw.setText("SAKRIJ");
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Password.setSelection(Password.length());
                }
                else{
                    Tw.setText("PRIKAŽI");
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Password.setSelection(Password.length());
                }
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emptyChecker()) {
                    val(Name.getText().toString(), Password.getText().toString());
                    final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                            "ČEKANJE...", true);
                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                            dialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 900);
                }
            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,AdminLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
    private void val(String username, String password){
        PrefUtils.saveUser(username,this);
        PrefUtils.savePassword(password,this);
        String cmd = "login";
        Database database = new Database(this);
        database.execute(cmd, username, password);
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("PAŽNJA");
        builder.setMessage("DA LI SIGURNO ŽELITE DA IZAĐETE?");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                finish();
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
    private boolean emptyChecker()
    {
        if(Name.getText().toString().trim().equalsIgnoreCase("")){
            Name.setError("UNESITE KORISNIČKO IME");
            return false;
        }
        else
        if(Password.getText().toString().trim().equalsIgnoreCase("")){
            Password.setError("UNESITE LOZINKU");
            return false;
        }
        return true;
    }
}

