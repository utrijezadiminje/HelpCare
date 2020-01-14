package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private Spinner Spinner;
    private EditText Username;
    private EditText Password;
    private EditText Password2;
    private Button Register;
    private TextView LogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.etUsername);
        Password=findViewById(R.id.etPassword1);
        Password2=findViewById(R.id.etPassword2);
        Spinner =findViewById(R.id.spinner);
        Register=findViewById(R.id.btnRegister);
        LogIn=findViewById(R.id.login);

        /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = findViewById(R.id.etName);
        Password=findViewById(R.id.etPassword);
        Login =findViewById(R.id.btnLogin);
        Tw=findViewById(R.id.textView);
        Reg=findViewById(R.id.textView2);
        Tw.setVisibility(View.GONE);
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
                if(Tw.getText()=="SHOW"){
                    Tw.setText("HIDE");
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Password.setSelection(Password.length());
                }
                else{
                    Tw.setText("SHOW");
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Password.setSelection(Password.length());
                }
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val(Name.getText().toString(),Password.getText().toString());
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void val(String username, String password){
        String cmd = "login";
        Database database = new Database(this);
        database.execute(cmd, username, password);
        /*
        if((userN.equals("Admin")) && (userP.equals("123"))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
         */
    }

}
