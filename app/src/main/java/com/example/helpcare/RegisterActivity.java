package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private EditText Password2;
    private int T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.etUsername);
        Password=findViewById(R.id.etPassword1);
        Password2=findViewById(R.id.etPassword2);
        Spinner Spinner =findViewById(R.id.spinner);
        Button Register=findViewById(R.id.btnRegister);
        TextView LogIn=findViewById(R.id.login);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                T=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String pass1 = Password.getText().toString();
                String pass2 = Password2.getText().toString();
                if (pass1.equals(pass2)) {
                    val(username, pass1, T);
                }
                else {
                    CharSequence text = "LOZINKE SE NE POKLAPAJU";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    private void val(String username, String password, int type){
        String cmd = "register";
        Database database = new Database(this);
        database.execute(cmd, username, password);
    }
}
