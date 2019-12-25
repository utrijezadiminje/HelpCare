package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private EditText Password2;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
}
/*public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = findViewById(R.id.etName);
        Password=findViewById(R.id.etPassword);
        Login =findViewById(R.id.btnLogin);
        Tw=findViewById(R.id.textView);
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

    }
    private void val(String userN,String userP){
        if((userN.equals("Admin")) && (userP.equals("123"))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}*/